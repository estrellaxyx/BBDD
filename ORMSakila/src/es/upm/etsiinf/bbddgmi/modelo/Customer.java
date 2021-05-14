package es.upm.etsiinf.bbddgmi.modelo;

import java.io.File;
import java.time.LocalDateTime;

public class Customer {

	private int customerId = -1;
	private String firstName, lastName, email;
	private int active;
	private Address address;
	private LocalDateTime createDate;
	private File image;

	// Constructores

	// Constructor para cargar de la base de datos

	public Customer(int customerId, String firstName, String lastName, String email, int active, Address address,
			LocalDateTime createDate, File image) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.active = active;
		this.address = address;
		this.createDate = createDate;
		this.image = image;
	}

	// Podría añadir el otro cosntructor si necesitase uno sin el id
	

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", active=" + active + ", address=" + address + ", createDate=" + createDate + ", "
				+ (image != null ? image.getAbsolutePath() : image) + "]";
	}

}
