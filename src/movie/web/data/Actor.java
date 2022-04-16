package movie.web.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

/**
* 
* @author Alexandre GOMES DA COSTA <alexandre.gomes-da-costa@etu.cyu.fr> & Maxime HOCQUET <maxime.hocquet@etu.cyu.fr>
* 
*
*/

public class Actor { 

	private Integer actor_id;
	private String name;
	
	public Actor() {
	}
	
	/**
	 * 
	 * @param name Fait parti d'un constructeur de l'objet Actor avec son nom renseign� par name
	 *
	 */
	
	public Actor(String name) {
		actor_id = null ;
		this.name = name ;
	}
	
	/**
	 * @param actor_id Fait parti d'un constructeur de l'objet Actor avec son identifiant renseign� par actor_id
	 * @param name Fait parti d'un constructeur de l'objet Actor avec son nom renseign� par name
	 *
	 */

	public Actor(Integer actor_id, String name) {
		this.actor_id = actor_id ;
		this.name = name;
	}
	
	/**
	 * 
	 * @return Retourne l'identifiant de l'objet Actor sollicit�
	 *
	 */

	public Integer getActor_id() {
		return actor_id;
	}
	
	/**
	 * 
	 * @param actor_id Modifie l'identifiant de l'objet Actor sollicit� avec l'identifiant actor_id
	 * 
	 */

	public void setActor_id(Integer actor_id) {
		this.actor_id = actor_id;
	}
	
	/**
	 * 
	 * @return Retourne le nom de l'objet Actor sollicit�
	 *
	 */

	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name Modifie le nom de l'objet Actor sollicit� avec le nom name
	 * 
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Affiche l'objet Actor sollicit�
	 * 
	 */
	
	public String toString() {
		return "Actor [actor_id=" + actor_id + ", name=" + name + "]";
	}
		
}
