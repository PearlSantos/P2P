package app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Director;
import app.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>
{
//	@Modifying
//	@Transactional
//	@Query("delete from Movie m where m.title like '%Harry Potter%'")
//	public void killPotter();
	
	
	// add this method to allow for paging
	public Page<Movie> findAll(Pageable p);

//	public List<Movie> findByDirector(Director d);
	public Movie findByTitle(String title);
}
