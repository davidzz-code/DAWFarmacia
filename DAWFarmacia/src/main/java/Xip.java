import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

public class Xip {
	private int id;
	private Medicine medicine;
	private Patient patient;
	private LocalDate date;

	public Xip() {
	}
	
	public Xip(int id, Medicine medicine, Patient patient, LocalDate date) {
		this.setId(id);
		this.setMedicine(medicine);
		this.setPatient(patient);
		this.setDate(date);
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	
	public Medicine getMedicine() {
		return medicine;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}
	
	
	
	public void load(String id) {
		
		String query = "SELECT * FROM xip WHERE id ='"+id+"';";
		BBDDFarmacia bd = new BBDDFarmacia();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
	        if (rs.next()) { // Verifica si hi ha cualcuna fila el resultSet
	            String patientMail = rs.getString("id_patient");
	            int medicineId = rs.getInt("id_medicine");
	            
	            Patient patient = new Patient();
	            Medicine medicine = new Medicine();
	            
	            patient.load(patientMail);
	            medicine.load(String.valueOf(medicineId));
	            
	            this.setPatient(patient);
	            this.setMedicine(medicine);
	            this.setDate(rs.getDate("date").toLocalDate());
	            
	        } else {
	            System.out.println("No se encontró ningún registro para el xip con id: " + id);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en Xip.load: " + e.getMessage());
	    }
	}
}
