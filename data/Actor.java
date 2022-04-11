package project.data;

public class Actor {
	private int id_actor;
	private String nom_acteur;
	
	public Actor() {}
	public Actor(int id_actor, String nom_acteur) {
		this.id_actor = id_actor;
		this.nom_acteur = nom_acteur;
	}
	public int getId_actor() {
		return id_actor;
	}

	public void setId_actor(int id_actor) {
		this.id_actor = id_actor;
	}

	public String getNom_acteur() {
		return nom_acteur;
	}

	public void setNom_acteur(String nom_acteur) {
		this.nom_acteur = nom_acteur;
	}

}
