package data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Actor { 

	private Integer actor_id;
	private String name;
	
	public Actor() {
	}
	
	public Actor(String name) {
		actor_id = null ;
		this.name = name ;
	}

	public Actor(Integer actor_id, String name) {
		this.actor_id = actor_id ;
		this.name = name;
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
