package project.service;

import project.connexion.ConnectPostgreSQL;
import project.data.User;

public class UserService {
	  ConnectPostgreSQL connect = new ConnectPostgreSQL("localhost","5432","moviesdb","maxime","gHIAw3,N");

	private int getNewId() { 
		int newId = 0; 
		
		return ++newId; 
			
	} 
	public User addStudent(User u) {
		int id = getNewId();
		int insert_effectue = connect.update("INSERT INTO utilisateur VALUES ("+id+",'"+u.getName()+"')","utilisateur");
		if(insert_effectue == 0) {
			System.out.println("Problem add user");
			return null;
		}
		return u;
	} 
	public boolean deleteStudent(int id) {
		int delete_effectue = connect.update("DELETE FROM utilisateur WHERE id = '"+id+"'","utilisateur");
		if(delete_effectue == 0) {
			System.out.println("Problem delete user");
			return false;
		}
		return true;
	} 
	public User getUser(int id, String name) {
		User u = null;
		String query = connect.query("SELECT * FROM utilisateur WHERE id='"+id+"'", "utilisateur");
		if(query=="") {
			System.out.println("L'identifiant n'existe pas");
			return u;
		}
		else {
			u = new User(id, name);
			return u;
		}
	}
}
