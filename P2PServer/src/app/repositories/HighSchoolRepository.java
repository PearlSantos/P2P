package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Bus;
import app.entities.HighSchool;

@Repository
public interface HighSchoolRepository extends JpaRepository<HighSchool, Long>{

}
