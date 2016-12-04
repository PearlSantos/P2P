package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Passenger {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long id;
	@Column
	@NotNull(message = "Please provide your name.")
	private String fullName;
	@Column
	@NotNull(message = "Please provide your password.")
	private String password;
	@Column
	@NotNull(message = "Please provide your mobile number.")
	private String mobileNumber;
	@Column
	private String emailAddress;
	@Column
	@NotNull(message = "Please provide your barangay")
	private String barangay;
	@Column
	@NotNull(message = "Please provide your city")
	private String city;
	@Column
	@NotNull(message = "Please provide your zip code")
	private String zipCode;
	@JoinColumn
	@ManyToOne 
	private Trip tripAssigned;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getBarangay() {
		return barangay;
	}
	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Trip getTripAssigned() {
		return tripAssigned;
	}
	public void setTripAssigned(Trip tripAssigned) {
		this.tripAssigned = tripAssigned;
	}
	public String getTripAssignedName() {
		return tripAssigned.getTripName();
	}
	
	
	
}
