

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;



/**
 * Servlet implementation class ServePatients
 */
public class ServePatients extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServePatients() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *
	 *	doGet(): Agafa els parametres mail i session. 
	 *  Recupera de la BBDD la llista de pacients i crea un array de string amb el mail dels pacients i l’envia en format JSON.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		
		Doctor d = new Doctor();
		d.load(mail);

		
		if(d.isLogged(mail, session)) {
			String query = "SELECT * FROM patient";
			
			ArrayList<String> listaPatients = new ArrayList<>();
			String jsonString = null;
			JSONArray ja = new JSONArray();
		
			BBDDFarmacia bd = new BBDDFarmacia();
			bd.conectar();
			
			ResultSet rs = bd.loadSelect(query);
			try {
				while(rs.next()) {
					String patientMail = rs.getString("mail");
					listaPatients.add(patientMail);
				}
				
			    for (String patient : listaPatients) { // Este era el problema de duplicación de elementos. Tenía el "ja.put()" dentro del while
		            ja.put(patient);
		        }
				
				jsonString = ja.toString();
	
			} catch (Exception e) {
				System.out.println("Error en ServePatients.doGet " + e.getMessage());
			}
			
			
			response.getWriter().append(jsonString);
		}
	}
}
