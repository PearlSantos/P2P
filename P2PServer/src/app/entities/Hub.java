package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Hub {
	@Id
	@Column
	private String hubId;
	@Column
	@NotNull
	private String nameOfPlace;
	
	public String getHubId() {
		return hubId;
	}
	public void setHubId(String hubId) {
		this.hubId = hubId;
	}
	public String getNameOfPlace() {
		return nameOfPlace;
	}
	public void setNameOfPlace(String nameOfPlace) {
		this.nameOfPlace = nameOfPlace;
	}
	
	
}
