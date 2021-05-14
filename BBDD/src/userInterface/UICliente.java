package userInterface;

import java.util.Scanner;

import modelo.Cliente;

public class UICliente {

	public static Cliente pedirNuevoCliente() {
		Scanner sc = new Scanner(System.in);
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
		
		return new Cliente(nombre, apellidos, ciudad, distrito, direccion, telefono_fijo, telefono_movil);
		
	}
	
}
