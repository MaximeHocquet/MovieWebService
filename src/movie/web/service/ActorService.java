package movie.web.service; 

import java.util.* ;
import java.util.Map.Entry;

import movie.web.data.Actor;

/**
* 
* @author Alexandre GOMES DA COSTA <alexandre.gomes-da-costa@etu.cyu.fr> & Maxime HOCQUET <maxime.hocquet@etu.cyu.fr>
*
*/

public class ActorService {
	
	private static HashMap<Integer, Actor> ACTOR_DATA = new HashMap<Integer, Actor>();
	
	/**
	* 
	* @return L'identifiant pour un nouvel objet Actor soit l'identifiant du dernier + 1 (ou retourne 1 si 1er acteur ajouté) 
	*
	*/

	private int getNewId() {
		
		int newId = 0;
		
		for (int id : ACTOR_DATA.keySet()) {
			
			if (newId < id) {
				newId = id;
			}
		}
		
		return ++newId;
	}
	
	/**
	* 
	* @param a L'acteur que l'on souhaite ajouter au service
	* @return L'acteur qui sera ajouté (avec son identifiant éventuellement mis à jour)
	*
	*/

	public Actor addActor(Actor a) {
		
		int id = getNewId();
		
		if (ACTOR_DATA.get(a.getActor_id()) != null) {
			return null;
		}
		
		a.setActor_id(id);
		ACTOR_DATA.put(id, a);
		return a;
	}
	
	/**
	* 
	* @param id L'identifiant de l'acteur que l'on souhaite supprimer du service
	* @return true si l'acteur a été supprimé ou sinon false 
	*
	*/

	public boolean deleteActor(int id) {
		
		if (ACTOR_DATA.get(id) == null) {
			return false;
		}
		
		ACTOR_DATA.remove(id);
		return true;
	}
	
	/**
	* 
	* @param name Le nom de l'acteur que l'on souhaite récupérer à partir du service
	* @return l'acteur correspondant au nom donné par name (ou sinon null car aucun acteur n'a un identifiant de 0) 
	*
	*/

	public Actor getActor(String name) {
		
		int id = 0 ;
		
		/* Recherche de l'acteur renseigné par name */

		for (Entry<Integer, Actor> entry : ACTOR_DATA.entrySet()) {

			if (entry.getValue().getName().equals(name)) {
				id = entry.getKey() ;
				break;
			}
		}

		return ACTOR_DATA.get(id);
	}
}