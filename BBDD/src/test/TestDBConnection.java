package test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

import com.mysql.cj.result.LocalDateTimeValueFactory;

import db.DBClientes;
import db.DBConnectionManager;
import db.DBPedido;
import db.EnumEstado;
import modelo.Cliente;

public class TestDBConnection {

	public static void main(String[] args) {
		DBConnectionManager.init("localhost","3306", "test_bbdd_jdbc", "root", "Xiongyuxiao991217");
		
		/*Connection c = DBConnectionManager.getConnection();

		String createSentence = "CREATE TABLE `test_bbdd_jdbc`.`clientes` ("
				 +"`id_cliente` INT NOT NULL AUTO_INCREMENT,"//autonumerico
				 +"`nombre` VARCHAR(30) NULL,"
				 +"`apellidos` VARCHAR(80) NULL,"
				 +"`ciudad` VARCHAR(30) NULL,"
				 +"`distrito` VARCHAR(30) NULL,"
				 +"`direccion` VARCHAR(30) NULL,"
				 +"`telefono_fijo` VARCHAR(30) NULL,"
				 +"`telefono_movil` VARCHAR(30) NULL,"
				 +"PRIMARY KEY (`id_cliente`))";

		try {
			Statement s = c.createStatement();
			
			s.executeUpdate(createSentence);
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
		
		//como solamente teniamos una conexion, 
		//podemos quitar el parametro de este metodo
		DBConnectionManager.closeConnection();
		
		//podemos volver a conectar y cerrar la conexion
		 
		if(!DBClientes.existTable())
			DBClientes.createTable();
		else
			System.out.println("la tabla ya esta creada");
		
		//Clientes.insertarCliente("Juan", "Lopez", "Madrid", "usera", "C/Paz, 21, 5ºA", "912431254", "68276213");
		//si lo damos a ejecutar dos veces, en la pantalla sale un 2 -> hemos creado dos clientes iguales
		
		//Clientes.insertarNewClientePrepared("Juan2", "Lopez", "Madrid", "usera", "C/Paz, 21, 5ºA", "912431254", "68276213");
		
		//pedir por usuario
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Introduce los datos del nuevo cliente ");
		System.out.print("nombre: ");
		String nombre = sc.nextLine();
		System.out.print("apellidos: ");
		String apellidos = sc.nextLine();
		System.out.print("ciudad: ");
		String ciudad = sc.nextLine();
		System.out.print("distrito: ");
		String distrito = sc.nextLine();
		System.out.print("direccion: ");
		String direccion = sc.nextLine();
		System.out.print("telefono_fijo: ");
		String telefono_fijo = sc.nextLine();
		System.out.print("telefono_movil: ");
		String telefono_movil = sc.nextLine();
		
		if(distrito.equals(""))
			distrito = null;
		
		Clientes.insertarCliente(nombre, apellidos, ciudad, distrito, direccion, telefono_fijo, telefono_movil);
		
		Clientes.insertarNewClientePrepared(nombre, apellidos, ciudad, distrito, direccion, telefono_fijo, telefono_movil);
		
		sc.close();
		
		Cliente c = userInterface.UICliente.pedirNuevoCliente();
		System.out.println(c);
		DBClientes.insertarNewClientePrepared(c);
		System.out.println(c);
		
		//DBClientes.insertarVariosClientes();
		
		LinkedList<Cliente> lista = DBClientes.buscarCliente("a");
		for(Cliente cl : lista) {
			System.out.println(cl);
		}*/
		
		LocalDateTime ahora = LocalDateTime.now();
		//DBPedido.insertarPedido(3, "Pedido de prueba desde java", EnumEstado.A, ahora);
		
		//DBPedido.insertarPedido(3, "Pedido de prueba desde java", EnumEstado.A, null);
		
		//DBPedido.obtenerPedido(6);
		//DBPedido.obtenerPedido(4);
		
		File f = new File(".");
		System.out.println(f.getAbsolutePath());
		
		DBClientes.importarCSV("/Users/apple/Desktop/UPM/UPM2.2/Eclipse/BBDD/clientes1.csv");
	}

}
