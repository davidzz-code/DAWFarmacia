

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
/**
 * Servlet implementation class Release
 */
public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Release() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)

		-doPost(): Agafa els parametres mail, session, idXip, idMed, date i mailP. 
		I insereix a la BBDD el nou registre a la taula xip.
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		
		String idXip = request.getParameter("idXip");
		String idMed = request.getParameter("idMed");
		String mailP = request.getParameter("mailP");
		String date = request.getParameter("date");
		
		String query = "INSERT INTO xip (id, doctor_mail, id_medicine, id_patient, date) VALUES ('"+idXip+"', '"+ mail +"', '"+ idMed +"', '"+ mailP +"', '"+ date +"')";
		
		
		Doctor d = new Doctor();
		d.load(mail);
		boolean isLogged = d.isLogged(mail, session);

		
		if(isLogged) {
			try {
				BBDDFarmacia bd = new BBDDFarmacia();
				bd.conectar();
				bd.updateDoctor(query);
				
				System.out.println("Insert hecho");
				
				
				response.getWriter().append("Alta confirmada");
				
			} catch(Exception e) {
				System.out.println("Error en Release.doPost" + e.getMessage());
			}
		
		} else {
			System.out.println("El doctor no está logueado, inicia sesión");
		}
	}
}
