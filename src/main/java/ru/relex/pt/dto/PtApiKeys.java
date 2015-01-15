package ru.relex.pt.dto;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class PtApiKeys {
	private Long id;
	private PtUsersDTO user;
	private Date expirationDate;
	private String key;
	
	public PtApiKeys(Long id, PtUsersDTO user, Date expirationDate, String key) {
		super();
		this.id = id;
		this.user = user;
		this.expirationDate = expirationDate;
		this.key = key;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PtUsersDTO getUser() {
		return user;
	}
	public void setUser(PtUsersDTO user) {
		this.user = user;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
