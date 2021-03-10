package com.capg.movie.capg.movie.booking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    //Fields
	int adminId;
	String adminName;
	String adminContact;
	
	@OneToOne(targetEntity = User.class)
	User user;

	//Constructors
	public Admin() {
		super();
	}
	public Admin(int adminId, String adminName, String adminContact) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminContact = adminContact;
	}
	public Admin(String adminName, String adminContact) {
		super();
		this.adminName = adminName;
		this.adminContact = adminContact;
	}
	
	public Admin(int adminId, String adminName, String adminContact, User user) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminContact = adminContact;
		this.user = user;
	}
	//Getters and setters
	public int getadminId() {
		return adminId;
	}
	public void setadminId(int adminId) {
		this.adminId = adminId;
	}
	
	
	public String getadminName() {
		return adminName;
	}
	public void setadminName(String adminName) {
		this.adminName = adminName;
	}
	public String getadminContact() {
		return adminContact;
	}
	public void setadminContact(String adminContact) {
		this.adminContact = adminContact;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId +", adminName=" + adminName + ", adminContact=" + adminContact + "]";
	}
	
	
    
}