package data;

import java.util.HashMap;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Film {
	
	private Integer film_id ;
	private String title ;
	private Float ranking ;
	private HashMap<Integer, Actor> actors ;
	
	public Film() {
	}
	
	public Film(Integer film_id) {
		this.film_id = film_id ;
		title = null ;		
		ranking = null ;
		actors = null ;
	}
	
	public Film(String title) {
		film_id = null ;
		this.title = title ;		
		ranking = null ;
		actors = null ;
	}
	
	public Film(Integer film_id, String title) {
		this.film_id = film_id ;
		this.title = title ;		
		ranking = null ;
		actors = null ;
	}
	
	public Film(String title, Float ranking) {
		film_id = null ;
		this.title = title ;		
		this.ranking = ranking ;
		actors = null ;
	}

	public Film(Integer film_id, String title, Float ranking, HashMap<Integer, Actor> actors) {
		this.film_id = film_id;
		this.title = title;
		this.ranking = ranking;
		this.actors = actors;
	}
	
	public Film(String title, Float ranking, HashMap<Integer, Actor> actors) {
		film_id = null ;
		this.title = title;
		this.ranking = ranking;
		this.actors = actors;
	}

	public Integer getFilm_id() {
		return film_id;
	}
	
	public void setFilm_id(Integer film_id) {
		this.film_id = film_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Float getRanking() {
		return ranking;
	}
	
	public void setRanking(Float ranking) {
		this.ranking = ranking;
	}

	public HashMap<Integer, Actor> getActors() {
		return actors;
	}

	public void setActors(HashMap<Integer, Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Film [film_id=" + film_id + ", title=" + title + ", ranking=" + ranking + ", actors=" + actors + "]";
	}

}