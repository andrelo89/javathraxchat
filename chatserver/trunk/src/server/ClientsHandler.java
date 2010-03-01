package server;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.common.TransportType;
import org.apache.mina.transport.socket.nio.SocketSessionConfig;

public class ClientsHandler extends IoHandlerAdapter {
	private static final String LINEFEED = "\r";

	private final Logger logger = Logger.getLogger(getClass().getName());

	// private Dispatcher dispatcher;
	private List<IoSession> sessions = Collections
			.synchronizedList(new ArrayList<IoSession>());
	private List<User> users = new ArrayList<User>();
	private List<String> rooms = Collections
			.synchronizedList(new ArrayList<String>());
	private boolean admin = false;
	private boolean loggedIn = false;

	private String adminsPasswd;

	public ClientsHandler(Persister p) {
		rooms = p.getCurrentRooms();
		adminsPasswd = p.getAdminPassword();
	}

	public void exceptionCaught(IoSession session, Throwable t)
			throws Exception {
		t.printStackTrace();
		session.close();
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		User user = new User();
		users.add(user);
		sessions.add(session);
		session.setAttribute("user", user);

		Date date = new Date();
		session.write(date.toString()
				+ "\n\rTo join any of the following rooms\n\r"
				+ "type: join <roomname> <desired nickname> and hit enter\n\r");
		printServerRooms(session);
	}

	public void messageReceived(IoSession session, Object objectMessage)
			throws Exception {
		String input = objectMessage.toString();
		if (input.trim().equalsIgnoreCase("quit")) {
			session.close();
			return;
		}

		User user = (User) session.getAttribute("user");
		if (input.startsWith("admin "))
			admin = true;

		if (!admin) {
			// business logic
			if (input.startsWith("join ")) {
				if (user.getCurrentRoom() != null) {
					sendMessage(
							"user " + user.getName() + " has left the room",
							session);
					sendMessage("autoleft room " + user.getCurrentRoom(),
							session);
					user.setCurrentRoom(null);
				}
				String[] tokens = input.split(" ", 4);

				if (tokens.length > 1) {
					String secondToken = tokens[1].trim();
					if (tokens[0].trim().equalsIgnoreCase("join")
							&& hasRoom(secondToken)) {
						if (tokens.length < 3)
							user.setName("Anonymous");
						else
							user.setName(tokens[2].trim());
						user.setCurrentRoom(secondToken);
						session.setAttribute("room", user.getCurrentRoom());
						sendMessage(user.getName() + " has joined the room",
								session);
						sendMessage("you have joined room " + secondToken
								+ "\n", session);
					}
				}
			} else if (input.startsWith("list channels"))
				printServerRooms(session);
			else if (input.startsWith("exit")) {
				sendMessage("user " + user.getName() + " has left the room",
						session);
				sendMessage("you have left the room", session);
				user.setCurrentRoom(null);
			} else if (input.startsWith("quit")) {
				sendMessage("bye", session);
				session.close();
			} else if (input.startsWith("@{")) {
				String input2 = input.substring(2);
				String message = input.substring(input2.indexOf('}') + 3);

				String[] users = input2.split(",");

				sendToUsers(Arrays.asList(users), message, session);
			} else
				roomChat(input, user);
		} else {
			if (input.split(" ")[1].equalsIgnoreCase(getCurrentPassword())) {
				loggedIn = true;
				logger.log(Level.INFO, "admin logged in");
			}
			if (!loggedIn) {
				sendMessage("incorrect password", session);
				logger.log(Level.INFO, "wrong admin passwd");
				return;
			}
//			String msg = "╔===============|\n\r"
//				+ "╠Admin Menu=====|\n\r"
//				+ "╠===============|===========|==============|\n\r"
//				+ "╠Change Password|System Logs|List All Rooms|\n\r"
//				+ "║passwd         ║logs       ║rooms         ║\n\r"
//				+ "|===============|===========|==============|\n\r"
//				+ "╔=================|\n\r"
//				+ "╠Room Commands====|\n\r"
//				+ "╠=================|==========================|=================|\n\r"
//				+ "╠Create Room======|Rename Room===============|Delete Room======|\n\r"
//				+ "║create <roomname>║rename <oldname> <newname>║delete <roomname>║\n\r"
//				+ "|=================|==========================|=================|\n\r";
			String msg =  "|===============|\n\r"
						+ "|Admin Menu=====|\n\r"
						+ "|===============|===========|==============|\n\r"
						+ "|Change Password|System Logs|List All Rooms|\n\r"
						+ "|passwd         |logs       |rooms         |\n\r"
						+ "|===============|===========|==============|\n\r"
						+ "|=================|\n\r"
						+ "|Room Commands====|\n\r"
						+ "|=================|==========================|=================|\n\r"
						+ "|Create Room======|Rename Room===============|Delete Room======|\n\r"
						+ "|create <roomname>|rename <oldname> <newname>|delete <roomname>|\n\r"
						+ "|=================|==========================|=================|\n\r";

			logger.log(Level.FINEST, "Sending: " + msg);
			sendMessage(msg, session);
			String[] tokens = input.split(" ");
			if (input.startsWith("passwd")) {
				setCurrentPassword(tokens[1].trim());
				logger.log(Level.INFO, "admin changed passwd to "
						+ getCurrentPassword());
			} else if (input.startsWith("create")) {
				createRoom(tokens[1].trim());
				logger.log(Level.INFO, "admin created new room: "
						+ tokens[1].trim());
			} else if (input.startsWith("rename")) {
				renameRoom(tokens[1].trim(), tokens[2].trim());
				logger.log(Level.INFO, "admin renamed room: "
						+ tokens[1].trim() + " to: " + tokens[2].trim());
			} else if (input.startsWith("delete")) {
				deleteRoom(tokens[1].trim());
				logger.log(Level.INFO, "admin deleted room: "
						+ tokens[1].trim() + ", users are now lonely and sad");
			} else if (input.startsWith("logs")) {
				sendMessage(getLogs(), session);
				logger.log(Level.INFO, "admin requested log list");
			}

			// save on every admin command
			Persister p = new Persister();
			p.setCurrentRooms(rooms);
			p.setAdminPassword(getCurrentPassword());
			XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("database.xml")));
			e.writeObject(p);
			e.close();
		}
	}

	private void sendToUsers(List<String> list, String message,
			IoSession session) {
		for (User u : users)
			if (list.contains(u.getName()))
				sendMessage(message, session);
	}

	private void roomChat(String input, User user2) {
		synchronized (sessions) {
			for (IoSession session : sessions) {
				if ((session.getAttribute("room").toString().equals(user2
						.getCurrentRoom())))
					sendMessage(input, session);
			}
		}
	}

	private String getCurrentPassword() {
		return adminsPasswd;
	}

	private void setCurrentPassword(String trim) {
		adminsPasswd = trim;
	}

	private void deleteRoom(String trim) {
		rooms.remove(trim);
	}

	private void renameRoom(String trim, String trim2) {
		synchronized (rooms) {
			rooms.remove(trim);
			for (User u : users) {
				if (u.getCurrentRoom().equals(trim2))
					u.setCurrentRoom(trim2);
			}
			rooms.add(trim2);
		}
	}

	private void createRoom(String trim) {
		synchronized (rooms) {
			rooms.add(trim);
		}
	}

	private boolean hasRoom(String secondToken) {
		for (String room : rooms)
			if (room.equalsIgnoreCase(secondToken))
				return true;
		return false;
	}

	private String getLogs() {
		// TODO Auto-generated method stub
		return null;
	}

	private void printServerRooms(IoSession session) {
		for (String room : rooms) {
			session.write(room + LINEFEED);
		}
	}

	private void sendMessage(String string, IoSession session) {
		session.write(string + LINEFEED);
	}

	public void sessionCreated(IoSession session) throws Exception {

		if (session.getTransportType() == TransportType.SOCKET)
			((SocketSessionConfig) session.getConfig())
					.setReceiveBufferSize(2048);

		session.setIdleTime(IdleStatus.BOTH_IDLE, 10);
	}
}