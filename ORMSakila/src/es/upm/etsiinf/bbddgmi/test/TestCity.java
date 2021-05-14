package es.upm.etsiinf.bbddgmi.test;

import java.util.List;

import es.upm.etsiinf.bbddgmi.db.DBCity;
import es.upm.etsiinf.bbddgmi.db.DBConnectionManager;
import es.upm.etsiinf.bbddgmi.db.DBCountry;
import es.upm.etsiinf.bbddgmi.modelo.City;
import es.upm.etsiinf.bbddgmi.modelo.Country;

public class TestCity {

	public static void main(String[] args) {
		DBConnectionManager.init("localhost", "3306", "sakila", "root", "Xiongyuxiao991217");

		City c = DBCity.getCityById(17);
		//System.out.println(c);

		// Busco una ciudad dado
		Country spain = DBCountry.getCountryByName("Sp").get(0);
		List<City> cList = DBCity.getCityByNameAndCountry("a", spain);

		// Hemos tenido que no cerrar la conexión porque como llamamos a varios métodos
		// se nos cerraba la conexión
		// Podríamos hacer un pool de conexiones y asignar a cada proceso una
		// O hacer que espere cierto tiempo

		for (City city : cList) {
			System.out.println(city);
		}
		DBConnectionManager.closeConnection();

	}
}
