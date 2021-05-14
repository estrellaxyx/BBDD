package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestPreparedStatement {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sakila?" + 
			"&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC",
			"root","Xiongyuxiao991217");
			
			String query= "select * from actor where actor_id = ? AND first_name= ? ";
			PreparedStatement stmt= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Introduce el id del actor: ");
			int id = sc.nextInt();
			stmt.setInt(1, id);
			System.out.println("Introduce el nombre del actor: ");
			String nombre = sc.next();
			
			stmt.setString(2, nombre);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println("Actor "+rs.getRow()+": "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			
			sc.close();
			rs.close();
			stmt.close();
		}catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.err.println("No se ha podido cargar el driver mysql JDBC");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("No se ha podido conectar a la base de datos");
			e.printStackTrace();
		}

	}

}
