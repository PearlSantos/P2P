package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Bus;
import app.entities.Trip;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long>{
	public Bus findByPlateNumber(String plateNumber);
	
	@Transactional
	@Modifying
	@Query("update Bus b set b.numOfAvailableSeats = ?1 where b.plateNumber = ?2")
	int setNumberOfAvailableSeats(int numOfAvailableSeats, String plateNumber);
}
