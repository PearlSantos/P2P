package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Bus;
import app.entities.Hub;

@Repository
public interface HubsRepository extends JpaRepository<Hub, Long>{
	public Hub findByNameOfPlace(String nameOfPlace);
}
