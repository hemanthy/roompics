package com.mobile.dao;

import java.util.List;
import java.util.Map;

import com.mobile.model.Company;
import com.mobile.model.Mobile;
import com.mobile.model.Person;

public interface MobileDAO {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
	public void addCompany(Company c);
	public void updateCompany(Company c);
	public List<Company> listCompanies();
	public Mobile getMobileById(Integer id);
	public List<Mobile> getLatestMobile();
	public List<Mobile> getTopBrandMobileList();
	public List<Company> getCompaniesList();
	public List<Mobile> getRelatedMobilesList(Mobile mobile);
	public Company getBrandByName(String brandName);
	public List<Mobile> getMobilesByRamSize(Integer ramSize);
	public List<Mobile> getMobilesByScreenSize(Integer screenSize);
	public List<Mobile> getMobilesBySearchCatageory(Map<String, String[]> catagoryUrl);
	public List<Mobile> getMobileDetailsByQueryString(String queryString);
	public List<Mobile> getUpcomingMobileList();
}
