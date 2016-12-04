package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Bus;
import app.entities.LSStudent;

@Repository
public interface LSStudentRepository extends JpaRepository<LSStudent, Long>{

}
