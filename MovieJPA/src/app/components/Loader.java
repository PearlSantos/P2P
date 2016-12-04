package app.components;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import app.entities.Movie;
import app.repositories.MovieRepository;

@Component
public class Loader {

	@Autowired
	private MovieRepository dao;
	
	
	@PostConstruct
	public void load() throws Exception
	{
		if (dao.count()==0)
		{
			System.out.println("loading...");
			// load stuff
			
			FileReader fr = new FileReader("topmovies.csv");
			BufferedReader br = new BufferedReader(fr);
			
			String currentLine = "";
			
			while ((currentLine = br.readLine())!=null)
			{
				// NOTE: this will fail for The Lion, the Witch and the Wardrobe ... I will ignore :)
				String[] data = currentLine.split(",");
				
				Movie m = new Movie();
				m.setRank(Integer.parseInt(data[0]));
				m.setTitle(data[1]);
				m.setGross(Double.parseDouble(data[2]));
				m.setDate(Integer.parseInt(data[3]));
				
				dao.save(m);
			}
		}
		
		Movie m = dao.findByTitle("Avatar");
		Double gross = m.getGross();
		String result =  m.getTitle() + " grossed $" + gross;
		System.out.println(result);
		

//		// kill potter
//		dao.killPotter();
//		
//		// display top 10
//		Page<Movie> page = dao.findAll(new PageRequest(0, 10, Direction.DESC, "gross"));
//		
//		// NOTE: several different PageRequest constructors
//		
//		
//		for (Movie movie : page.getContent()) // getContent() gives the List<Movie>
//		{
//			System.out.println(movie);
//		}
//		
//		
		
	}
	
}
