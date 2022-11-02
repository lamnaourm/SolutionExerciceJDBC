import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {
		try {
			
			Scanner sc = new Scanner(System.in);
			
			String req1 = "SELECT * FROM PRODUIT";	
			String req2 = "SELECT * FROM PRODUIT WHERE prix_achat>20 and nom like 'P%'";
			String req3 = "SELECT famille, sum(prix_achat), sum(prix_vente) FROM PRODUIT group by famille";
			String req4 = "SELECT * FROM PRODUIT Where famille = ?";
			String req5 = "INSERT INTO PRODUIT VALUES (null, ?,?,?,?)";
			String req6 = "UPDATE PRODUIT SET prix_achat += prix_achat*0.1 prix_vente += prix_vente*0.1 WHERE famille = ?";
			String req7 = "DELETE FROM PRODUIT WHERE nom = 'A%'";
			
			
			Properties prop = new Properties();
			prop.load(new FileInputStream("db.properties"));
			
			Class.forName(prop.getProperty("driver"));
			
			Connection conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),prop.getProperty("pwd"));
			
			Statement st = conn.createStatement();
			
			System.out.println("1- Liste des produits");
			ResultSet rs = st.executeQuery(req1);
			while(rs.next()) {
				System.out.printf("id:%d - nom:%s - famille:%s - Achat:%.2f - vente:%.2f\n", rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5));
			}
			rs.close();
			
			System.out.println("---------------------------------------------------------------------------");
			
			System.out.println("2- Liste des produits qui ont un nom qui commence par A et prix achat > 200");
			rs = st.executeQuery(req2);
			while(rs.next()) {
				System.out.printf("id:%d - nom:%s - famille:%s - Achat:%.2f - vente:%.2f\n", rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5));
			}
			rs.close();
			
			System.out.println("---------------------------------------------------------------------------");
			
			System.out.println("3- Somme prix achat et prix de vente par famille");
			rs = st.executeQuery(req3);
			while(rs.next()) {
				System.out.printf("Famille: %s - Somme Achat:%.2f - Somme Vente:%.2f\n",rs.getString(1),rs.getDouble(2),rs.getDouble(3));
			}
			rs.close();
			
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("4- Liste des produits d'une famille");
		
			PreparedStatement pst = conn.prepareStatement(req4);
			System.out.print("Donner une famille : ");
			pst.setString(1, sc.nextLine());
			rs = pst.executeQuery();
			while(rs.next()) {
				System.out.printf("id:%d - nom:%s - famille:%s - Achat:%.2f - vente:%.2f\n", rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5));
			}
			rs.close();
			
			
			
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
