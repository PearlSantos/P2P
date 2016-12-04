package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class HighSchool extends Passenger{
	@Column
	@NotNull
	private String ateneoSchool;
	@Column
	@NotNull
	private String yearLevelAndSection;
	@Column
	@NotNull
	private String nameOfParent;
	@Column
	@NotNull
	private String mobileNumberOfParent;
	@Column
	@NotNull
	private String emailAddOfParent;
	public String getAteneoSchool() {
		return ateneoSchool;
	}
	public void setAteneoSchool(String ateneoSchool) {
		this.ateneoSchool = ateneoSchool;
	}
	public String getYearLevelAndSection() {
		return yearLevelAndSection;
	}
	public void setYearLevelAndSection(String yearLevelAndSection) {
		this.yearLevelAndSection = yearLevelAndSection;
	}
	public String getNameOfParent() {
		return nameOfParent;
	}
	public void setNameOfParent(String nameOfParent) {
		this.nameOfParent = nameOfParent;
	}
	public String getMobileNumberOfParent() {
		return mobileNumberOfParent;
	}
	public void setMobileNumberOfParent(String mobileNumberOfParent) {
		this.mobileNumberOfParent = mobileNumberOfParent;
	}
	public String getEmailAddOfParent() {
		return emailAddOfParent;
	}
	public void setEmailAddOfParent(String emailAddOfParent) {
		this.emailAddOfParent = emailAddOfParent;
	}
	
}
