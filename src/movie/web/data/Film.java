package movie.web.data;

import java.util.HashMap;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

/**
* 
* @author Alexandre GOMES DA COSTA <alexandre.gomes-da-costa@etu.cyu.fr> & Maxime HOCQUET <maxime.hocquet@etu.cyu.fr>
*
*/

public class Film {
	
	private Integer film_id ;
	private String title ;
	private Float ranking ;
	private HashMap<Integer, Actor> actors ;
	
	public Film() {
	}
	
	/**
	 * 
	 * @param film_id Fait parti d'un constructeur de l'objet Film avec son identifiant renseign� par film_id
	 *
	 */
	
	public Film(Integer film_id) {
		this.film_id = film_id ;
		title = null ;		
		ranking = null ;
		actors = null ;
	}
	
	/**
	 * 
	 * @param title Fait parti d'un constructeur de l'objet Film avec son nom renseign� par title
	 *
	 */
	
	public Film(String title) {
		film_id = null ;
		this.title = title ;		
		ranking = null ;
		actors = null ;
	}
	
	/**
	 * 
	 * @param film_id Fait parti d'un constructeur de l'objet Film avec son identifiant renseign� par film_id
	 * @param title Fait parti d'un constructeur de l'objet Film avec son nom renseign� par title
	 *
	 */
	
	public Film(Integer film_id, String title) {
		this.film_id = film_id ;
		this.title = title ;		
		ranking = null ;
		actors = null ;
	}
	
	/**
	 * 
	 * @param title Fait parti d'un constructeur de l'objet Film avec son nom renseign� par title
	 * @param ranking Fait parti d'un constructeur de l'objet Film avec sa note renseign� par ranking
	 *
	 */
	
	public Film(String title, Float ranking) {
		film_id = null ;
		this.title = title ;		
		this.ranking = ranking ;
		actors = null ;
	}
	
	/**
	 * 
	 * @param title Fait parti d'un constructeur de l'objet Film avec son nom renseign� par title
	 * @param ranking Fait parti d'un constructeur de l'objet Film avec sa note renseign� par ranking
	 * @param actors Fait parti d'un constructeur de l'objet Film avec sa liste d'acteurs renseign� par actors
	 *
	 */
	
	public Film(String title, Float ranking, HashMap<Integer, Actor> actors) {
		film_id = null ;
		this.title = title;
		this.ranking = ranking;
		this.actors = actors;
	}
	
	/**
	 * 
	 * @param film_id Fait parti d'un constructeur de l'objet Film avec son identifiant renseign� par film_id
	 * @param title Fait parti d'un constructeur de l'objet Film avec son nom renseign� par title
	 * @param ranking Fait parti d'un constructeur de l'objet Film avec sa note renseign� par ranking
	 * @param actors Fait parti d'un constructeur de l'objet Film avec sa liste d'acteurs renseign� par actors
	 *
	 */

	public Film(Integer film_id, String title, Float ranking, HashMap<Integer, Actor> actors) {
		this.film_id = film_id;
		this.title = title;
		this.ranking = ranking;
		this.actors = actors;
	}
	
	/**
	 * 
	 * @return Retourne l'identifiant de l'objet Film sollicit�
	 *
	 */

	public Integer getFilm_id() {
		return film_id;
	}
	
	/**
	 * 
	 * @param film_id Modifie l'identifiant de l'objet Film sollicit� avec l'identifiant film_id
	 * 
	 */
	
	public void setFilm_id(Integer film_id) {
		this.film_id = film_id;
	}
	
	/**
	 * 
	 * @return Retourne le nom de l'objet Film sollicit�
	 *
	 */
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * 
	 * @param title Modifie le nom de l'objet Film sollicit� avec le nom title
	 * 
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 
	 * @return Retourne la note de l'objet Film sollicit�
	 *
	 */
	
	public Float getRanking() {
		return ranking;
	}
	
	/**
	 * 
	 * @param ranking Modifie la note de l'objet Film sollicit� avec la note ranking
	 * 
	 */
	
	public void setRanking(Float ranking) {
		this.ranking = ranking;
	}
	
	/**
	 * 
	 * @return Retourne la liste d'acteurs l'objet Film sollicit�
	 *
	 */

	public HashMap<Integer, Actor> getActors() {
		return actors;
	}
	
	/**
	 * 
	 * @param actors Modifie la liste d'acteurs de l'objet Film sollicit� avec la liste d'acteurs actors
	 * 
	 */

	public void setActors(HashMap<Integer, Actor> actors) {
		this.actors = actors;
	}

	/**
	 * 
	 * @return Affiche l'objet Film sollicit�
	 * 
	 */
	
	public String toString() {
		return "Film [film_id=" + film_id + ", title=" + title + ", ranking=" + ranking + ", actors=" + actors + "]";
	}

}