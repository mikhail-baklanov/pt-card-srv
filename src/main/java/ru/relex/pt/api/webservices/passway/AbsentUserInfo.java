package ru.relex.pt.api.webservices.passway;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AbsentUserInfo {
	private long userId;
	private String firstName;
	private String lastName;
	private String middleName;
	private long passTime;
	private long absentTimeMin;
	private String absentType;
	
	public AbsentUserInfo()
	{
		
	}
	public AbsentUserInfo(long userId, String firstName, String lastName,
			String middleName, long passTime, long absentTime, String absentType) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.passTime = passTime;
		this.absentTimeMin = absentTime;
		this.absentType = absentType;
	}

	@XmlElement
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@XmlElement
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public long getPassTime() {
		return passTime;
	}

	public void setPassTime(long passTime) {
		this.passTime = passTime;
	}

	@XmlElement
	public long getAbsentTime() {
		return absentTimeMin;
	}

	public void setAbsentTime(long absentTime) {
		this.absentTimeMin = absentTime;
	}

	@XmlElement
	public String getAbsentType() {
		return absentType;
	}

	public void setAbsentType(String absentType) {
		this.absentType = absentType;
	}
	
}
