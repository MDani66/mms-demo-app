package com.tsystems.mms.demoapp.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "org_unit")
public class OrganisationalUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_unit_id", nullable = false)
	protected Long id;
	
	
	@OneToMany(mappedBy = "organisationalUnit")
	@JsonIgnore
	private List<User> users = new ArrayList<User>();


	/**
	 * 
	 */
	public OrganisationalUnit() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	@Override
	public String toString() {
		return "OrganisationalUnit [id=" + id + "]";
	}
	
	

}
