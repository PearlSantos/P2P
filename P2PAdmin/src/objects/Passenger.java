package objects;



public class Passenger {
	public Passenger() {
		
	}
	String fullName;
	String password;
	String mobileNumber;
	String emailAddress;
	String barangay;
	String city;
	String zipCode;
	String tripAssigned;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
//	public String getTypeOfPassenger() {
//		return typeOfPassenger;
//	}
//	public void setTypeOfPassenger(String typeOfPassenger) {
//		this.typeOfPassenger = typeOfPassenger;
//	}
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
	public String getTripAssigned() {
		return tripAssigned;
	}
	public void setTripAssigned(String tripAssigned) {
		this.tripAssigned = tripAssigned;
	}
}
