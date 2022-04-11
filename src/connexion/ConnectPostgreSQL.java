package connexion ;
import java.sql.*;

public class ConnectPostgreSQL
{
	String server;
	String port;
	String db;
	String user;
	String password;
	
	public ConnectPostgreSQL(String server, String port, String db, String user, String password) {
		this.server = server;
		this.db = db;		
		this.port = port;
		this.user = user;
		this.password = password;
	}
  public String query(String query ,String type_table)
  {
    try
    {
      //�tape 1: charger la classe de driver
      Class.forName("org.postgresql.Driver");

      //�tape 2: cr�er l'objet de connexion
          
      Connection conn = DriverManager.getConnection( "jdbc:postgresql://"+server+":"+port+"/"+db, user, password);

      //�tape 3: cr�er l'objet statement 
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(query);
      
      //�tape 4: ex�cuter la requ�te
      String result = "";
      while(res.next())
    	  
      result = table_res(type_table,res);

      //�tape 5: fermez l'objet de connexion
      conn.close();
      
      //�tape 6: envoi r�sultat requ�te
      return result;
    }
    catch(Exception e){ 
      System.out.println(e);
    }
    //si requ�te non aboutie
	return null;
  }
  public int update(String query ,String type_table)
  {
    try
    {
      //�tape 1: charger la classe de driver
      Class.forName("org.postgresql.Driver");

      //�tape 2: cr�er l'objet de connexion
          
      Connection conn = DriverManager.getConnection( "jdbc:postgresql://"+server+":"+port+"/"+db, user, password);

      //�tape 3: cr�er l'objet statement 
      Statement stmt = conn.createStatement();
      int res = stmt.executeUpdate(query);
      
      //�tape 4: ex�cuter la requ�te
    

      //�tape 5: fermez l'objet de connexion
      conn.close();
      
      //�tape 6: envoi r�sultat requ�te
      return res;
    }
    catch(Exception e){ 
      System.out.println(e);
    }
    //si requ�te non aboutie
	return 0;
  }

  public int getLenghtTable(String query )
  {
    try
    {
      //�tape 1: charger la classe de driver
      Class.forName("org.postgresql.Driver");

      //�tape 2: cr�er l'objet de connexion
          
      Connection conn = DriverManager.getConnection( "jdbc:postgresql://"+server+":"+port+"/"+db, user, password);

      //�tape 3: cr�er l'objet statement 
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(query);
      
      //�tape 4: ex�cuter la requ�te
      int result= 0;
      while(res.next()) {result++;}

      //�tape 5: fermez l'objet de connexion
      conn.close();
      
      //�tape 6: envoi r�sultat requ�te
      return result;
    }
    catch(Exception e){ 
      System.out.println(e);
    }
    //si requ�te non aboutie
	return 0;
  }
  public String table_res(String type_table,  ResultSet res) {
	  String result = null;
	  switch (type_table) {
		case "utilisateur":
			try {
				result = res.getString(1)+"  "+res.getString(2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "film":
			try {
				result = res.getString(1)+"  "+res.getDate(2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "acteur":
			try {
				result = res.getInt(1)+"  "+res.getDouble(2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
	
		default:
			break;
	  }
	  return result;
	  
  }
}