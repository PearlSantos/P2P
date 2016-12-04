package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Bus;
import app.entities.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>{
	public Route findByRouteName(String routeName);
}
