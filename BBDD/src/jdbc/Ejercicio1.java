package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio1 {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://" + "localhost" + "/" + "sakila" + "?" + "user=" + "root"
					+ "&password=" + "Xiongyuxiao991217" + "&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
			
			Statement s = conn.createStatement();
			String sqlQuery = "SHOW TABLES;";
			
			ResultSet rs = s.executeQuery(sqlQuery);
			
			while(rs.next())
				//podemos acceder a las columnas del rs por numero(indice)
				//o por nombre de la columna
				System.out.println(rs.getString("Tables_in_sakila"));
			
			//cerramos los resultados y la sentencia
			rs.close();
			s.close();
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}

}
