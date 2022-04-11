package data;

import java.util.ArrayList;

public class Film {
	
	private Integer film_id ;
	private String title ;
	private Integer ranking ;
	private ArrayList <Actor> actors ;
	
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
	
	public Integer getRanking() {
		return ranking;
	}
	
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Film [film_id=" + film_id + ", title=" + title + ", ranking=" + ranking + ", actors=" + actors + "]";
	}

}