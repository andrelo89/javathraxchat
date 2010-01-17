/**
 * 
 */
package server;

import java.util.List;

public class Persister {
	private List<String> currentRooms;
	private String adminPassword;
	public Persister(){
	}
	public List<String> getCurrentRooms() {
		return currentRooms;
	}
	public void setCurrentRooms(List<String> currentRooms) {
		this.currentRooms = currentRooms;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}