package ru.relex.pt.api.webservices.passway;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="process")
public class PassProcessInfo {
	private long id;
	private String status; /* work, away, absent, ignore */
	private int absentTimeMin;
	private String absentType;
	
	public PassProcessInfo() {
	
	}
	
	public PassProcessInfo(long id, String status, String absentType,
			int absentTimeMin) {
		super();
		this.id = id;
		this.status = status;
		this.absentType = absentType;
		this.absentTimeMin = absentTimeMin;
	}

	@XmlElement
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@XmlElement
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@XmlElement
	public int getAbsentTimeMin() {
		return absentTimeMin;
	}
	
	public void setAbsentTimeMin(int absentTimeMin) {
		this.absentTimeMin = absentTimeMin;
	}
	
	@XmlElement
	public String getAbsentType() {
		return absentType;
	}
	public void setAbsentType(String absentType) {
		this.absentType = absentType;
	}
}
