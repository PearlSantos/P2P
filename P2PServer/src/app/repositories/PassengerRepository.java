package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.Employee;
import app.entities.Passenger;
import app.entities.Trip;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{

	public Passenger findByMobileNumber(String mobileNumber);
	
	@Query("delete from Passenger p where p.mobileNumber =?1")
	public void deletePassenger(String mobileNumber);
	
	@Modifying
	@Query("update Passenger p set p.tripAssigned = ?1 where p.mobileNumber = ?2")
	int setTrip(Trip t, String mobileNumber);
	
	@Modifying
	@Query("update Passenger p set p.mobileNumber = ?1 where p.mobileNumber = ?2")
	int setMobileNumber(String newMobileNumber, String mobileNumber);
}
