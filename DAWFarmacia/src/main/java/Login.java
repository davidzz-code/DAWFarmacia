

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		try {
			BBDDFarmacia bd = new BBDDFarmacia();
			bd.conectar();
			
			String query = "SELECT * FROM doctor WHERE mail = '" + mail + "'";
			
			ResultSet rs = bd.loadSelect(query);
			
			Doctor d = new Doctor();
			d.login(mail, pass);
			
			if (rs.next()) {
			    response.getWriter().append(String.valueOf(d.getSession()) + ";");
			    response.getWriter().append(rs.getString("name"));
			} else {
			    System.out.println("No se encontraron resultados en Login.doGet");
			}
		}catch (SQLException e) {
			System.out.println("Error en Login.doGet " + e.getMessage());
		}
	}

}
