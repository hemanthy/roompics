package com.mobile.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.dao.MobileDAO;
import com.mobile.dao.MobileDAOImpl;
import com.mobile.model.Company;
import com.mobile.model.Mobile;
import com.mobile.model.Person;
import com.mobile.vo.AutoCompleteMobileVo;
import com.mobile.vo.DataMobileVO;
import com.mobile.vo.MobileConstants;

public class MobileServiceImpl implements MobileService {
	
	public MobileServiceImpl(){
		MobileDAO =  new MobileDAOImpl();
	}
	
	private MobileDAO MobileDAO;

	public void setMobileDAO(MobileDAO MobileDAO) {
		this.MobileDAO = MobileDAO;
	}

	@Override
	public void addPerson(Person p) {
		this.MobileDAO.addPerson(p);
	}

	@Override
	public void updatePerson(Person p) {
		this.MobileDAO.updatePerson(p);
	}

	@Override
	public List<Person> listPersons() {
		return this.MobileDAO.listPersons();
	}

	@Override
	public Person getPersonById(int id) {
		return this.MobileDAO.getPersonById(id);
	}

	@Override
	public void removePerson(int id) {
		this.MobileDAO.removePerson(id);
	}

	@Override
	public void addCompany(Company c) {
		this.MobileDAO.addCompany(c);
	}

	@Override
	public void updateCompany(Company c) {
		this.MobileDAO.updateCompany(c);		
	}

	@Override
	public List<Company> listCompanies() {
		return this.MobileDAO.listCompanies();
	}

	@Override
	public Mobile getMobileById(Integer id) {
		return this.MobileDAO.getMobileById(id);
	}

	@Override
	public List<Mobile> getLatestMobile(){
		return this.MobileDAO.getLatestMobile();
	}


	@Override
	 
	public List<Mobile> getTopBrandMobileList() {
		return this.MobileDAO.getTopBrandMobileList();
	}

	@Override
	 
	public List<Company> getCompaniesList() {
		return this.MobileDAO.getCompaniesList();
	}

	@Override
	 
	public List<Mobile> getRelatedMobilesList(Mobile mobile) {
		return this.MobileDAO.getRelatedMobilesList(mobile);
	}

	@Override
	 
	public Company getBrandByName(String brandName) {
		return this.MobileDAO.getBrandByName(brandName);
	}

	@Override
	 
	public List<Mobile> getMobilesByRamSize(Integer ramSize) {
		return this.MobileDAO.getMobilesByRamSize(ramSize);
	}

	@Override
	 
	public List<Mobile> getMobilesByScreenSize(Integer screenSize) {
		return this.MobileDAO.getMobilesByScreenSize(screenSize);
	}

	@Override
	 
	public List<Mobile> getMobilesBySearchCatageory(String[] catageory) {
		Map<String,String[]> catagoryUrl = new HashMap<String, String[]>();
		for (String url : catageory) {
			if(url.contains(MobileConstants.BRAND)){
				extractCatageryName(catagoryUrl, url,MobileConstants.BRAND);
			}
			if(url.contains(MobileConstants.SIM)){
				extractCatageryName(catagoryUrl, url,MobileConstants.SIM);
			}
			if(url.contains(MobileConstants.RAM)){
				extractCatageryName(catagoryUrl, url,MobileConstants.RAM);
			}
			if(url.contains(MobileConstants.PRIMARY_CAMERA)){
				extractCatageryName(catagoryUrl, url,MobileConstants.PRIMARY_CAMERA);
			}
			if(url.contains(MobileConstants.SECONDARY_CAMERA)){
				extractCatageryName(catagoryUrl, url,MobileConstants.SECONDARY_CAMERA);
			}
			if(url.contains(MobileConstants.SCREEN_SIZE)){
				extractCatageryName(catagoryUrl, url,MobileConstants.SCREEN_SIZE);
			}
			if(url.contains(MobileConstants.SIM)){
				extractCatageryName(catagoryUrl, url,MobileConstants.SIM);
			}
			if(url.contains(MobileConstants.AVAILABILITY)){
				extractCatageryName(catagoryUrl, url,MobileConstants.AVAILABILITY);
			}
		}
		return this.MobileDAO.getMobilesBySearchCatageory(catagoryUrl);
	}

	private void extractCatageryName(Map<String, String[]> catagoryUrl, String url,String mobileConstants) {
		if(url.contains("_")){
			// Multiple brand selected
			String substring = url.substring(mobileConstants.length()+1);
			String[] spec = substring.split("_");
			catagoryUrl.put(mobileConstants, spec);
		}else{
			// Single brand selected
			String substring = url.substring(mobileConstants.length()+1);
			String[] spec = new String[1];
			spec[0] =  substring;
			catagoryUrl.put(mobileConstants, spec);
		}
	}

	@Override
	public DataMobileVO getMobileDetailsByQueryString(String queryString) {
		DataMobileVO dataMobileVO = new DataMobileVO();
		List<AutoCompleteMobileVo> autoCompleteMobileVOList = new ArrayList<AutoCompleteMobileVo>();
		if(queryString!=null && queryString.trim().length()>=1){
			dataMobileVO.setQueryString(queryString.trim());
			List<Mobile> mobileList = this.MobileDAO.getMobileDetailsByQueryString(queryString.trim());
			if(mobileList!=null && !mobileList.isEmpty()){
				for (Mobile mobile : mobileList) {
					AutoCompleteMobileVo autoCompleteMobileVo = new AutoCompleteMobileVo();
					autoCompleteMobileVo.setTitle(mobile.getTitle());
					autoCompleteMobileVo.setMobileId(mobile.getId());
					autoCompleteMobileVo.setName(mobile.getBrandName());
					autoCompleteMobileVo.setLabel(mobile.getTitle());
					autoCompleteMobileVo.setUrlTitle(mobile.getTitle().replaceAll(" ", "-").toLowerCase());
					autoCompleteMobileVOList.add(autoCompleteMobileVo);
				}
				dataMobileVO.setMobileVoList(autoCompleteMobileVOList);;
			}
		}
		
		return dataMobileVO;
	}
	
	@Override
	public List<Mobile> getUpcomingMobileList() {
		return this.MobileDAO.getUpcomingMobileList();
	}

	@Override
	public List<Mobile> getMobilesBySecondaryCamera(Integer secondaryCamera) {
		return this.MobileDAO.getMobilesBySecondaryCamera(secondaryCamera);
	}

	@Override
	public List<Mobile> getDualMobiles() {
		return this.MobileDAO.getDualMobiles();
	}

	@Override
	public Mobile getMobileByName(String mobileName) {
        return this.MobileDAO.getMobileByName(mobileName);
    }
	
	@Override
	 public Company getCompanyById(int id) {
	        return this.MobileDAO.getCompanyById(id);
	    }

	@Override
	public List<Mobile> getMobilesByCompanyId(int id) {
		 return this.MobileDAO.getMobilesByCompanyId(id);
	}

	@Override
	public List<Mobile> getMobilesByBrandName(String brandName) {
		 return this.MobileDAO.getMobilesByBrandName(brandName);
	}
}
