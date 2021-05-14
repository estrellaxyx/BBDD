package es.upm.etsiinf.bbddgmi.test;

import java.util.List;

import es.upm.etsiinf.bbddgmi.db.DBConnectionManager;
import es.upm.etsiinf.bbddgmi.db.DBCountry;
import es.upm.etsiinf.bbddgmi.modelo.Country;

public class TestCountry {

	public static void main(String[] args) {
		DBConnectionManager.init("localhost","3306","sakila", "root","Xiongyuxiao991217");
		
		Country c = DBCountry.getCountryById(27);
		
		//saco por pantalla
		System.out.println(c);
		
		List<Country> countrylist = DBCountry.getCountryByName("sa");
		
		for(Country co : countrylist) {
			System.out.println(co);
		}
	}

}
