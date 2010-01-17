package server;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoSession;

public class ClientHandler extends Thread {
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	private Socket socket;
	private Dispatcher dispatcher;
	private BufferedReader reader;
	private PrintWriter writer;
	private User user;
	private SimpleDateFormat sdf = new SimpleDateFormat();
	private boolean admin;
	private boolean loggedIn = false;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public ClientHandler(Socket socket, Dispatcher dispatcher,
			boolean admin) {
		this.admin = admin;
		this.socket = socket;
		this.dispatcher = dispatcher;

		if (!admin) {
			dispatcher.incrementClients();
			try {
				reader = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);
				writer.println("connected at " + sdf.format(new Date()) + "\n");
				printServerRooms();
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			logger.log(Level.INFO, "new user");
			user = new User();
		}
		else
		{
			try {
				reader = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);
				writer.println("Please enter admin password");
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			logger.log(Level.INFO, "admin connected");
		}
		start();
	}

	public ClientHandler() {
		// TODO Auto-generated constructor stub
	}

	private void printServerRooms() {
		writer.println("To join any of the following rooms\n\r" +
				"type: join <roomname> <desired nickname> and hit enter\n\r");
		for (String room : this.dispatcher.getRooms())
			writer.println(room);
	}

	public void run() {
		String input;
		try {
			while ((input = reader.readLine()) != null) {
				logger.log(Level.INFO, "got input: " + input);
				if (!admin) {
					// business logic
					if (input.startsWith("join ")) {
						if (user.getCurrentRoom() != null) {
							dispatcher.roomInfo("user " + user.getName()
									+ " has left the room", user);
							sendMessage("autoleft room "
									+ user.getCurrentRoom(), user);
							user.setCurrentRoom(null);
						}
						String[] tokens = input.split(" ");

						if (tokens.length > 1) {
							String secondToken = tokens[1].trim();
							if (tokens[0].trim().equalsIgnoreCase("join")
									&& dispatcher.hasRoom(secondToken)) {
								if (tokens.length < 3)
									user.setName("Anonymous");
								else
									user.setName(tokens[2].trim());
								user.setCurrentRoom(secondToken);
								dispatcher.roomInfo(user.getName()
										+ " has joined the room", user);
								sendMessage("you have joined room "
										+ secondToken + "\n", user);
							}
						}
					} else if (input.startsWith("list channels"))
						printServerRooms();
					else if (input.startsWith("exit")) {
						dispatcher.roomInfo("user " + user.getName()
								+ " has left the room", user);
						sendMessage("you have left the room", user);
						user.setCurrentRoom(null);
					} else if (input.startsWith("quit")) {
						sendMessage("bye", user);
						break;
					} else if (input.startsWith("@{")) {
						String input2 = input.substring(2);
						String message = input
								.substring(input2.indexOf('}') + 3);

						String[] users = input2.split(",");
						dispatcher.sendToUsers(users, message, user);
					} else
						dispatcher.roomChat(input, user);
				} else {
					if(input.equalsIgnoreCase(dispatcher.getCurrentPassword()))
					{
						loggedIn = true;
						logger.log(Level.INFO, "admin logged in");						
					}
					if(!loggedIn)
					{
						sendMessage("incorrect password\n", user);
						logger.log(Level.INFO, "wrong admin passwd");						
						continue;
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
					sendMessage(msg,user);
					String[] tokens = input.split(" ");
					if(input.startsWith("passwd"))
					{
						dispatcher.setCurrentPassword(tokens[1].trim());
						logger.log(Level.INFO, "admin changed passwd to " + dispatcher.getCurrentPassword());
					}
					else if(input.startsWith("create"))
					{
						dispatcher.createRoom(tokens[1].trim());
						logger.log(Level.INFO, "admin created new room: " + tokens[1].trim());
					}
					else if(input.startsWith("rename"))
					{
						dispatcher.renameRoom(tokens[1].trim(), tokens[2].trim());
						logger.log(Level.INFO, "admin renamed room: " + tokens[1].trim() + " to: " + tokens[2].trim() );
					}
					else if(input.startsWith("delete"))
					{
						dispatcher.deleteRoom(tokens[1].trim());
						logger.log(Level.INFO, "admin deleted room: " + tokens[1].trim() + ", users are now lonely and sad");
					}
					else if(input.startsWith("logs"))
					{
						sendMessage(getLogs(), user);
						logger.log(Level.INFO, "admin requested log list");
					}
					
					//save on every admin command
					Persister p =  new Persister();
					p.setCurrentRooms(dispatcher.getRooms());
					p.setAdminPassword(dispatcher.getCurrentPassword());
					XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
							new FileOutputStream("database.xml")));
					e.writeObject(p);
					e.close();
				}
			}
			writer.close();
			reader.close();
			socket.close();
		} catch (Exception e) {
			sendMessage("Disconnecting. See you Later...", user);
			logger.log(Level.INFO, "exception caused client disconnect: " + e.getMessage());
		}
		dispatcher.removeClientHandler(this);
	}
	
	private String getLogs() throws IOException {
		Reader fileReader = null;
		try {
			fileReader = new FileReader("server.log");
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Log file missing");
		}

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		StringBuilder result = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line);
			result.append('\n');
			result.append('\r');
		}
		return result.toString();
	}

	public void sendMessage(String msg, User user) {
		try {
			writer.println(msg);
			logger.log(Level.INFO, "sent message to user");
		} catch (Exception e) {
			dispatcher.removeClientHandler(this);
			logger.log(Level.SEVERE, "unexpected client exit: "
					+ e.getMessage());
		}
	}

}
