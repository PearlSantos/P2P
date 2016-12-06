package app.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Bus;
import app.entities.Passenger;
import app.entities.Trip;
import app.repositories.BusRepository;
import app.repositories.PassengerRepository;
import app.repositories.RouteRepository;
import app.repositories.TripRepository;

@Component
@Path("/client")
public class ClientServer {
	
	@Autowired
	private PassengerRepository passengerRepo;
	@Autowired
	private BusRepository busRepo;
	@Autowired
	private RouteRepository routeRepo;
	@Autowired
	private TripRepository tripRepo;

	@POST
	@Path("/register")
	public void register(@FormParam("fullName") String fullName, @FormParam("password") String password, 
						@FormParam("mobileNumber")String mobileNumber, @FormParam("typeOfPassenger") String typeOfPassenger, 
						@FormParam("emailAddress") String emailAddress, @FormParam("barangay") String barangay, 
						@FormParam("city") String city, @FormParam("zipCode") String zipCode){
		Passenger p = new Passenger();
		p.setFullName(fullName);
		p.setPassword(password);
		p.setMobileNumber(mobileNumber);
		p.setEmailAddress(emailAddress);
		p.setBarangay(barangay);
		p.setCity(city);
		p.setZipCode(zipCode);
		passengerRepo.save(p);
	}
	
	@POST
	@Path("/reserveSeat")
	public void reserveSeat(@FormParam("mobileNumber") String mobileNumber, @FormParam("tripName") String tripName){
		Passenger p = passengerRepo.findByMobileNumber(mobileNumber);
		Trip trip = tripRepo.findByTripName(tripName);
		Bus b = trip.getAssignedBus();
		int currSeatNumber = b.getNumOfAvailableSeats();
		busRepo.setNumberOfAvailableSeats(currSeatNumber-1, b.getPlateNumber());
//		b.setCapacity(currSeatNumber-1);
		passengerRepo.setTrip(trip, mobileNumber);
	}
	
	@POST
	@Path("/cancelTrip")
	public void cancelTrip(@FormParam("mobileNumber") String mobileNumber, @FormParam("tripName") String tripName){
		Passenger p = passengerRepo.findByMobileNumber(mobileNumber);
		Trip trip = tripRepo.findByTripName(tripName);
		Bus b = trip.getAssignedBus();
		int currSeatNumber = b.getNumOfAvailableSeats();
//		b.setCapacity(currSeatNumber+1);
		busRepo.setNumberOfAvailableSeats(currSeatNumber+1, b.getPlateNumber());
		passengerRepo.setTrip(null, mobileNumber);
	}
	
//	@POST
//	@Path("/updateMobileNumber")
//	public void updateMobileNumber(){
//		
//	}
}
