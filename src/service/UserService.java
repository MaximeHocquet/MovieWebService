package service;

import connexion.ConnectPostgreSQL;

import data.*;


public class UserService {
	  ConnectPostgreSQL connect = new ConnectPostgreSQL("localhost","5432","moviesdb","maxime","gHIAw3,N");

	private int getNewId() { 
		int newId = 0; 
		String query = connect.query("SELECT MAX(id) FROM utilisateur", "utilisateur");
		if(query=="") {
			System.out.println("L'identifiant n'existe pas");
			return newId;
		}
		else {
			newId = Integer.valueOf(query);
			return  ++newId;
		}		
	} 
	public User addUser(User u) {
		int id = getNewId();
		int insert_effectue = connect.update("INSERT INTO utilisateur VALUES ("+id+",'"+u.getName()+"')","utilisateur");
		if(insert_effectue == 0) {
			System.out.println("Problem add user");
			return null;
		}
		return u;
	} 
	public boolean deleteUser(int id) {
		int delete_effectue = connect.update("DELETE FROM utilisateur WHERE id = '"+id+"'","utilisateur");
		if(delete_effectue == 0) {
			System.out.println("Problem delete user");
			return false;
		}
		return true;
	} 
	public User getUser(int id) {
		User u = null;
		String query = connect.query("SELECT * FROM utilisateur WHERE id='"+id+"'", "utilisateur");
		if(query=="") {
			System.out.println("L'identifiant n'existe pas");
			return u;
		}
		else {
			String[] query_split = query.split(" ");
			u = new User(Integer.valueOf(query_split[0]), query_split[1]);
			return u;
		}
	}
	public Film addFilm(Film f) {
		int id = getNewId();
		int insert_effectue = connect.update("INSERT INTO film VALUES ("+id+",'"+f.getTitle()+"')","film");
		if(insert_effectue == 0) {
			System.out.println("Problem add film");
			return null;
		}
		return f;
	} 
	public Actor addActor(Actor a) {
		int id = getNewId();
		int insert_effectue = connect.update("INSERT INTO acteur VALUES ("+id+",'"+a.getName()+"')","acteur");
		if(insert_effectue == 0) {
			System.out.println("Problem add film");
			return null;
		}
		return a;
	}
	public Film getFilm(int id) {
		Film f = null;
		String query = connect.query("SELECT * FROM film WHERE id_film='"+id+"'", "film");
		if(query=="") {
			System.out.println("L'identifiant n'existe pas");
			return f;
		}
		else {
			String[] query_split = query.split(" ");
			f = new Film(Integer.valueOf(query_split[0]), query_split[1]);
			return f;
		}
	}
	public Actor getActor(int id) {
		Actor a = null;
		String query = connect.query("SELECT * FROM acteur WHERE id='"+id+"'", "acteur");
		if(query=="") {
			System.out.println("L'identifiant n'existe pas");
			return a;
		}
		else {
			String[] query_split = query.split(" ");
			a = new Actor(Integer.valueOf(query_split[0]), query_split[1]);
			return a;
		}
	}
}
