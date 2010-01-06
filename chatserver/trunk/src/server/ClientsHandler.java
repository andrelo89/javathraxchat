package server;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.common.TransportType;
import org.apache.mina.transport.socket.nio.SocketSessionConfig;

public class ClientsHandler extends IoHandlerAdapter {
	private final Logger logger = Logger.getLogger(getClass().getName());
	
//	private Dispatcher dispatcher;
	private List<User> users = new ArrayList<User>();
	private List<String> rooms = Collections.synchronizedList(new ArrayList<String>());
	private HashMap<IoSession, User> mapping = new HashMap<IoSession, User>();
	private boolean admin = false;
	private boolean loggedIn = false;
	
	public void exceptionCaught(IoSession session, Throwable t) throws Exception {
		t.printStackTrace();
		session.close();
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		User user = new User();
		users.add( user);
		mapping.put(session, user);
	}
	public void messageReceived(IoSession session, Object objectMessage) throws Exception {
		String input = objectMessage.toString();
//		if( input.trim().equalsIgnoreCase("quit") ) {
//			session.close();
//			return;
//		}
//
//		Date date = new Date();
//		session.write( date.toString() );
		if (!admin) {
			// business logic
			if (input.startsWith("join ")) {
				if (user.getCurrentRoom() != null) {
					roomInfo("user " + user.getName()
							+ " has left the room", user);
					sendMessage("autoleft room "
							+ user.getCurrentRoom(), user, session);
					user.setCurrentRoom(null);
				}
				String[] tokens = input.split(" ");

				if (tokens.length > 1) {
					String secondToken = tokens[1].trim();
					if (tokens[0].trim().equalsIgnoreCase("join")
							&& hasRoom(secondToken)) {
						if (tokens.length < 3)
							user.setName("Anonymous");
						else
							user.setName(tokens[2].trim());
						user.setCurrentRoom(secondToken);
						roomInfo(user.getName()
								+ " has joined the room", user);
						sendMessage("you have joined room "
								+ secondToken + "\n", user, session);
					}
				}
			} else if (input.startsWith("list channels"))
				printServerRooms();
			else if (input.startsWith("exit")) {
				roomInfo("user " + user.getName()
						+ " has left the room", user);
				sendMessage("you have left the room", user, session);
				user.setCurrentRoom(null);
			} else if (input.startsWith("quit")) {
				sendMessage("bye", user, session);
				//TODO quittin time
			} else if (input.startsWith("@{")) {
				String input2 = input.substring(2);
				String message = input
						.substring(input2.indexOf('}') + 3);

				String[] users = input2.split(",");
				sendToUsers(users, message, user);
			} else
				roomChat(input, user);
		} else {
			if(input.equalsIgnoreCase(getCurrentPassword()))
			{
				loggedIn = true;
				logger.log(Level.INFO, "admin logged in");						
			}
			if(!loggedIn)
			{
				sendMessage("incorrect password\n", user, session);
				logger.log(Level.INFO, "wrong admin passwd");						
				//TODO logged in
			}
			String msg =
				"╔═══════════════╗\n\r"+
				"╠Admin Menu═════╣\n\r" +
				"╠═══════════════╬═══════════╦══════════════╗\n\r"+
				"╠Change Password╬System Logs╬List All Rooms╣\n\r"+
				"║passwd         ║logs       ║rooms         ║\n\r"+
				"╚═══════════════╩═══════════╩══════════════╝\n\r"+
				"╔═════════════════╗\n\r" +
				"╠Room Commands════╣\n\r" +
				"╠═════════════════╬══════════════════════════╦═════════════════╗\n\r"+
				"╠Create Room══════╬Rename Room═══════════════╬Delete Room══════╣\n\r"+
				"║create <roomname>║rename <oldname> <newname>║delete <roomname>║\n\r"+
				"╚═════════════════╩══════════════════════════╩═════════════════╝\n\r";
			
			logger.log(Level.FINEST, "Sending: " + msg);
			sendMessage(msg,user, session);
			String[] tokens = input.split(" ");
			if(input.startsWith("passwd"))
			{
				setCurrentPassword(tokens[1].trim());
				logger.log(Level.INFO, "admin changed passwd to " + getCurrentPassword());
			}
			else if(input.startsWith("create"))
			{
				createRoom(tokens[1].trim());
				logger.log(Level.INFO, "admin created new room: " + tokens[1].trim());
			}
			else if(input.startsWith("rename"))
			{
				renameRoom(tokens[1].trim(), tokens[2].trim());
				logger.log(Level.INFO, "admin renamed room: " + tokens[1].trim() + " to: " + tokens[2].trim() );
			}
			else if(input.startsWith("delete"))
			{
				deleteRoom(tokens[1].trim());
				logger.log(Level.INFO, "admin deleted room: " + tokens[1].trim() + ", users are now lonely and sad");
			}
			else if(input.startsWith("logs"))
			{
				sendMessage(getLogs(), user, null);
				logger.log(Level.INFO, "admin requested log list");
			}
			
			//save on every admin command
			Persister p =  new Persister();
			p.setCurrentRooms(rooms);
			p.setAdminPassword(getCurrentPassword());
			XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("database.xml")));
			e.writeObject(p);
			e.close();
		}
	}

	private void sendToUsers(String[] users, String message, User user2) {
		// TODO Auto-generated method stub
		
	}

	private void roomChat(String input, User user2) {
		// TODO Auto-generated method stub
		
	}

	private String getCurrentPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setCurrentPassword(String trim) {
		// TODO Auto-generated method stub
		
	}

	private void deleteRoom(String trim) {
		// TODO Auto-generated method stub
		
	}

	private void renameRoom(String trim, String trim2) {
		synchronized(rooms)
		{
			rooms.remove(trim);
			for(User u : users)
			{
				
			}
			rooms.add(trim2);
		}		
	}

	private void createRoom(String trim) {
		synchronized(rooms)
		{
			rooms.add(trim);
		}
	}

	private boolean hasRoom(String secondToken) {
		// TODO Auto-generated method stub
		return false;
	}

	private void roomInfo(String string, User user2) {
		// TODO Auto-generated method stub
		
	}

	private String getLogs() {
		// TODO Auto-generated method stub
		return null;
	}

	private void printServerRooms() {
		// TODO Auto-generated method stub
		
	}

	private void sendMessage(String string, User user2, IoSession session) {
		session.write(string);
	}

	public void sessionCreated(IoSession session) throws Exception {

		if( session.getTransportType() == TransportType.SOCKET )
			((SocketSessionConfig) session.getConfig() ).setReceiveBufferSize( 2048 );

        session.setIdleTime( IdleStatus.BOTH_IDLE, 10 );
	}
}
