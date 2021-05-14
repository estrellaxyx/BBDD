package es.upm.etsiinf.bbddgmi.modelo;

public class Country {

	private int id = -1;
	private String name;
	
	//constructor used when loading from the database
	public Country(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//constructor used when creating new objects
	public Country(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Country [Id = "+id+", name = "+name+"]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
