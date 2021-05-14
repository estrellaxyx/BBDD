package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TiposDeCursorsJDBC {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + "localhost" + "/" + "sakila" + "?" + "user=" + "root"
					+ "&password=" + "Xiongyuxiao991217" + "&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");

		//a) Comprobar si nuestro driver y servidor soportan los tipos de cursor cursor 
			//TYPE_SCROLL_SENSITIVE, TYPE_SCROLL_INSENSITIVE, y TYPE_FORWARD_ONLY	
		DatabaseMetaData dbMD = conn.getMetaData();
		if(dbMD.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY))
			System.out.println("Admite cursores de solo avanzar en los resultados");
		if(dbMD.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE))
			System.out.println("Admite cursores de avanzar y retroceder, sin actualizacion de datos");
		if(dbMD.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE))
			System.out.println("Admite cursores de avanzar y retroceder, con actualizacion de datos");
		
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		//b) Obtener la lista de actores cuyo nombre es 'DAN' e 
		//imprimirlo en orden inverso al que se recibe en el resultset
		String query = "SELECT * "
				+ "FROM actor " + "WHERE actor.first_name = 'DAN';";
		
		ResultSet rs = st.executeQuery(query);
		
		rs.afterLast();
		while(rs.previous()) {
			System.out.println("Actor "+rs.getRow()+": "+rs.getString("first_name")+" "+rs.getString("last_name"));
		}
		rs.close();
		
		//c) Contar el número de filas resultado de una consulta. 
		//Por ejemplo: DESCRIBE actor;
		query = "DESCRIBE actor;";
		
		rs = st.executeQuery(query);
		//contar el numero de filas recibidas
		rs.last();
		System.out.println(rs.getRow()+" filas obtenidas");
		//como para contar las filas habla llevado el cursor al final, 
		//debemos devolverlo a antes de la primera fila
		rs.beforeFirst();
		//vemos el contenido
		while(rs.next()) {
			System.out.println("Campo "+rs.getRow()+": "+rs.getString("field")+" "+rs.getString("type"));
		}

		
		}catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.err.println("No se ha podido cargar el driver mysql JDBC");
			e.printStackTrace();
		}catch (SQLException e) {
			System.err.println("No se ha podido conectar a la base de datos");
			e.printStackTrace();
		}

	}

}
