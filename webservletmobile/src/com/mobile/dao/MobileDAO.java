package com.mobile.dao;

import java.util.List;
import java.util.Map;

import com.mobile.exception.MobileException;
import com.mobile.model.Company;
import com.mobile.model.Mobile;
import com.mobile.model.Person;
import java.sql.Connection;

public interface MobileDAO {

	public void addPerson(Person p) throws MobileException ;
	public void updatePerson(Person p) throws MobileException ;
	public List<Person> listPersons() throws MobileException ;
	public Person getPersonById(int id) throws MobileException ;
	public void removePerson(int id) throws MobileException ;
	public void addCompany(Company c)  throws MobileException ;
	public void updateCompany(Company c) throws MobileException ;
	public List<Company> listCompanies() throws MobileException ;
	public Mobile getMobileById(Integer id) throws MobileException ;
	public List<Mobile> getLatestMobile() throws MobileException ;
	public List<Mobile> getTopBrandMobileList() throws MobileException ;
	public List<Company> getCompaniesList() throws MobileException ;
	public List<Mobile> getRelatedMobilesList(Mobile mobile) throws MobileException ;
	public Company getBrandByName(String brandName) throws MobileException ;
	public List<Mobile> getMobilesByRamSize(Integer ramSize) throws MobileException ;
	public List<Mobile> getMobilesByScreenSize(Integer screenSize) throws MobileException ;
	public List<Mobile> getMobilesBySearchCatageory(Map<String, String[]> catagoryUrl) throws MobileException ;
	public List<Mobile> getMobileDetailsByQueryString(String queryString) throws MobileException ;
	public List<Mobile> getUpcomingMobileList() throws MobileException ;
	public Mobile getMobileByName(String mobileName) throws MobileException ;
    public List<Mobile> getMobilesBySecondaryCamera(Integer var1) throws MobileException ;
    public List<Mobile> getDualMobiles()  throws MobileException ;
	public Company getCompanyById(int id) throws MobileException ;
	public List<Mobile> getMobilesByCompanyId(int id) throws MobileException ;
	public List<Mobile> getMobilesByBrandName(String brandName) throws MobileException ;
	public void setConnection(Connection connection);
}
