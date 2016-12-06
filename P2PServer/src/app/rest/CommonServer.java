package app.rest;

import java.util.List;

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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String viewTripDetails(@FormParam("tripName") String tripName){
		Trip t = tripRepo.findByTripName(tripName);
		return t.toString();
	}
	
	@POST
	@Path("/updateMobileNumber")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updatePassengerMobileNumber(@FormParam("oldMobileNumber") String oldMobileNumber, @FormParam("newMobileNumber") String newMobileNumber){
		passengerRepo.setMobileNumber(newMobileNumber, oldMobileNumber);
	}
	
	@POST
	@Path("/retrieveAllTrips")
	public String retrieveAllTrips(){
		List<Trip> trips = tripRepo.findAll();
		String result = "";
		for(int i=0; i < trips.size(); i++){
			if(i == trips.size()-1){
				result = result + trips.get(i).getTripName();
			}
			else{
				result = result + trips.get(i).getTripName() + ",";
			}
		}
		return result;
	}

}
