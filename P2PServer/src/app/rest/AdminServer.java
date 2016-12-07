package app.rest;


import java.util.List;

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
	@Produces(MediaType.TEXT_PLAIN)
	public String viewPassengerInfo(@FormParam("mobileNumber") String mobileNumber){
		Passenger p = passengerRepo.findByMobileNumber(mobileNumber);
//		System.out.println(p.getFullName() + " yehey");
		return p.toString();
		
		
		
//		MyPassenger mp = new MyPassenger();
//		mp.setFullName(p.getFullName());
//		mp.setPassword(p.getPassword());
//		mp.setBarangay(p.getBarangay());
//		mp.setCity(p.getCity());
//		mp.setEmailAddress(p.getEmailAddress());
//		mp.setMobileNumber(p.getMobileNumber());
//		mp.setTripAssigned(p.getTripAssigned().getTripName());
//		mp.setZipCode(p.getZipCode());
//		return mp;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/deletePassenger")
	public void deletePassenger(@FormParam("mobileNumber") String mobileNumber){
		System.out.println(mobileNumber);
		passengerRepo.deletePassengerByMobileNumber(mobileNumber);
//		Passenger p = passengerRepo.findByMobileNumber(mobileNumber);
//		passengerRepo.delete(p);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/createTrip")
	public void createTrip(@FormParam("tripName") String tripName, @FormParam("ETD") String ETD, @FormParam("ETA") String ETA,
			@FormParam("routeName") String routeName, @FormParam("plateNumber") String plateNumber){
		Bus b = busRepo.findByPlateNumber(plateNumber);
		Route r = routeRepo.findByRouteName(routeName);
		Trip t = new Trip();
//		tripName, ETD, ETA, r, b
		t.setTripName(tripName);
		t.setETD(ETD);
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
	public void deleteTrip(@FormParam("tripName") String tripName){
		System.out.println(tripName);
		tripRepo.deleteTripByTripName(tripName);
	}
	
	
	@POST
	@Path("/retrieveAllPassengers")
	public String retrieveAllPassengers(){
		List<Passenger> pass = passengerRepo.findAll();
		String result = "";
		for(int i=0; i < pass.size(); i++){
			if(i == pass.size()-1){
				result = result + pass.get(i).getMobileNumber();
			}
			else{
				result = result + pass.get(i).getMobileNumber() + ",";
			}
		}
		//System.out.println(result);
		return result;
	}
	
	@POST
	@Path("/retrieveAllRoutes")
	public String retrieveAllRoutes(){
		List<Route> route = routeRepo.findAll();
		String result = "";
		for(int i=0; i < route.size(); i++){
			if(i == route.size()-1){
				result = result + route.get(i).getRouteName();
			}
			else{
				result = result + route.get(i).getRouteName() + ",";
			}
		}
		System.out.println("RESULT AGGGHHH " + result);
		return result;
	}
	
	@POST
	@Path("/retrieveAllBuses")
	public String retrieveAllBuses(){
		List<Bus> bus = busRepo.findAll();
		String result = "";
		for(int i=0; i < bus.size(); i++){
			if(i == bus.size()-1){
				result = result + bus.get(i).getPlateNumber();
			}
			else{
				result = result + bus.get(i).getPlateNumber() + ",";
			}
		}
		return result;
	}
	
//	@POST
//	@Path("/sendUpdates")
//	public void sendUpdates(@FormParam("tripName") String message, @FormParam("tripName") String[] mobileNumbers){
//		//OMG Cel paano to
//	}


}
