package app.components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Bus;
import app.entities.Hub;
import app.entities.Passenger;
import app.entities.Route;
import app.entities.Trip;
import app.repositories.BusRepository;
import app.repositories.HubsRepository;
import app.repositories.PassengerRepository;
import app.repositories.RouteRepository;
import app.repositories.TripRepository;

@Component
public class Loader {
	
	@Autowired
	private PassengerRepository passengerRepo;
	@Autowired
	private BusRepository busRepo;
	@Autowired
	private RouteRepository routeRepo;
	@Autowired
	private TripRepository tripRepo;
	@Autowired
	private HubsRepository hubRepo;
	
	@PostConstruct
	public void load() throws Exception
	{
		
		
		if(busRepo.count()==0){
			FileReader fr = new FileReader("busPopulator.csv");
			BufferedReader br = new BufferedReader(fr);
			
			String currentLine = "";
			
			while ((currentLine = br.readLine())!=null)
			{
				// NOTE: this will fail for The Lion, the Witch and the Wardrobe ... I will ignore :)
				String[] data = currentLine.split(",");
				
				Bus b = new Bus();
				b.setPlateNumber(data[0]);
				b.setCapacity(Integer.parseInt(data[1]));
				b.setNumOfAvailableSeats(Integer.parseInt(data[2]));
				
				
				busRepo.save(b);
		}
			
		
		
		}
		
		if(hubRepo.count()==0){
			FileReader fr = new FileReader("hubPopulator.csv");
			BufferedReader br = new BufferedReader(fr);
			
			String currentLine = "";
			
			while ((currentLine = br.readLine())!=null)
			{
				// NOTE: this will fail for The Lion, the Witch and the Wardrobe ... I will ignore :)
				String[] data = currentLine.split(",");
				
				Hub h = new Hub();
				h.setHubId(data[0]);
				h.setNameOfPlace(data[1]);
//				
//				
//				r.setNumOfAvailableSeats(Integer.parseInt(data[2]));
				
				
				hubRepo.save(h);
		}
			
}
		
		if(routeRepo.count()==0){
			FileReader fr = new FileReader("routePopulator.csv");
			BufferedReader br = new BufferedReader(fr);
			
			String currentLine = "";
			
			while ((currentLine = br.readLine())!=null)
			{
				// NOTE: this will fail for The Lion, the Witch and the Wardrobe ... I will ignore :)
				String[] data = currentLine.split(",");
				
				Route r = new Route();
				r.setRouteId(data[0]);
				r.setRouteName(data[1]);
				Hub startingHub = hubRepo.findByNameOfPlace(data[2]);
				Hub endingHub = hubRepo.findByNameOfPlace(data[3]);
				r.setStartingHub(startingHub);
				r.setEndingHub(endingHub);
//				
//				
//				r.setNumOfAvailableSeats(Integer.parseInt(data[2]));
				
				
				routeRepo.save(r);
			}
		}
			
			
			if(tripRepo.count()==0){
				FileReader fr = new FileReader("tripPopulator.csv");
				BufferedReader br = new BufferedReader(fr);
				
				String currentLine = "";
				
				while ((currentLine = br.readLine())!=null)
				{
					// NOTE: this will fail for The Lion, the Witch and the Wardrobe ... I will ignore :)
					String[] data = currentLine.split(",");
					
					Trip t = new Trip();
					t.setTripName(data[0]);
					t.setETD(data[1]);
					t.setETA(data[2]);
					Route r = routeRepo.findByRouteName(data[3]);
					Bus b = busRepo.findByPlateNumber(data[4]);
					t.setAssignedRoute(r);
					t.setAssignedBus(b);
//					
//					
//					r.setNumOfAvailableSeats(Integer.parseInt(data[2]));
					
					
					tripRepo.save(t);
			}
	}
			
			if (passengerRepo.count()==0)
			{
				System.out.println("loading...");
				// load stuff
				
				FileReader fr = new FileReader("passengerPopulator.csv");
				BufferedReader br = new BufferedReader(fr);
				
				String currentLine = "";
				
				while ((currentLine = br.readLine())!=null)
				{
					// NOTE: this will fail for The Lion, the Witch and the Wardrobe ... I will ignore :)
					String[] data = currentLine.split(",");
					
					Passenger p = new Passenger();
					p.setFullName(data[0]);
					p.setPassword(data[1]);
					p.setMobileNumber(data[2]);
					p.setEmailAddress(data[3]);
					p.setBarangay(data[4]);
					p.setCity(data[5]);
					p.setZipCode(data[6]);
					
					
					passengerRepo.save(p);
				}
			}
//			tester code
//			String mobileNumber = "9213465469";
//			
//			Passenger p = passengerRepo.findByMobileNumber(mobileNumber);
//			System.out.println(p.toString());
//			
//			
	}
	
}
				

	



