import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BBDDFarmacia {
	private Connection con;
	private Statement st;
	
	// Connecting method
	public void conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error en BBDD.conectar.Class: " + e.getMessage());
		}
		
		con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3308/farmacia", "root", "");
		} catch (SQLException e) {
			System.out.println("Error en BBDD.conectar.Connection: " + e.getMessage());
		}
		
		
		st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Error en BBDD.conectar.Statement: " + e.getMessage());
		}
		
	}
	
	public ResultSet loadSelect(String query) {
		ResultSet rs;
		
		rs = null;
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error en BBDDFarmacia.loadSelect.executeQuery " + e.getMessage());
		}
		return rs;
	}

	public void updateDoctor(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error en BBDDFarmacia.executeUpdate " + e.getMessage());
		}
		
	}


}
