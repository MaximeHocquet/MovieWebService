package project.service;

import java.util.HashMap;
import java.util.Map;

import project.connexion.ConnectPostgreSQL;
import project.data.User;

public class UserService {
	  ConnectPostgreSQL connect = new ConnectPostgreSQL("localhost","5432","db_banque","maxime","gHIAw3,N");

	private int getNewId() { 
		int newId = 0; 
		for (int id : STUDENT_DATA.keySet()) {
			if (newId < id) newId = id; 
		} 
		return ++newId; 
			
	} 
	public User addStudent(User s) {
		int id = getNewId();
		int insert_effectue = connect.update("INSERT INTO users VALUES ("+id+",'"+s.getName()+"')","user");
		if(insert_effectue == 0) {
			System.out.println("Probleme add user");
		}
		if(STUDENT_DATA.get(s.getId()) != null) { return null; } 
		s.setId(id);
		STUDENT_DATA.put(id, s); return s;
	} 
	public boolean deleteStudent(int id) {
		if(STUDENT_DATA.get(id) == null) {
			return false; 
			} 
		STUDENT_DATA.remove(id); 
		return true;
		} 
	public Student getStudent(int id) { 
		return STUDENT_DATA.get(id); 
		} 
	}
}
