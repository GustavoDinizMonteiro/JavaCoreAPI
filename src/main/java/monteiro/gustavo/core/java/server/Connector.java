package monteiro.gustavo.core.java.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

import io.github.cdimascio.dotenv.Dotenv;

public class Connector {
	private static Dotenv dotenv = Dotenv.load();
	
	private String url;
	private String user;
	private String pass;
	
	public Connector() {
		this.url = dotenv.get("DB_URL");
		this.user = dotenv.get("DB_USER");
		this.pass = dotenv.get("DB_PASS");
	}
    
    public Connection getConnection()
    {
      try {
          DriverManager.registerDriver(new Driver());
          return DriverManager.getConnection(this.url, this.user, this.pass);
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
    }
}
