# MovieWebService

Members:
Alexandre GOMES DA COSTA alexandre.gomes-da-costa@etu.cyu.fr
Maxime HOCQUET maxime.hocquet@etu.cyu.fr

This academic project has been made in order to create a movie web service that interact with the OMDB REST API 
- https://www.omdbapi.com

Project's configuration
Eclipse IDE 2019, Apache CXF 2.7.18, Apache Tomcat 9.0.58, JDK 1.8

Project's description:
By using this project that use the OMDB API, you can perform diffenrent actions for films and their actors. The project work locally so you will maybe have to set yours settings according to the project's configuration. You will also need to create 2 project on Eclipse :
- (Service) a new Dynamic web project in which you import the package (and Classes) "movie.web.data", "movie.web.resource", "movie.web.service". Modify the web.xml file too by the provide one. (the web.xml file is located in the "WebContent/WEB-INF" directory of your dynamic web project)
- (Client) a new Java Project in which you import the package (and Class) "movie.web.test"
JavaDoc can be generated

Service’s description:
Service permits to add new film/actor, delete film/actor and get film/actor. You can access your film or actor by using :
- http://localhost:8080/movie.web/actor/api/actor/{name of your actor}
- http://localhost:8080/movie.web/film/api/film/{name of your film}

Client's description:
Client provide the name of the film or actor that the user want to be add, delete and get or the service by interacting with the OMDB API and can also provide film's or actor's informations. The client also add automatically the actors if the request of the user is to add a film.

Exemple de classe Test (provided in the class Test) :

public static void main(String[] args) throws IOException {
		
	String namefilm1 = "Avengers" ;
	String namefilm2 = "Batman" ;		

	Film film1 = new Film(addFilm(namefilm1),namefilm1,getRanking(namefilm1),getActorList(namefilm1)); 
	Film film2 = new Film(addFilm(namefilm2),namefilm2,getRanking(namefilm2),getActorList(namefilm2));
		 
	getFilm(film1.getTitle());
	getFilm(film2.getTitle());

	deleteFilm(film2.getFilm_id());		
		
	Map.Entry<Integer, Actor> entry1 = film1.getActors().entrySet().iterator().next();
	Map.Entry<Integer, Actor> entry2 = film2.getActors().entrySet().iterator().next();
		
	Actor acteur1 = entry1.getValue() ;
	Actor acteur2 = entry2.getValue() ;
		
	getActor(acteur1.getName());
	getActor(acteur2.getName());
		
	deleteActor(acteur2.getActor_id());
				
}
