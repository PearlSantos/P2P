package app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movie 
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long id;
	
	@Column
	private Integer rank; 
	
	@Column
	private Double gross; 
	
	@Column
	private String title; 

	@Column
	private Integer date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Double getGross() {
		return gross;
	}

	public void setGross(Double gross) {
		this.gross = gross;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("Movie [id=%s, rank=%s, gross=%.2f, title=%s, date=%s]", id, rank, gross, title, date);
	} 
	
	

	
}
