package server;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Server {
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

		Dispatcher nonadmin = new Dispatcher(3333, p.getCurrentRooms());
		new Dispatcher(6666, p.getCurrentRooms(), nonadmin, p.getAdminPassword());
	}
}