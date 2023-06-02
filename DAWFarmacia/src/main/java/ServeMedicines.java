

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ServeMedicines
 */
public class ServeMedicines extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeMedicines() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		
		Doctor d = new Doctor();
		d.load(mail);

		
		if(d.isLogged(mail, session)) {
		
			String jsonString = null;
			JSONArray ja = new JSONArray();
		
			BBDDFarmacia bd = new BBDDFarmacia();
			bd.conectar();
			
			try {
				String query = "SELECT * FROM medicine";
				ResultSet rs = bd.loadSelect(query);
				Class.forName("org.json.JSONObject");
				
				
				while(rs.next()) {
					int medicineId = rs.getInt("id");
					String medicineName = rs.getString("name");
					float medicineTmax = rs.getFloat("tmax");
					float medicineTmin = rs.getFloat("tmin");
					
					Medicine medicine = new Medicine(medicineId, medicineName, medicineTmax, medicineTmin);
					JSONObject jsonOb = new JSONObject(medicine);
					ja.put(jsonOb);
				}
				
				jsonString = ja.toString();

	
			} catch (Exception e) {
				System.out.println("Error en ServeMedicines.doGet " + e.getMessage());
			}
			
			
			response.getWriter().append(jsonString);
		} else {
			System.out.println("El doctor no está logueado");
		}
	}

}
