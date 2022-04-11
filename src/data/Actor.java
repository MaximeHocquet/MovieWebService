package data;

public class Actor { 

	private Integer actor_id;
	private String name;

	public Actor(Integer id, String nom) {
		actor_id = id ;
		name = nom ;
	}

	public Integer getActor_id() {
		return actor_id;
	}

	public void setActor_id(Integer actor_id) {
		this.actor_id = actor_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Actor [actor_id=" + actor_id + ", name=" + name + "]";
	}
	
}
