package modelo;

public class Cliente {

	private int id = -1;//cliente con id = -1 es que es un cliente nuevo, sin guardar en la base de datos
	

	private String nombre, apellidos, ciudad, distrito, direccion, telefono_fijo, telefono_movil;

	public Cliente(String nombre, String apellidos, String ciudad, String distrito, String direccion,
			String telefono_fijo, String telefono_movil) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ciudad = ciudad;
		this.distrito = distrito;
		this.direccion = direccion;
		this.telefono_fijo = telefono_fijo;
		this.telefono_movil = telefono_movil;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono_fijo() {
		return telefono_fijo;
	}

	public void setTelefono_fijo(String telefono_fijo) {
		this.telefono_fijo = telefono_fijo;
	}

	public String getTelefono_movil() {
		return telefono_movil;
	}

	public void setTelefono_movil(String telefono_movil) {
		this.telefono_movil = telefono_movil;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", ciudad=" + ciudad
				+ ", distrito=" + distrito + ", direccion=" + direccion + ", telefono_fijo=" + telefono_fijo
				+ ", telefono_movil=" + telefono_movil + "]";
	}

}
