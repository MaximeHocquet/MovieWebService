package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.cxf.jaxrs.client.*;
import data.*;

public class Test {

	private static String webServiceUrl1 = "http://localhost:8080/movie.web/film/api/film";
	private static String webServiceUrl2 = "http://localhost:8080/movie.web/actor/api/actor";

	public static void main(String[] args) throws IOException {
		
		String namefilm1 = "Avengers" ;
		String namefilm2 = "Batman" ;		

		Film film1 = new Film(addFilm(namefilm1),namefilm1,getRanking(namefilm1),getActorList(namefilm1)); 
		Film film2 = new Film(addFilm(namefilm2),namefilm2,getRanking(namefilm2),getActorList(namefilm2));
		 
		getFilm(film1.getTitle());
		getFilm(film2.getTitle());

		deleteFilm(film2.getFilm_id());
	
		
		
		Map.Entry<Integer, Actor> entry1 = film1.getActors().entrySet().iterator().next();
		Map.Entry<Integer, Actor> entry2 = film2.getActors().entrySet().iterator().next();
		
		Actor acteur1 = entry1.getValue() ;
		Actor acteur2 = entry2.getValue() ;
		
		getActor(acteur1.getName());
		getActor(acteur2.getName());
		
		deleteActor(acteur2.getActor_id());
				
	}

	private static Integer addFilm(String name) throws IOException {
		
		System.out.print("\nAdding " + name + "... ");
		WebClient c = WebClient.create(webServiceUrl1);
		
		Float ranking = getRanking(name) ;
		HashMap<Integer,Actor> actorlist = getActorList(name) ;
		
		Film f = new Film(name, ranking, actorlist);
		
		Response r = c.post(f);
		
		if (r.getStatus() == 400) {
			System.out.println("Oops!");
			return null;
		}
		
		String uri = r.getHeaderString("Content-Location");
		System.out.println("OK.");
		
		return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
	}

	private static Boolean deleteFilm(Integer id) {
		
		System.out.print("Deleting " + id + "... ");
		WebClient c = WebClient.create(webServiceUrl1).path(id);
		int status = c.delete().getStatus();
		
		if (status == 200) {
			System.out.println("OK.");
			return true;
		}
		System.out.println("Oops!");
		return false;
	}

	private static Film getFilm(String title) {
		
		System.out.print("Getting " + title + "... ");
		WebClient c = WebClient.create(webServiceUrl1).path(title);
		Film f = null;
		
		try {
			f = c.get(Film.class);
			System.out.println(f.toString());
		} catch (NotFoundException e) {
			System.out.println("Oops!");
		}
		return f;
	}
	
	private static Integer addActor(String name) {
		System.out.print("Adding " + name + "... ");
		WebClient c = WebClient.create(webServiceUrl2);
		Actor a = new Actor(name);
		Response r = c.post(a);
		if (r.getStatus() == 400) {
			System.out.println("Oops!");
			return null;
		}
		String uri = r.getHeaderString("Content-Location");
		System.out.println("OK.");
		return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
	}

	private static Boolean deleteActor(Integer id) {
		System.out.print("Deleting " + id + "... ");
		WebClient c = WebClient.create(webServiceUrl2).path(id);
		int status = c.delete().getStatus();
		if (status == 200) {
			System.out.println("OK.");
			return true;
		}
		System.out.println("Oops!");
		return false;
	}

	private static Actor getActor(String name) {
		System.out.print("Getting " + name + "... ");
		WebClient c = WebClient.create(webServiceUrl2).path(name);
		Actor a = null;
		try {
			a = c.get(Actor.class);
			System.out.println(a.toString());
		} catch (NotFoundException e) {
			System.out.println("Oops!");
		}
		return a;
	}

	public static Float getRanking(String title_name) throws IOException {

		Float ranking = (float) -1;

		URL url = new URL("http://www.omdbapi.com/?apikey=c64d2da6&t=" + title_name + "&r=xml");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/xml");

		String contentType = con.getHeaderField("Content-Type");
		
	//	con.setConnectTimeout(5000);
	//	con.setReadTimeout(5000);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		in.close();

		con.disconnect();

		// System.out.println(content);

		String contentChar = content.toString();
		
		if ( !(contentChar.contains("<root response=\"False\">")) ) {

		 // System.out.println(contentChar);

			String[] beforeCutChar = contentChar.split("imdbRating=\"");
			String[] afterCutChar = beforeCutChar[1].split("\"");
	
		//	System.out.println(afterCutChar[0]);
	
			ranking = Float.valueOf(afterCutChar[0]);
		}

		return ranking;

	}
	
	public static HashMap<Integer,Actor> getActorList(String title_name) throws IOException {
		
		HashMap<Integer,Actor> actorlist = new HashMap<Integer, Actor>();

		URL url = new URL("http://www.omdbapi.com/?apikey=c64d2da6&t=" + title_name + "&r=xml");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/xml");
	
		String contentType = con.getHeaderField("Content-Type");
		
	//	con.setConnectTimeout(5000);
	//	con.setReadTimeout(5000);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		in.close();

		con.disconnect();

		String contentChar = content.toString();
		
		if ( !(contentChar.contains("<root response=\"False\">")) ) {

		// System.out.println(contentChar);

			String[] beforeCutChar = contentChar.split("actors=\"");
			String[] afterCutChar = beforeCutChar[1].split("\"");
	
		//	System.out.println(afterCutChar[0]);
			
			String[] actors = afterCutChar[0].split(", ") ;
			
			for (int i=0;i<actors.length;i++) {
				
				Actor acteur = new Actor(addActor(actors[i]),actors[i]);
				actorlist.put(acteur.getActor_id(),acteur);
				
			}
	
		}

		return actorlist;

	}
	
}