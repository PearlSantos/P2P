package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Employee extends Passenger {
	@Column
	private String unit;
	@Column
	private String department;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}
