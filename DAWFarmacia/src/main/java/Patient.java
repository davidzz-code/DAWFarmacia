import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient extends Person {
	public Patient(String name, String mail) {
		super(name, mail);
	}
	
	public Patient() {
	}
	
	public void load(String id) {
		
		String query = "SELECT * FROM patient where mail ='"+id+"';";
		BBDDFarmacia bd = new BBDDFarmacia();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
	        if (rs.next()) { // Verifica si hi ha cualcuna fila el resultSet
	            this.setName(rs.getString("name"));
	            this.setMail(rs.getString("mail"));
	        } else {
	            System.out.println("No se encontró ningún registro para el paciente: " + id);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en Patient.load: " + e.getMessage());
	    }
	}
	
	
}
