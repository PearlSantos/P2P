package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LSStudent extends Passenger {
	@Column
	private String yearAndSection;

	public String getYearAndSection() {
		return yearAndSection;
	}

	public void setYearAndSection(String yearAndSection) {
		this.yearAndSection = yearAndSection;
	}
}
