package es.upm.etsiinf.bbddgmi.modelo;

public class City {

	private int id = -1;
	private String name;
	private Country country;
	
	//constructor used when loading form database
	public City(int id, String name, Country country) {
		this.id = id;
		this.name = name;
		this.country = country;
	}

	//constructor used when creating new objects
	public City(String name, Country country) {
		this.name = name;
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", country=" + country + "]";
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
}
