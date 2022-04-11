package service; 

import java.util.* ;
import data.Film ;

public class FilmService {
	
	private static Map<Integer, Film> FILM_DATA = new HashMap<Integer, Film>();

	private int getNewId() {
		
		int newId = 0;
		
		for (int id : FILM_DATA.keySet()) {
			
			if (newId < id) {
				newId = id;
			}
		}
		
		return ++newId;
	}

	public Film addFilm(Film f) {
		
		int id = getNewId();
		
		if (FILM_DATA.get(f.getFilm_id()) != null) {
			return null;
		}
		
		f.setFilm_id(id);
		FILM_DATA.put(id, f);
		return f;
	}

	public boolean deleteFilm(int id) {
		
		if (FILM_DATA.get(id) == null) {
			return false;
		}
		
		FILM_DATA.remove(id);
		return true;
	}

	public Film getFilm(int id) {
		return FILM_DATA.get(id);
	}
}