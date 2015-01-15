package ru.relex.pt.api.webservices.passway;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pass")
public class Pass {
	private long id;
	private long userId;
	private String cardId;
	private String direction;
	private String firstName;
	private String lastName;
	private String middleName;
	private long passTime;
	private String status;

	public Pass() {

	}

	public Pass(long id, long userId, String cardId, String direction,
			String firstName, String lastName, String middleName,
			long passTime, String status) {
		super();
		this.id = id;
		this.userId = userId;
		this.cardId = cardId;
		this.direction = direction;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.passTime = passTime;
		this.status = status;
	}

	@XmlElement
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@XmlElement
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@XmlElement
	public long getPassTime() {
		return passTime;
	}

	public void setPassTime(long passTime) {
		this.passTime = passTime;
	}

	@XmlElement
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
