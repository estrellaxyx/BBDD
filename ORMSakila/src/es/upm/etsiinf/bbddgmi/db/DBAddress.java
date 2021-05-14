package es.upm.etsiinf.bbddgmi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.upm.etsiinf.bbddgmi.modelo.Address;
import es.upm.etsiinf.bbddgmi.modelo.City;
import es.upm.etsiinf.bbddgmi.modelo.Country;

public class DBAddress {

	public static Address getAddressById(int id) {

		// Hago la conexión
		Connection conn = DBConnectionManager.getConnection();

		Address a = null;

		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM address WHERE address_id = ? ;");
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next())
				a = new Address(rs.getInt(1), // id
						rs.getString(2), // address1
						rs.getString(3), // address2
						rs.getString(4), // district
						rs.getString(6), // post code
						rs.getString(7), // phone
						DBCity.getCityById(rs.getInt(5))); // city

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;

	}

	// Hacemos un método que nos guarde una dirección
	public static void saveNewAddress(Address a) {

		// cojo la conexion
		try {
			PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(
					"INSERT INTO address(address,address2,district,city_id, postal_code,phone, location) VALUES"
							+ "(?,?,?,?,?,?,POINT(-122.8185647, 49.6999986))",
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, a.getAddress1());
			pst.setString(2, a.getAddress2());
			pst.setString(3, a.getDistrict());
			pst.setInt(4, a.getCity().getId());
			pst.setString(5, a.getPostalCode());
			pst.setString(6, a.getPhone());

			// Número de filas afectadas
			int n = pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				a.setId(rs.getInt(1));
			}

			pst.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método que borra una dirección
	public static void deleteAddress(Address address) {
		try {
			PreparedStatement pst = DBConnectionManager.getConnection()
					.prepareStatement("DELETE FROM address WHERE address_id = ?;");
			pst.setInt(1, address.getId());

			int numRowsAffected = pst.executeUpdate();

			if (numRowsAffected < 1) {
				System.out.println("No se encontró dirección para borrar con id = " + address.getId());
			} else {
				System.out.println("Borrada de la base de datos la dirección " + address.getId());
			}

			address.setId(-1);

			pst.close();
			DBConnectionManager.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
