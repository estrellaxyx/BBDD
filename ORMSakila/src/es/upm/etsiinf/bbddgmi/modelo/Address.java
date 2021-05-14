package es.upm.etsiinf.bbddgmi.modelo;

public class Address {
	private int id = -1;
	private String address1, address2, district, postalCode, phone;
	private City city;
	
	//from database
	public Address(int id, String address1, String address2, String district, String postalCode, String phone,
			City city) {
		super();
		this.id = id;
		this.address1 = address1;
		this.address2 = address2;
		this.district = district;
		this.postalCode = postalCode;
		this.phone = phone;
		this.city = city;
	}

	//from new objects
	public Address(String address1, String district, String phone, City city) {
		super();
		this.address1 = address1;
		this.district = district;
		this.phone = phone;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	

	@Override
	public String toString() {
		return "Address [id=" + id + ", address1=" + address1 + ", address2=" + address2 + ", district=" + district
				+ ", postalCode=" + postalCode + ", phone=" + phone + ", city=" + city + "]";
	}
	
	
}
