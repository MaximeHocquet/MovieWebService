package project.connexion;

import java.net.ServerSocket ;
import java.net.Socket ;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.io.PrintWriter ;

public class ServeurTCP {
	//private static String nom_utilisateur = null;
	private static String id_compte = null;
	private static String id_compte_percoit = null;

    public static void main (String argv []) throws IOException {
        ServerSocket serverSocket = null ;
        serverSocket = new ServerSocket (5000) ;
        
        //connexion base de donnee
        ConnectPostgreSQL connect = new ConnectPostgreSQL("localhost","5432","db_banque","maxime","gHIAw3,N");

        Socket clientSocket = null ;
        try {
            clientSocket = serverSocket.accept () ;
        } 
        catch (IOException e) {
            System.err.println ("Accept echoue.") ;
            System.exit (1) ;
        }

        PrintWriter flux_sortie = new PrintWriter (clientSocket.getOutputStream (), true) ;
        //flux_sortie.println ("Quel est votre identifiant? Taper -h si besoin d'aide");
        
        BufferedReader flux_entree = new BufferedReader (
                                new InputStreamReader (
                                clientSocket.getInputStream ())) ;

        String chaine_entree;
        //flux_sortie.println ("Quel est votre identifiant?");
        
        while ( (chaine_entree = flux_entree.readLine ()) != null) {
        	
        	String[] chaine_split = chaine_entree.split(" ");
        	String type_commande = chaine_split[0];
        	int taille_commande =  chaine_split.length;
             
			//quitter 	
			if (type_commande.equals ("-q")) {
				break ;
			}
			//aide
			else if(type_commande.equals("-h")) {
				String aff = "Les commandes possibles: --"
						+ "-h : aide. --"
						+ "-i identifiant_utilisateur : s'identifier à l'aide de votre identifiant. --"
						+ "-a id_compte : acces a un compte bancaire via son id. --"
						+ "-s : acces au solde d'un compte bancaire. --"
						+ "-d montant_depot : transaction de type depot. --"
						+ "-r montant_retrait : transaction de type retrait. --"
						+ "-t montant_transfert id_compte_percoit : transaction de type transfert entre comptes. --"
						+ "-deco : deconnecte du compte. --"
						+ "-q : quitter le programme.";
				flux_sortie.println(aff);      			
			}
			//identifiaction
			else if( type_commande.equals("-i") && taille_commande == 2) {
				
				String query = connect.query("SELECT * FROM se_connecter WHERE nom='" + chaine_split[1] +"'", "connexion");
				if(query=="") {
					String str = "L'identifiant n'existe pas";
					flux_sortie.println(str);
				}
				else {
				//System.out.println(query);

				String[] query_split = query.split(" ");
				
				String str = "Bonjour " + query_split[0] + " vous êtes un " + query_split[2];
				flux_sortie.println(str);
				}
			}
			//identification compte bancaire
			else if( type_commande.equals("-a")  && taille_commande == 2) {
				String query = connect.query("SELECT * FROM compte WHERE nom_compte='" + chaine_split[1] +"'", "compte");
				if(query=="") {
					String str = "Le compte n'existe pas";
					flux_sortie.println(str);
				}
				else {
				
				
					String[] query_split = query.split(" ");
					String str = "Vous etes bien sur votre compte " + query_split[0] ;
					id_compte = query_split[0];
					flux_sortie.println(str);
				}
				
			}
			//solde d'un compte bancaire
			else if( type_commande.equals("-s") && taille_commande == 1) {
				if(id_compte == null) {
					flux_sortie.println ("Quel est l'identifiant de votre compte bancaire? Si besoin d'aide taper -h") ;
				}else {
					String query = connect.query("SELECT * FROM compte WHERE nom_compte='"+id_compte+"'", "compte");
					String[] query_split = query.split(" ");
					String str = "Votre solde est de  " + query_split[6] ;

					flux_sortie.println(str);
				}
			}
			//depot d'argent
			else if( type_commande.equals("-d") && taille_commande == 2) {
				 if(id_compte==null) {
				   	flux_sortie.println ("Quel est l'identifiant de votre compte bancaire? Si besoin d'aide taper -h") ;
				  }
				 else {
					 try {
					 double montant_depot = Double.valueOf(chaine_split[1]);
						int id_depot = connect.getLenghtTable("SELECT id_transaction FROM transactions")+1;
						//System.out.println(id_depot);
				 
				String str ="";
				
					if(montant_depot<=0) {
						str = "Valeur de depot doit etre superieur a 0";
					}
					
					else {
					
						//SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");  
						String pattern = "yyyy-MM-dd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						String date = simpleDateFormat.format(new Date());
					    int insert_effectue = connect.update("INSERT INTO transactions VALUES ("+id_depot+",'"+id_compte+"','"+date+"','depot')", "transactions");
					   
					  
						if(insert_effectue == 0) {
							str = "Probleme lors de la transaction";
						}
						//inserer le type de transaction 
						insert_effectue = connect.update("INSERT INTO depot_argent VALUES ("+id_depot+","+montant_depot+")", "depot");
						if(insert_effectue == 0) {
							str = "Probleme lors du depot d'argent";
						}
			
						//effectuer le depot dans la table compte sur le solde 
						insert_effectue = connect.update("UPDATE compte set solde = solde + "+montant_depot+" WHERE nom_compte='"+id_compte+"'", "compte");
						if(insert_effectue == 0) {
							str = "Probleme lors du depot d'argent";
						}
								
						//recuper la valeur du solde
						String query = connect.query("SELECT * FROM compte WHERE nom_compte='"+id_compte+"'", "compte");
						String[] query_split = query.split(" ");
						 str = "Votre solde est de  " + query_split[6] ;

					}
				flux_sortie.println (str) ;
				 }
				 catch(NumberFormatException e){
						 
					 flux_sortie.println ("Valeur de depot doit etre un  nombre entier");
					 }
				   }
				 
				
			}
			//retrait d'argent
			else if( type_commande.equals("-r") && taille_commande == 2) {
				 if(id_compte==null) {
					flux_sortie.println ("Quel est l'identifiant de votre compte bancaire? Si besoin d'aide taper -h") ;
				 }
				 else {
					 try {
					double montant_retrait = Double.valueOf(chaine_split[1]);
					int id_retrait = connect.getLenghtTable("SELECT id_transaction FROM transactions")+1;
					String str ="";
					
					if(montant_retrait<=0) {
						str = "Valeur de retrait doit etre superieur a 0";
					}
					else {
						String pattern = "yyyy-MM-dd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						String date = simpleDateFormat.format(new Date());
					    
						int insert_effectue = connect.update("INSERT INTO transactions VALUES ("+id_retrait+",'"+id_compte+"','"+date+"','retrait')", "transactions");
						if(insert_effectue == 0) {
							str = "Probleme lors de la transaction";
						}
						//inserer le type de transaction 
						insert_effectue = connect.update("INSERT INTO retrait_argent VALUES ("+id_retrait+","+montant_retrait+")", "retrait");
						if(insert_effectue == 0) {
							str = "Probleme lors du retrait d'argent";
						}
			
						//effectuer le retrait dans la table compte sur le solde 
						insert_effectue = connect.update("UPDATE compte set solde = solde - "+montant_retrait+" WHERE nom_compte='"+id_compte+"'", "compte");
						if(insert_effectue == 0) {
							str = "Probleme lors du retrait d'argent";
						}
								
						//recuper la valeur du solde
						String query = connect.query("SELECT * FROM compte WHERE nom_compte='"+id_compte+"'", "compte");
						String[] query_split = query.split(" ");
						 str = "Votre solde est de  " + query_split[6] ;
						
					}
					flux_sortie.println (str) ;
					 }catch(NumberFormatException e){
						 
					 flux_sortie.println ("Valeur de retrait doit etre un  nombre entier");
					 }
				 }
			}
				 
			//transaction
			else if( type_commande.equals("-t")  && taille_commande == 3) {
				if(id_compte==null) {
					flux_sortie.println ("Quel est l'identifiant de votre compte bancaire? Si besoin d'aide taper -h") ;
				 }
				else {
					try {
					double montant_retrait = Double.valueOf(chaine_split[1]);
					int id_transfert = connect.getLenghtTable("SELECT id_transaction FROM transactions")+1;
					String str ="";
					if(montant_retrait<=0) {
						str = "Valeur de transfert doit etre superieur a 0";
					}
					else {
					
					//verifaction existance compte percevant l'argent
					String query = connect.query("SELECT * FROM compte WHERE nom_compte='"+chaine_split[2]+"'", "compte");
					String[] query_split = query.split(" ");
					id_compte_percoit = query_split[0];
					 if(query_split[0]==null) {
						str = "Le compte n'existe pas";
					}
					else {
						String pattern = "yyyy-MM-dd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						String date = simpleDateFormat.format(new Date());
					    
					    
					    // inserer la transaction effectuer
						int insert_effectue = connect.update("INSERT INTO transactions VALUES ("+id_transfert+",'"+id_compte+"','"+date+"','transfert')", "transfert");
						if(insert_effectue == 0) {
							str = "Probleme lors de la transaction";
						}
						//inserer le type de transaction 
						insert_effectue = connect.update("INSERT INTO transfert_argent VALUES ("+id_transfert+",'"+id_compte+"','"+id_compte_percoit+"','null',"+montant_retrait+")", "transfert");
						if(insert_effectue == 0) {
							str = "Probleme lors du transfert d'argent";
						}
			
						//effectuer le retrait dans la table compte sur le solde de celui qui envois
						insert_effectue = connect.update("UPDATE compte set solde = solde - "+montant_retrait+" WHERE nom_compte='"+id_compte+"'", "compte");
						if(insert_effectue == 0) {
							str = "Probleme lors du transfert d'argent";
						}
						
						//effectuer le depot dans la table compte sur le solde de celui qui recois
						insert_effectue = connect.update("UPDATE compte set solde = solde + "+montant_retrait+" WHERE nom_compte='"+id_compte_percoit+"'", "compte");
						if(insert_effectue == 0) {
							str = "Probleme lors du transfert d'argent";
						}
								
						//recuper la valeur du solde
						String query1 = connect.query("SELECT * FROM compte WHERE nom_compte='"+id_compte+"'", "compte");
						String[] query_split1 = query1.split(" ");
						 str = "Votre solde est de  " + query_split1[6] ;
						
					}
					}
					flux_sortie.println (str) ;
					
					}catch(NumberFormatException e){
						 
					 flux_sortie.println ("Valeur de retrait doit etre un  nombre entier");
					 }
				}
			}
			else if( type_commande.equals("-deco") && taille_commande == 1) {
				id_compte = null;
				flux_sortie.println ("Vous vous etes deconnecter de votre compte. Si besoin d'aide taper -h") ;
				
			}
            else{
                flux_sortie.println ("Taper -h pour toute aide.") ;
            }
        }
        flux_sortie.close () ;
        flux_entree.close () ;
        clientSocket.close () ;
        serverSocket.close () ;
    }
}