package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Employee;
import app.entities.Passenger;
import app.entities.Trip;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{

	public Passenger findByMobileNumber(String mobileNumber);
	
	@Transactional
	@Modifying
	@Query("delete from Passenger p where p.mobileNumber =?1")
	public void deletePassengerByMobileNumber(String mobileNumber);
	
	@Transactional
	@Modifying
	@Query("update Passenger p set p.tripAssigned = ?1 where p.mobileNumber = ?2")
	int setTrip(Trip t, String mobileNumber);
	
	@Transactional
	@Modifying
	@Query("update Passenger p set p.mobileNumber = ?1 where p.mobileNumber = ?2")
	int setMobileNumber(String newMobileNumber, String mobileNumber);
}


