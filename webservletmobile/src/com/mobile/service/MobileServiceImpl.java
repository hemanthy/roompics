package com.mobile.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.dao.MobileDAO;
import com.mobile.dao.MobileDAOImpl;
import com.mobile.exception.MobileException;
import com.mobile.model.Company;
import com.mobile.model.Mobile;
import com.mobile.model.Person;
import com.mobile.vo.AutoCompleteMobileVo;
import com.mobile.vo.DataMobileVO;
import com.mobile.vo.MobileConstants;
import java.sql.Connection;

public class MobileServiceImpl implements MobileService {
	
	public MobileServiceImpl() throws MobileException{
		MobileDAO =  new MobileDAOImpl();
	}
	
	private MobileDAO MobileDAO;

	public void setMobileDAO(MobileDAO MobileDAO) {
		this.MobileDAO = MobileDAO;
	}

	@Override
	public void addPerson(Person p)  throws MobileException {
		this.MobileDAO.addPerson(p);
	}

	@Override
	public void updatePerson(Person p) throws MobileException {
		this.MobileDAO.updatePerson(p);
	}

	@Override
	public List<Person> listPersons()  throws MobileException {
		return this.MobileDAO.listPersons();
	}

	@Override
	public Person getPersonById(int id)  throws MobileException {
		return this.MobileDAO.getPersonById(id);
	}

	@Override
	public void removePerson(int id)  throws MobileException {
		this.MobileDAO.removePerson(id);
	}

	@Override
	public void addCompany(Company c)  throws MobileException {
		this.MobileDAO.addCompany(c);
	}

	@Override
	public void updateCompany(Company c)  throws MobileException {
		this.MobileDAO.updateCompany(c);		
	}

	@Override
	public List<Company> listCompanies()  throws MobileException {
		return this.MobileDAO.listCompanies();
	}

	@Override
	public Mobile getMobileById(Integer id)  throws MobileException {
		return this.MobileDAO.getMobileById(id);
	}

	@Override
	public List<Mobile> getLatestMobile() throws MobileException {
		return this.MobileDAO.getLatestMobile();
	}


	@Override
	 
	public List<Mobile> getTopBrandMobileList()  throws MobileException {
		return this.MobileDAO.getTopBrandMobileList();
	}

	@Override
	 
	public List<Company> getCompaniesList()  throws MobileException {
		return this.MobileDAO.getCompaniesList();
	}

	@Override
	 
	public List<Mobile> getRelatedMobilesList(Mobile mobile)  throws MobileException {
		return this.MobileDAO.getRelatedMobilesList(mobile);
	}

	@Override
	 
	public Company getBrandByName(String brandName)  throws MobileException {
		return this.MobileDAO.getBrandByName(brandName);
	}

	@Override
	 
	public List<Mobile> getMobilesByRamSize(Integer ramSize)  throws MobileException {
		return this.MobileDAO.getMobilesByRamSize(ramSize);
	}

	@Override
	 
	public List<Mobile> getMobilesByScreenSize(Integer screenSize)  throws MobileException {
		return this.MobileDAO.getMobilesByScreenSize(screenSize);
	}

	@Override
	 
	public List<Mobile> getMobilesBySearchCatageory(String[] catageory)  throws MobileException {
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
	public DataMobileVO getMobileDetailsByQueryString(String queryString)  throws MobileException {
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
	public List<Mobile> getUpcomingMobileList()  throws MobileException {
		return this.MobileDAO.getUpcomingMobileList();
	}

	@Override
	public List<Mobile> getMobilesBySecondaryCamera(Integer secondaryCamera)  throws MobileException {
		return this.MobileDAO.getMobilesBySecondaryCamera(secondaryCamera);
	}

	@Override
	public List<Mobile> getDualMobiles()  throws MobileException {
		return this.MobileDAO.getDualMobiles();
	}

	@Override
	public Mobile getMobileByName(String mobileName)  throws MobileException {
		mobileName =	mobileName.replaceAll("wi fi", "wi-fi").replaceAll("ale l04", "ale-l04").replaceAll("a10 70", "a10-70")
		.replaceAll("a7 30", "a7-30").replaceAll("a7 50", "a7-50").replaceAll("a8 50", "a8-50")
		.replaceAll("3 chip", "3-chip").replaceAll("shv e220", "shv-e220").replaceAll("lte a", "lte-a")
		.replaceAll("octa core", "octa-core").replaceAll("so 03d", "so-03d").replaceAll("so 04d", "so-04d")
		.replaceAll("so 05d", "so-05d");
        return this.MobileDAO.getMobileByName(mobileName);
    }
	
	@Override
	 public Company getCompanyById(int id)  throws MobileException {
	        return this.MobileDAO.getCompanyById(id);
	    }

	@Override
	public List<Mobile> getMobilesByCompanyId(int id)  throws MobileException {
		 return this.MobileDAO.getMobilesByCompanyId(id);
	}

	@Override
	public List<Mobile> getMobilesByBrandName(String brandName)  throws MobileException {
		 return this.MobileDAO.getMobilesByBrandName(brandName);
	}

	@Override
	public void setConnection(Connection connection) throws MobileException {
		this.MobileDAO.setConnection(connection);		
	}
}
