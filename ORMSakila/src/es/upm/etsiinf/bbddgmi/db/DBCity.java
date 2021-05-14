package es.upm.etsiinf.bbddgmi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.upm.etsiinf.bbddgmi.modelo.City;
import es.upm.etsiinf.bbddgmi.modelo.Country;

public class DBCity {

	// Método para cargar una ciudad
	public static City getCityById(int id){

		// Hago la conexión
		Connection conn = DBConnectionManager.getConnection();

		City city = null;

		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM city WHERE city_id = ? ;");
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next())
				city = new City(rs.getInt(1), rs.getString(2), DBCountry.getCountryById(rs.getInt(3)));

			rs.close();
			pst.close();
			DBConnectionManager.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return city;

	}

	public static List<City> getCityByNameAndCountry(String q, Country c){
		// Hago la conexión
		Connection conn = DBConnectionManager.getConnection();

		List<City> cities = new ArrayList<City>();

		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM city WHERE city LIKE ? AND country_id = ? ;");
			pst.setString(1, "%" + q + "%");
			pst.setInt(2, c.getId());

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				City city = new City(rs.getInt(1), rs.getString(2), DBCountry.getCountryById(rs.getInt(3)));
				cities.add(city);
				
			}
			
			rs.close();
			pst.close();
			DBConnectionManager.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cities;
	}

}
