package app.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Trip {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long tripId;
	@Column
	private String tripName;
	@Column
	private String ETD;
	@Column
	private String ETA;
	@JoinColumn
	@OneToOne
	private Route assignedRoute;
	@OneToOne
	@JoinColumn
	private Bus assignedBus;
	public Long getTripId() {
		return tripId;
	}
	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public String getETD() {
		return ETD;
	}
	public void setETD(String eTD) {
		ETD = eTD;
	}
	public String getETA() {
		return ETA;
	}
	public void setETA(String eTA) {
		ETA = eTA;
	}
	public Route getAssignedRoute() {
		return assignedRoute;
	}
	public void setAssignedRoute(Route assignedRoute) {
		this.assignedRoute = assignedRoute;
	}
	public Bus getAssignedBus() {
		return assignedBus;
	}
	public void setAssignedBus(Bus assignedBus) {
		this.assignedBus = assignedBus;
	}
	@Override
	public String toString() {
		return tripName + "," + ETD + "," + ETA
				+ "," + assignedRoute.getRouteName() + "," + assignedBus.getPlateNumber();
	}
	
	
}
