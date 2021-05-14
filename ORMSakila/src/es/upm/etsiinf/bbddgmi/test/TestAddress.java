package es.upm.etsiinf.bbddgmi.test;

import java.util.List;

import es.upm.etsiinf.bbddgmi.db.DBAddress;
import es.upm.etsiinf.bbddgmi.db.DBCity;
import es.upm.etsiinf.bbddgmi.db.DBConnectionManager;
import es.upm.etsiinf.bbddgmi.db.DBCountry;
import es.upm.etsiinf.bbddgmi.modelo.Address;
import es.upm.etsiinf.bbddgmi.modelo.City;
import es.upm.etsiinf.bbddgmi.modelo.Country;

public class TestAddress {
	public static void main(String[] args) {
		// Primero conecto la conexión
		DBConnectionManager.init("localhost", "3306", "sakila", "root", "Xiongyuxiao991217");

		//Address a = DBAddress.getAddressById(3);
		//System.out.println(a);
		

		Country spain = DBCountry.getCountryByName("sp").get(0);
		
		List<City> cList = DBCity.getCityByNameAndCountry("a", spain);
		
		Address newAddress = new Address("C/ Mayor XXX, 3ºC", "", "974857", cList.get(0));
		newAddress.setPostalCode("98834");
		
		System.out.println(newAddress);
		
		DBAddress.saveNewAddress(newAddress);
		
		System.out.println(newAddress);
		
		//Borrar dirección
		//DBAddress.deleteAddress(newAddress);
	}
}
