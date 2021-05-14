package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestCursor {

	public static void main(String[] args) {
		// Cargar todos los actores de la tabla 'actor' de la base de datos sakila,
		// luego hay que pedir al usuario repetidamente un número entero por teclado.
		// Si el número está entre 1 y el número de filas cargadas en el resultado
		// imprime por la consola: "El nombre del actor en la fila X es ...".
		// Si el número es superior al número de filas, indicar que no existe la fila
		// seleccionada.
		// Si el número es menor que 1 terminar del programa.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sakila?" + 
			"&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC",
			"root","Xiongyuxiao991217");
		
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			String query = "SELECT * FROM actor;";
			
			ResultSet rs = st.executeQuery(query);
			Scanner sc = new Scanner(System.in);
			int n = -1;
			rs.last();
			int filas = rs.getRow();
			do {
				System.out.println("Introduce la fila");
				n = sc.nextInt();
				if(n>=1&& n <= filas) {
					rs.absolute(n);
					System.out.println("El actor en la fila "+n+" es "+rs.getString("first_name")+" "+rs.getString("last_name"));
				}else if(n>filas)
					System.out.println("No existe dicha fila");
			}while(n>=1);
			System.out.println("FIN");
			sc.close();
			rs.close();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.err.println("No se ha podido cargar el driver mysql JDBC");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("No se ha podido conectar a la base de datos");
			e.printStackTrace();
		}
	}

}
