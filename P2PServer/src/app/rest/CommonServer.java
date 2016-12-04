package app.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Trip;
import app.repositories.BusRepository;
import app.repositories.PassengerRepository;
import app.repositories.RouteRepository;
import app.repositories.TripRepository;

@Component
@Path("/common")
public class CommonServer {
	
	@Autowired
	private PassengerRepository passengerRepo;
	@Autowired
	private TripRepository tripRepo;

	
	@POST
	@Path("/viewTrip")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Trip viewTripDetails(String tripName){
		Trip t = tripRepo.findByTripName(tripName);
		return t;
	}
	
	@POST
	@Path("/updateMobileNumber")
	public void updatePassengerMobileNumber(@FormParam("oldMobileNumber") String oldMobileNumber, @FormParam("newMobileNumber") String newMobileNumber){
		passengerRepo.setMobileNumber(newMobileNumber, oldMobileNumber);
	}

}
