package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dispatcher implements Runnable {

	private final Logger logger = Logger.getLogger (getClass().getName());
	private ArrayList<ClientHandler> clientHandlers;  
	private List<String> rooms;
	private Dispatcher nonadmin;
	private String currentPassword;
	private ServerSocket serverSocket;
	private int port;  
	private int clientCount;
	
	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public int getClientCount() {
		return clientCount;
	}

	public void setClientCount(int clientCount) {
		this.clientCount = clientCount;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


	public Dispatcher(int port, List<String> rooms) {
		init(port, rooms); 
		logger.log(Level.INFO, "starting chat server");
	}

	private void init(int port, List<String> rooms) {
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage() );
			System.exit(0);
		}
		clientCount = 0;
		clientHandlers = new ArrayList<ClientHandler>();
		this.rooms = rooms;
		
		new Thread(this).start();
	}

	public Dispatcher(int port, List<String> rooms,
			Dispatcher nonadmin, String currentPassword) {
		this.nonadmin = nonadmin;
		this.currentPassword = currentPassword;
		init(port, rooms);
		logger.log(Level.INFO, "starting admin server");
	}

	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				if(serverSocket.getLocalPort() == 3333)
					clientHandlers.add(new ClientHandler(socket, this, false));
				else if(serverSocket.getLocalPort() == 6666)
					clientHandlers.add(new ClientHandler(socket, this, true));
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}
	public void roomChat(String message, User user) {
		roomInfo(user.getName() + ": " + message, user);
	}
	
	public synchronized void removeClientHandler(ClientHandler removeMe) {
		String client = removeMe.getUser().getName();
		try {
			removeMe.getReader().close();
			removeMe.getWriter().close();
			removeMe.getSocket().close();
			for (ClientHandler handler : clientHandlers) {
				if (handler.equals(removeMe)) {
					clientHandlers.remove(removeMe);
				}
			}
			clientHandlers.trimToSize();
			clientCount--;
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage());
		}
		roomInfo(client + " has disconnected", removeMe.getUser());
	}

	public void roomInfo(String string, User user) {
		for (ClientHandler clientHandler : clientHandlers)
			if (!clientHandler.getUser().equals(user)) {
				if (clientHandler.getUser().getCurrentRoom() != null
						&& user.getCurrentRoom() != null)
					if (clientHandler.getUser().getCurrentRoom()
							.equalsIgnoreCase(user.getCurrentRoom()))
						clientHandler.sendMessage(string, user);
			}
	}

	public void incrementClients() {
		clientCount++;
	}

	public List<String> getRooms() {
		return rooms;
	}

	public synchronized boolean hasRoom(String exists) {
		for(String room : rooms)
			if(exists.equalsIgnoreCase(room))
				return true;
		return false;
	}

	public void sendToUsers(String[] users, String message, User user) {
		//TODO: attempts to send to one too many (last entry in users is "}message" should be removed)
		for (ClientHandler clientHandler : clientHandlers) {
			for (String userName : users)
				if (clientHandler.getUser().getName() != null && clientHandler.getUser().getName().equalsIgnoreCase(userName))
				{
					String username = user.getName() == null ? "anonymous user" : user.getName();
					clientHandler.sendMessage("whisper from " + username +": " + message, user);
				}
		}
	}

	public synchronized void createRoom(String newRoom) {
		if(!nonadmin.hasRoom(newRoom))
			rooms.add(newRoom);
	}

	public ArrayList<ClientHandler> getClientHandlers() {
		return clientHandlers;
	}

	public synchronized void deleteRoom(String deleteMe) {
		if (hasRoom(deleteMe))
			for (ClientHandler clientHandler : nonadmin.getClientHandlers()) {
				if(clientHandler.getUser() != null && clientHandler.getUser().getCurrentRoom() != null)
					if (clientHandler.getUser().getCurrentRoom().equalsIgnoreCase(deleteMe)) {
						nonadmin.roomClosedNotifcation(deleteMe);
					}
				rooms.remove(deleteMe.toUpperCase());
				rooms.remove(deleteMe.toLowerCase());
			}
	}

	private void roomClosedNotifcation(String deleteMe) {
		for (ClientHandler clientHandler : clientHandlers) {
			if (clientHandler.getUser() != null
					&& clientHandler.getUser().getCurrentRoom() != null
					&& clientHandler.getUser().getCurrentRoom()
							.equalsIgnoreCase(deleteMe))
			{
				clientHandler.sendMessage("**room closed you are now in the void", null);
				clientHandler.getUser().setCurrentRoom(null);
			}
		}
	}

	public synchronized void renameRoom(String oldRoomName, String newRoomName) {
		if (hasRoom(oldRoomName))
		for (ClientHandler clientHandler : nonadmin.getClientHandlers()) {
			if(clientHandler.getUser() != null && clientHandler.getUser().getCurrentRoom() != null)
				if (clientHandler.getUser().getCurrentRoom().equalsIgnoreCase(oldRoomName)) {
					clientHandler.getUser().setCurrentRoom(newRoomName);
					clientHandler.sendMessage("**room renamed by admin to: " + newRoomName , null);
				}
		}
		rooms.remove(oldRoomName.toUpperCase());
		rooms.remove(oldRoomName.toLowerCase());
		rooms.add(newRoomName);
	}

}