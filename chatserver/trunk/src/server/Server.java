package server;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Server {
	public static final int ADMIN_LISTEN_PORT = 6666;
	public static final int LISTEN_PORT = 3333;
	public static final int MAX_CLIENTS = 1000;
	public static final String TEXT_ENCODING = "UTF-8";
	
	private static List<String> rooms;
	public static void main(String[] args) {

		Persister p = null;
		try {
			XMLDecoder d = null;
			d = new XMLDecoder(new BufferedInputStream(new FileInputStream(
					"database.xml")));
			p = (Persister) d.readObject();
			d.close();
		} catch (FileNotFoundException e) {
			System.out.println("database file not found. using default rooms and admin password");
			rooms = new ArrayList<String>();
			rooms.add("A");
			rooms.add("B");
			rooms.add("C");
			p = new Persister();
			p.setCurrentRooms(rooms);
			p.setAdminPassword("admin");
		}

		Dispatcher nonadmin = new Dispatcher(LISTEN_PORT, p.getCurrentRooms());
		new Dispatcher(ADMIN_LISTEN_PORT, p.getCurrentRooms(), nonadmin, p.getAdminPassword());
	}
}