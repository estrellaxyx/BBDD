package es.upm.etsiinf.bbddgmi.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.upm.etsiinf.bbddgmi.modelo.Country;

public class DBCountry {

	public static Country getCountryById(int id) {
		Country c = null;
		
			try {
				PreparedStatement pst = DBConnectionManager.getConnection()
						.prepareStatement("SELECT * FROM country WHERE country_id = ? ;");
				
				
				pst.setInt(1, id);
				
				//solo devuelve una fila
				ResultSet rs = pst.executeQuery();
				
				//sacamos entonces su id y el nombre, creamos un pais con ellos
				if(rs.next()) {
					int countryId = rs.getInt(1);
					String name = rs.getString(2);
					c = new Country(countryId, name);
					
				}
				rs.close();
				pst.close();
				DBConnectionManager.closeConnection();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
		return c;
	}
	
	// Hago una búsqueda de un String en los países
	public static List<Country> getCountryByName(String q){
		List<Country> countries = new ArrayList<>();
		
		try {
			PreparedStatement pst = DBConnectionManager.getConnection()
					.prepareStatement("SELECT * FROM country WHERE country LIKE ? ;");
			
			
			pst.setString(1, "%"+q+"%");
			
			//solo devuelve una fila
			ResultSet rs = pst.executeQuery();
			
			//sacamos entonces su id y el nombre, creamos un pais con ellos
			while(rs.next()) {
				int countryId = rs.getInt(1);
				String name = rs.getString(2);
				Country c = new Country(countryId, name);
				countries.add(c);
			}
			rs.close();
			pst.close();
			DBConnectionManager.closeConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return countries;
	}
}
