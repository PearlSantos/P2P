package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Bus;
import app.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
