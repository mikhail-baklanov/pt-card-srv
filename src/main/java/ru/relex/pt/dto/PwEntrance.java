package ru.relex.pt.dto;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class PwEntrance {
	private Long id;
	private String cardId;
	private Date passTime;
	private PtUsersDTO user;
	private String direction;
	private String status; /* active, completed, ignored*/
	
	public PwEntrance(Long id, String cardId, Date passTime, PtUsersDTO user,
			String direction) {
		super();
		this.id = id;
		this.cardId = cardId;
		this.passTime = passTime;
		this.user = user;
		this.direction = direction;
		this.status = "active";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Date getPassTime() {
		return passTime;
	}
	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}
	public PtUsersDTO getUser() {
		return user;
	}
	public void setUser(PtUsersDTO user) {
		this.user = user;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
