package service; 

import java.util.* ;
import data.Actor ;

public class ActorService {
	
	private static Map<Integer, Actor> ACTOR_DATA = new HashMap<Integer, Actor>();

	private int getNewId() {
		
		int newId = 0;
		
		for (int id : ACTOR_DATA.keySet()) {
			
			if (newId < id) {
				newId = id;
			}
		}
		
		return ++newId;
	}

	public Actor addActor(Actor a) {
		
		int id = getNewId();
		
		if (ACTOR_DATA.get(a.getActor_id()) != null) {
			return null;
		}
		
		a.setActor_id(id);
		ACTOR_DATA.put(id, a);
		return a;
	}

	public boolean deleteActor(int id) {
		
		if (ACTOR_DATA.get(id) == null) {
			return false;
		}
		
		ACTOR_DATA.remove(id);
		return true;
	}

	public Actor getActor(int id) {
		return ACTOR_DATA.get(id);
	}
}