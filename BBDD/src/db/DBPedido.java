package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class DBPedido {

	public static void insertarPedido(int idCliente, String descripcion, EnumEstado estado, LocalDateTime fecha) {
		Connection c = DBConnectionManager.getConnection();

		try {
			PreparedStatement pst = c.prepareStatement(
					"INSERT INTO `pedido_cliente`(`id_cliente`, `descripcion`,`estado`,`fecha_pedido`)"
							+ "\n VALUES (?,?,?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS);

			pst.setInt(1, idCliente);
			pst.setString(2, descripcion);
			pst.setString(3, estado.toString());
			pst.setObject(4, fecha);

			int filasAfectadas = pst.executeUpdate();

			int newId = -1;
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next())
				newId = rs.getInt(1);
			System.out.println("Nueva linea de pedido creada con id " + newId);

			rs.close();
			pst.close();

			DBConnectionManager.closeConnection();
		} catch (SQLException ex) {

			System.err.println(ex.getMessage());
		}

	}

	public static void obtenerPedido(int idPedido) {
		Connection c = DBConnectionManager.getConnection();

		try {
			PreparedStatement pst = c.prepareStatement("SELECT * FROM `pedido_cliente`" + " WHERE id_pedido = ?;");
			pst.setInt(1, idPedido);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int idCliente = rs.getInt(2);
				String descripcion = rs.getString(3);
				LocalDateTime fecha = rs.getObject(4, LocalDateTime.class);
				EnumEstado estado = EnumEstado.valueOf(rs.getString(6));

				System.out.println(
						"El pedido con id " + idPedido + " es [" 
								+ "cliente: " + idCliente + "," 
								+ "descripcion: "+ descripcion + "," 
								+ "fecha: " + (fecha == null ? "VACIO" : fecha.toString()) + "," 
								+ "estado: " + estado + "]");
			} else
				System.out.println("El pedido con id: " + idPedido + " no existe.");
			
			rs.close();
			pst.close();

			DBConnectionManager.closeConnection();
		} catch (SQLException ex) {

			System.err.println(ex.getMessage());
		}
	}

}
