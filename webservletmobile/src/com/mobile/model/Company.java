package com.mobile.model;

import java.util.Set;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author Hemanth
 *
 */
public class Company {

	private int id;

	private String brandName;

	private boolean enabled;

	private Set<Mobile> mobile;
	
	

	public Company(int id, String brandName, boolean enabled, Set<Mobile> mobile) {
		this.id = id;
		this.brandName = brandName;
		this.enabled = enabled;
		this.mobile = mobile;
	}
	
	public Company() {
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public Set<Mobile> getMobile() {
		return mobile;
	}



	public void setMobile(Set<Mobile> mobile) {
		this.mobile = mobile;
	}



	@Override
	public String toString() {
		return "id=" + id + ", name=" + brandName;
	}
}
