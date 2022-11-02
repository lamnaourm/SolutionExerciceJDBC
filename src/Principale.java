import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Principale {

	public static void main(String[] args) {
		try {
			
			String req1 = "SELECT * FROM PRODUIT";	
			String req2 = "SELECT * FROM PRODUIT WHERE prix_achat>200 and nom like 'A%'";
			String req3 = "SELECT sum(prix_achat), sum(prix_vente) FROM PRODUIT group by famille";
			String req4 = "SELECT * FROM PRODUIT Where famille = ?";
			String req5 = "INSERT INTO PRODUIT VALUES (null, ?,?,?,?)";
			String req6 = "UPDATE PRODUIT SET prix_achat += prix_achat*0.1 prix_vente += prix_vente*0.1 WHERE famille = ?";
			String req7 = "DELETE FROM PRODUIT WHERE nom = 'A%'";
			
			
			Properties prop = new Properties();
			prop.load(new FileInputStream("db.properties"));
			
			Class.forName(prop.getProperty("driver"));
			
			Connection conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),prop.getProperty("pwd"));
			
			Statement st = conn.createStatement();
			
			
			st.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
