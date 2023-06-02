
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class Doctor extends Person {
	//Atributs
	private String pass;
	private LocalDate last_log;
	private long session;
	private ArrayList<Xip> releaseList;
	
	//Scanners
	Scanner sc = new Scanner(System.in);

	//Constructors
	public Doctor() {
		super();
		this.setReleaseList();
	}

	public Doctor(String name, String mail, String pass, LocalDate last_log, long session) {
		super(name, mail);
		this.setPass(pass);
		this.setLastLog(last_log);
		this.setSession(session);
		this.setReleaseList();
	}

	
	//MÉTODES
	
	
	//Métode heredat de la classe persona que carrega a l'objecte les dades del doctor
	public void load (String id) {
		String query = "SELECT * FROM doctor where mail ='"+id+"';";
		BBDDFarmacia bd = new BBDDFarmacia();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
	        if (rs.next()) { // Verifica si hi ha cualcuna fila el resultSet
	            this.setName(rs.getString("name"));
	            this.setMail(rs.getString("mail"));
	            this.setPass(rs.getString("pass"));
	        } else {
	            System.out.println("No se encontró ningún registro para el correo: " + id);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en Doctor.load: " + e.getMessage());
	    }
	}
	
	//Métode per fer login del doctor
	public void login (String mail, String pass) {
		String query = "SELECT * FROM doctor where mail ='"+mail+"' AND pass='"+pass+"';";
		BBDDFarmacia bd = new BBDDFarmacia();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		try {
			if (rs.next()) {
				this.setLastLog(LocalDate.now());
				Random random = new Random();
				String code = "";
				for (int i= 0; i<9;i++) {
					code+=random.nextInt(10);
				}
				long session = Long.parseLong(code);
				
				this.setSession(session);
								
				query = "UPDATE doctor SET last_log= '"+this.getLastLog()+"',session= '"+this.getSession()+"' WHERE mail='"+mail+"';";
				bd.updateDoctor(query);
				
				this.load(mail);
			} else {
				System.out.println("El doctor no existe y por lo tanto no se han podido cargar los datos.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Error en el formato del número de sesion: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error en doctor.login" + e.getMessage());
		}
	}
	
	//Métode que retorna un boolea que indica true si troba el mail amb la session en data
	public boolean isLogged(String mail, String session) {
		String query = "SELECT * FROM doctor WHERE mail = '" + mail + "' AND session = '" + session + "'";

		BBDDFarmacia bd = new BBDDFarmacia();
		bd.conectar();
		
		ResultSet resultSet = bd.loadSelect(query);
		
		try {
			if(resultSet.next()) {
				System.out.println("Doctor logueado correctamente");
			} else {
				System.out.println("El doctor no está logueado");
				return false;
			}
		} catch(SQLException e) {
			System.out.println("Error en doctor.isLogged " + e.getMessage());
		}
		
		return true;
	}
	
	
	//Métode que carrega a l'array del doctor tots el xips que estiguin en data vinculats a ell
	public void loadReleaseList() {
		releaseList = new ArrayList<>();
		String query = "SELECT * FROM xip WHERE doctor_mail = '" + this.getMail() + "' AND date > current_date()";
		
		BBDDFarmacia bd = new BBDDFarmacia();
		bd.conectar();
		
		ResultSet rs = bd.loadSelect(query);
		
		try {
			while(rs.next()) {
				int xipId = rs.getInt("id");
				
				Xip xip = new Xip();
				xip.setId(xipId);
				
				xip.load(String.valueOf(xipId));

				releaseList.add(xip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Métode que retorna un String que correspon a una taula HTML de tots els xips d'alta, vigents y vinculats al doctor
	public String getTable() {
		
		this.loadReleaseList();// Guarda crea los xips relacionados con el doctor y los guarda en la array.
		
		StringBuilder html = new StringBuilder();
		
		try {
			html.append("<table>");
			
			html.append("<tr>");
			html.append("<th>ID</th>");
			html.append("<th>Medicina</th>");
			html.append("<th>Paciente</th>");
			html.append("<th>Fecha</th>");
			html.append("</tr>");

			for (Xip xip : releaseList) {
			    html.append("<tr>");
			    html.append("<td>").append(xip.getId()).append("</td>");
			    html.append("<td>").append(xip.getMedicine().getName()).append("</td>");
			    html.append("<td>").append(xip.getPatient().getName()).append("</td>");
			    html.append("<td>").append(xip.getDate()).append("</td>");
			    html.append("</tr>");
			}

			html.append("</table>");
		}
		catch (Exception e) {
			System.out.println("Error en Doctor.getTable() " + e.getMessage());
		}
	    
		return html.toString();
	}
	
	
	//Getters y Setters
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public LocalDate getLastLog() {
		return last_log;
	}
	public void setLastLog(LocalDate last_log) {
		this.last_log = last_log;
	}

	public long getSession() {
		return session;
	}
	public void setSession(long session) {
		this.session = session;
	}

	public ArrayList<Xip> getReleaseList() {
		return releaseList;
	}
	public void setReleaseList() {
		this.releaseList = new ArrayList<>();
	}
	
}