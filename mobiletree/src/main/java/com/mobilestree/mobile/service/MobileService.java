/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.mobilestree.mobile.DataMobileVO
 *  com.mobilestree.mobile.model.Company
 *  com.mobilestree.mobile.model.Mobile
 *  com.mobilestree.mobile.model.Person
 */
package com.mobilestree.mobile.service;

import com.mobilestree.mobile.DataMobileVO;
import com.mobilestree.mobile.model.Company;
import com.mobilestree.mobile.model.Mobile;
import com.mobilestree.mobile.model.Person;
import java.util.List;

public interface MobileService {
    public void addPerson(Person var1);

    public void updatePerson(Person var1);

    public List<Person> listPersons();

    public Person getPersonById(int var1);

    public void removePerson(int var1);

    public void addCompany(Company var1);

    public void updateCompany(Company var1);

    public List<Company> listCompanies();

    public List<Mobile> getMobilesList(int var1);

    public Mobile getMobileById(Integer var1);

    public List<Mobile> getLatestMobile();

    public List<Mobile> getTopBrandMobileList();

    public List<Company> getCompaniesList();

    public Company getCompanyById(int var1);

    public List<Mobile> getRelatedMobilesList(Mobile var1);

    public Company getBrandByName(String var1);

    public List<Mobile> getMobilesByRamSize(Integer var1);

    public List<Mobile> getMobilesByScreenSize(Integer var1);

    public List<Mobile> getMobilesBySearchCatageory(String[] var1);

    public DataMobileVO getMobileDetailsByQueryString(String var1);

    public List<Mobile> getUpcomingMobileList();

    public List<Mobile> getMobilesBySecondaryCamera(Integer var1);

    public List<Mobile> getDualMobiles();

    public Mobile getMobileByName(String var1);
}