import java.sql.ResultSet;
import java.sql.SQLException;

public class Medicine {
	private int id;
	private String name;
	private float tMax;
	private float tMin;
	
	public Medicine() {

	}
	
	public Medicine(int id, String name, float tMax, float tMin) {
		this.setId(id);
		this.setName(name);
		this.setTmax(tMax);
		this.setTmin(tMin);
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTmax(float tMax) {
		this.tMax = tMax;
	}
	
	public float getTmax() {
		return tMax;
	}
	
	public void setTmin(float tMin) {
		this.tMin = tMin;
	}
	
	public float getTmin() {
		return tMin;
	}

	
	
	public void load(String id) {
		
		String query = "SELECT * FROM medicine where id ='"+id+"';";
		BBDDFarmacia bd = new BBDDFarmacia();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
	        if (rs.next()) { // Verifica si hi ha cualcuna fila el resultSet
	            this.setName(rs.getString("name"));
	            this.setTmax(rs.getFloat("tmax"));
	            this.setTmin(rs.getFloat("tmin"));
	        } else {
	            System.out.println("No se encontró ningún registro para la medicina con id: " + id);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en Medicine.load: " + e.getMessage());
	    }
	}

}
