package app.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

import app.entities.Bus;
import app.entities.Passenger;
import app.entities.Route;
import app.entities.Trip;
import app.repositories.BusRepository;
import app.repositories.PassengerRepository;
import app.repositories.RouteRepository;
import app.repositories.TripRepository;

@Component
@Path("/admin")
public class AdminServer {
	
	@Autowired
	private PassengerRepository passengerRepo;
	@Autowired
	private BusRepository busRepo;
	@Autowired
	private RouteRepository routeRepo;
	@Autowired
	private TripRepository tripRepo;
	
	@POST
	@Path("/viewPassengerInfo")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public MyPassenger viewPassengerInfo(@FormParam("mobileNumber") String mobileNumber){
		Passenger p = passengerRepo.findByMobileNumber(mobileNumber);
		System.out.println(p.getFullName() + " yehey");
//		return p.getFullName();
		MyPassenger mp = new MyPassenger();
		mp.setFullName(p.getFullName());
		mp.setPassword(p.getPassword());
		mp.setBarangay(p.getBarangay());
		mp.setCity(p.getCity());
		mp.setEmailAddress(p.getEmailAddress());
		mp.setMobileNumber(p.getMobileNumber());
		mp.setTripAssigned(p.getTripAssigned().getTripName());
		mp.setZipCode(p.getZipCode());
		return mp;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/deletePassenger")
	public void deletePassenger(@FormParam("mobileNumber") String mobileNumber){
		System.out.println(mobileNumber);
		passengerRepo.deletePassenger(mobileNumber);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/createTrip")
	public void createTrip(@FormParam("tripName") String tripName, @FormParam("ETD") String ETD, @FormParam("ETA") String ETA,
			@FormParam("routeName") String routeName, @FormParam("plateName") String plateNumber){
		Bus b = busRepo.findByPlateNumber(plateNumber);
		Route r = routeRepo.findByRouteName(routeName);
		Trip t = new Trip();
//		tripName, ETD, ETA, r, b
		t.setTripName(tripName);
		t.setETA(ETD);
		t.setETA(ETA);
		t.setAssignedRoute(r);
		t.setAssignedBus(b);
		tripRepo.save(t);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/updateTrip")
	public void updateTrip(@FormParam("tripName") String tripName, @FormParam("newETD") String newETD, @FormParam("newETA") String newETA){
//		Trip t = tripRepo.findByTripName(tripName);
//		Trip temp = t;
//		tripRepo.delete(temp);
//		t.setETA(newETA);
//		t.setETD(newETD);
//		tripRepo.save(t);
		
		tripRepo.setETA(newETA, tripName);
		tripRepo.setETD(newETD, tripName);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/deleteTrip")
	public void deleteTrip(String tripName){
		Trip t = tripRepo.findByTripName(tripName);
		tripRepo.delete(t);
	}
	
//	@POST
//	@Path("/sendUpdates")
//	public void sendUpdates(@FormParam("tripName") String message, @FormParam("tripName") String[] mobileNumbers){
//		//OMG Cel paano to
//	}
	
	




	class MyPassenger {
		public MyPassenger() {
			
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
//		public String getTypeOfPassenger() {
//			return typeOfPassenger;
//		}
//		public void setTypeOfPassenger(String typeOfPassenger) {
//			this.typeOfPassenger = typeOfPassenger;
//		}
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

}
