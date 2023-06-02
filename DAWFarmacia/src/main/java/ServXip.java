

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ServXip
 */
public class ServXip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServXip() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	 *
	 *	-doGet(): Agafa els parametres mail i session. 
	 *Executa de la classe Doctor els mètodes isLogged(), load(), loadRelease(), getTable() i retorna l’String resultant.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		
		Doctor d = new Doctor();
		boolean isLogged = d.isLogged(mail, session);

		if(isLogged) {
			d.load(mail);
			String html = d.getTable();
			response.getWriter().append(html);
		} else {
			System.out.println("El doctor no está loggeado");
		}
	}

}
