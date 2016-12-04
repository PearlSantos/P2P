package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.Bus;
import app.entities.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long>{
	public Trip findByTripName(String tripName);
	
	@Modifying
	@Query("update Trip t set t.ETA = ?1 where t.tripName = ?2")
	int setETA(String ETA, String tripName);
	
	@Modifying
	@Query("update Trip t set t.ETD = ?1 where t.tripName = ?2")
	int setETD(String ETD, String tripName);

}