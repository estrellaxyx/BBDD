package es.upm.etsiinf.bbddgmi.test;


import java.io.File;

import es.upm.etsiinf.bbddgmi.db.DBConnectionManager;
import es.upm.etsiinf.bbddgmi.db.DBCustomer;
import es.upm.etsiinf.bbddgmi.modelo.Customer;

public class TestCustomer {

	public static void main(String[] args) {

		DBConnectionManager.init("localhost", "3306", "sakila", "root", "Xiongyuxiao991217");

		Customer c = DBCustomer.getCostumerById(2);
		System.out.println(c);
		
		File image = new File("/Users/apple/Desktop/UPM/UPM2.2/Eclipse/ORMSakila/descarga.jpg");
		if(!(image.exists() && image.isFile())){
			System.out.println("No se encuentra fichero en: " + image.getAbsolutePath());
			c.setImage(null); //Si no encuentro el fichero la pongo a null
		}else {
			c.setImage(image);
			DBCustomer.updateCustomerImage(c);
		}
		
	}
}