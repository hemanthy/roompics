/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.mobilestree.mobile.AutoCompleteMobileVo
 *  com.mobilestree.mobile.DataMobileVO
 *  com.mobilestree.mobile.common.CommonHelper
 *  com.mobilestree.mobile.dao.MobileDAO
 *  com.mobilestree.mobile.model.Company
 *  com.mobilestree.mobile.model.Mobile
 *  com.mobilestree.mobile.model.Person
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.mobilestree.mobile.service;

import com.mobilestree.mobile.AutoCompleteMobileVo;
import com.mobilestree.mobile.DataMobileVO;
import com.mobilestree.mobile.common.CommonHelper;
import com.mobilestree.mobile.dao.MobileDAO;
import com.mobilestree.mobile.model.Company;
import com.mobilestree.mobile.model.Mobile;
import com.mobilestree.mobile.model.Person;
import com.mobilestree.mobile.service.MobileService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MobileServiceImpl implements MobileService {
	
    private MobileDAO MobileDAO;

    public void setMobileDAO(MobileDAO MobileDAO) {
        this.MobileDAO = MobileDAO;
    }

    @Transactional
    @Override
    public void addPerson(Person p) {
        this.MobileDAO.addPerson(p);
    }

    @Transactional
    @Override
    public void updatePerson(Person p) {
        this.MobileDAO.updatePerson(p);
    }

    @Transactional
    @Override
    public List<Person> listPersons() {
        return this.MobileDAO.listPersons();
    }

    @Transactional
    @Override
    public Person getPersonById(int id) {
        return this.MobileDAO.getPersonById(id);
    }

    @Transactional
    @Override
    public void removePerson(int id) {
        this.MobileDAO.removePerson(id);
    }

    @Transactional
    @Override
    public void addCompany(Company c) {
        this.MobileDAO.addCompany(c);
    }

    @Override
    public void updateCompany(Company c) {
        this.MobileDAO.updateCompany(c);
    }

    @Transactional
    @Override
    public List<Company> listCompanies() {
        return this.MobileDAO.listCompanies();
    }

    @Transactional
    @Override
    public List<Mobile> getMobilesList(int id) {
        return this.MobileDAO.getMobilesList(id);
    }

    @Transactional
    @Override
    public Mobile getMobileById(Integer id) {
        return this.MobileDAO.getMobileById(id);
    }

    @Transactional
    @Override
    public List<Mobile> getLatestMobile() {
        return this.MobileDAO.getLatestMobile();
    }

    @Transactional
    @Override
    public List<Mobile> getTopBrandMobileList() {
        return this.MobileDAO.getTopBrandMobileList();
    }

    @Transactional
    @Override
    public List<Company> getCompaniesList() {
        return this.MobileDAO.getCompaniesList();
    }

    @Transactional
    @Override
    public Company getCompanyById(int id) {
        return this.MobileDAO.getCompanyById(id);
    }

    @Transactional
    @Override
    public List<Mobile> getRelatedMobilesList(Mobile mobile) {
        return this.MobileDAO.getRelatedMobilesList(mobile);
    }

    @Transactional
    @Override
    public Company getBrandByName(String brandName) {
        return this.MobileDAO.getBrandByName(brandName);
    }

    @Transactional
    @Override
    public List<Mobile> getMobilesByRamSize(Integer ramSize) {
        return this.MobileDAO.getMobilesByRamSize(ramSize);
    }

    @Transactional
    @Override
    public List<Mobile> getMobilesByScreenSize(Integer screenSize) {
        return this.MobileDAO.getMobilesByScreenSize(screenSize);
    }

    @Transactional
    @Override
    public List<Mobile> getMobilesBySearchCatageory(String[] catageory) {
        HashMap<String, String[]> catagoryUrl = new HashMap<String, String[]>();
        for (String url : catageory) {
            if (url.contains((CharSequence)"brand")) {
                CommonHelper.extractCatageryName(catagoryUrl, (String)url, (String)"brand");
            }
            if (url.contains((CharSequence)"sim")) {
                CommonHelper.extractCatageryName(catagoryUrl, (String)url, (String)"sim");
            }
            if (url.contains((CharSequence)"ram")) {
                CommonHelper.extractCatageryName(catagoryUrl, (String)url, (String)"ram");
            }
            if (url.contains((CharSequence)"primary-camera")) {
                CommonHelper.extractCatageryName(catagoryUrl, (String)url, (String)"primary-camera");
            }
            if (url.contains((CharSequence)"secondary-camera")) {
                CommonHelper.extractCatageryName(catagoryUrl, (String)url, (String)"secondary-camera");
            }
            if (url.contains((CharSequence)"screen-size")) {
                CommonHelper.extractCatageryName(catagoryUrl, (String)url, (String)"screen-size");
            }
            if (url.contains((CharSequence)"sim")) {
                CommonHelper.extractCatageryName(catagoryUrl, (String)url, (String)"sim");
            }
            if (url.contains((CharSequence)"availability")) {
                CommonHelper.extractCatageryName(catagoryUrl, (String)url, (String)"availability");
            }
            if (url.contains((CharSequence)"internal-memory")) {
                CommonHelper.extractCatageryName(catagoryUrl, (String)url, (String)"internal-memory");
            }
            if (!url.contains((CharSequence)"external-memory")) continue;
            CommonHelper.extractCatageryName(catagoryUrl, (String)url, (String)"external-memory");
        }
        List<Mobile> sortedMobileList = this.sortMobileListBasedUrl(catagoryUrl);
        return sortedMobileList;
    }

    private List<Mobile> sortMobileListBasedUrl(Map<String, String[]> catagoryUrl) {
        String[] brandList = catagoryUrl.get("brand");
        ArrayList<Mobile> sortedMobileList = new ArrayList<Mobile>();
        List<Mobile> mobileList = this.MobileDAO.getMobilesBySearchCatageory(catagoryUrl);
        if (brandList != null) {
            LinkedHashMap mobileMap = new LinkedHashMap();
            if (brandList != null) {
                for (String brandName : brandList) {
                    mobileMap.put(brandName, new ArrayList());
                }
            }
            for (Mobile mobile : mobileList) {
                List mapmobileList = (List)mobileMap.get(mobile.getBrandName().toLowerCase());
                mapmobileList.add(mobile);
            }
            for (int i = brandList.length - 1; i >= 0; --i) {
                sortedMobileList.addAll((Collection)mobileMap.get(brandList[i]));
            }
        } else {
            return mobileList;
        }
        return sortedMobileList;
    }

    @Transactional
    @Override
    public DataMobileVO getMobileDetailsByQueryString(String queryString) {
        DataMobileVO dataMobileVO = new DataMobileVO();
        ArrayList<AutoCompleteMobileVo> autoCompleteMobileVOList = new ArrayList<AutoCompleteMobileVo>();
        if (queryString != null && queryString.trim().length() >= 1) {
            dataMobileVO.setQueryString(queryString.trim());
            List<Mobile> mobileList = this.MobileDAO.getMobileDetailsByQueryString(queryString.trim());
            if (!(mobileList == null || mobileList.isEmpty())) {
                for (Mobile mobile : mobileList) {
                    AutoCompleteMobileVo autoCompleteMobileVo = new AutoCompleteMobileVo();
                    autoCompleteMobileVo.setTitle(mobile.getTitle());
                    autoCompleteMobileVo.setMobileId(mobile.getId());
                    autoCompleteMobileVo.setName(mobile.getBrandName());
                    autoCompleteMobileVo.setLabel(mobile.getTitle());
                    autoCompleteMobileVo.setUrlTitle(mobile.getTitle().replaceAll(" ", "-").toLowerCase());
                    autoCompleteMobileVOList.add(autoCompleteMobileVo);
                }
                dataMobileVO.setMobileVoList(autoCompleteMobileVOList);
            }
        }
        return dataMobileVO;
    }

    @Transactional
    @Override
    public List<Mobile> getUpcomingMobileList() {
        return this.MobileDAO.getUpcomingMobileList();
    }

    @Transactional
    @Override
    public List<Mobile> getMobilesBySecondaryCamera(Integer secondaryCamera) {
        return this.MobileDAO.getMobilesBySecondaryCamera(secondaryCamera);
    }

    @Transactional
    @Override
    public List<Mobile> getDualMobiles() {
        return this.MobileDAO.getDualMobiles();
    }

    @Transactional
    @Override
    public Mobile getMobileByName(String mobileName) {
        return this.MobileDAO.getMobileByName(mobileName);
    }
}