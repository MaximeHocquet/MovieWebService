package project.data;

public class Film {
	private int id_film;
	private String titre;
	
	public Film() {}
	public Film(int id_film, String titre) {
		this.id_film = id_film;
		this.titre = titre;
	}
	public Integer getId_film() {
		return id_film;
	}

	public void setId_film(Integer id_film) {
		this.id_film = id_film;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}
