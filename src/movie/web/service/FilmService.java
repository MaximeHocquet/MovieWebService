package movie.web.service;

import java.util.*;
import java.util.Map.Entry;

import movie.web.data.Film;

/**
* 
* @author Alexandre GOMES DA COSTA <alexandre.gomes-da-costa@etu.cyu.fr> & Maxime HOCQUET <maxime.hocquet@etu.cyu.fr>
*
*/

public class FilmService {

	private static HashMap<Integer, Film> FILM_DATA = new HashMap<Integer, Film>();

	/**
	* 
	* @return L'identifiant pour un nouvel objet Film soit l'identifiant du dernier + 1 (ou retourne 1 si 1er film ajouté) 
	*
	*/
	
	private int getNewId() {

		int newId = 0;

		for (int id : FILM_DATA.keySet()) {

			if (newId < id) {
				newId = id;
			}
		}

		return ++newId;
	}
	
	/**
	* 
	* @param f Le film que l'on souhaite ajouter au service
	* @return Le film qui sera ajouté (avec son identifiant éventuellement mis à jour)
	*
	*/

	public Film addFilm(Film f) {

		int id = getNewId();

		if (FILM_DATA.get(f.getFilm_id()) != null) {
			return null;
		}

		f.setFilm_id(id);
		FILM_DATA.put(id, f);
		return f;
	}
	
	/**
	* 
	* @param id L'identifiant du film que l'on souhaite supprimer du service
	* @return true si le film a été supprimé ou sinon false 
	*
	*/

	public boolean deleteFilm(int id) {

		if (FILM_DATA.get(id) == null) {
			return false;
		}

		FILM_DATA.remove(id);
		return true;
	}
	
	/**
	* 
	* @param title Le nom du film que l'on souhaite récupérer à partir du service
	* @return le film correspondant au nom donné par title (ou sinon null car aucun film n'a un identifiant de 0) 
	*
	*/

	public Film getFilm(String title) {
		
		int id = 0 ;
		
		/* Recherche du film renseigné par name */

		for (Entry<Integer, Film> entry : FILM_DATA.entrySet()) {

			if (entry.getValue().getTitle().equals(title)) {
				id = entry.getKey() ;
				break;
			}
		}

		return FILM_DATA.get(id);
	}
}