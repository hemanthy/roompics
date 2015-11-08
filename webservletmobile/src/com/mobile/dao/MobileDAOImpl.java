package com.mobile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.model.Company;
import com.mobile.model.Mobile;
import com.mobile.model.Person;
import com.mobile.util.DbUtil;
import com.mobile.vo.MobileConstants;


public class MobileDAOImpl implements MobileDAO {
	

	private Connection connection;

	public MobileDAOImpl() {
		connection = DbUtil.getConnection();
	}
	
	private  List<Mobile> getMobileObjList(String sql,Map<String,String> map){
		PreparedStatement preparedStatement;
		List<Mobile> mobileList =  new ArrayList<Mobile>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Mobile mobile = new Mobile();
				mobile.setId(rs.getInt("id"));
				mobile.setBrandName(rs.getString("Brand_Name"));
				mobileList.add(mobile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		preparedStatement.setInt(1, userId);
		return mobileList;
	}
	
	public List<Company> getCompanyObjList(String sql,Map<String,String> map){
		List<Company> cmpyList =  new ArrayList<Company>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
//		preparedStatement.setInt(1, userId);
		ResultSet rs = preparedStatement.executeQuery();
		
		if (rs.next()) {
			Company company = new Company();
			company.setId(rs.getInt("id"));
			company.setBrandName(rs.getString("brandName"));
			company.setEnabled(rs.getBoolean("brandName"));
			cmpyList.add(company);
			
			/*user.setUserid(rs.getInt("userid"));
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			user.setDob(rs.getDate("dob"));
			user.setEmail(rs.getString("email"));*/
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cmpyList;
	}
	
	@Override
	public void addPerson(Person p) {
	}

	@Override
	public void updatePerson(Person p) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		return null;
	}

	@Override
	public Person getPersonById(int id) {
		return null;
	}

	@Override
	public void removePerson(int id) {
	}
	
	public void addMobile(Mobile m) {
	}
	
	public void adCompany(Company c) {
	}
	
	
	
	
	public void getSession(){
		/*Session session = null;
		try{
			session= this.sessionFactory.getCurrentSession();	
		}catch(Exception e){
			e.getStackTrace();
			session = this.sessionFactory.openSession();
		}*/
		
	}

	@Override
	public void addCompany(Company c) {
	}

	@Override
	public void updateCompany(Company c) {
	}

	@Override
	public List<Company> listCompanies() {
		String sql = "select * from Company";
		List<Company> companyList = getCompanyObjList(sql, null);
		return companyList;
	}

	@Override
	public Mobile getMobileById(Integer id) {
		String sql = "select * from Mobile where id ="+id;
		List<Mobile> mobileList =	getMobileObjList(sql, null);
		if(!mobileList.isEmpty()){
			return mobileList.get(0);
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getLatestMobile(){
		String sql = "select * from Mobile order by Announced_Month desc";
		List<Mobile> mobileList  =	getMobileObjList(sql, null);
		return mobileList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getTopBrandMobileList() {
		String sql = "select * from Mobile where (ram >= 4.7 or Screen_Size >= 5) and (Company_id = 1 or Company_id = 2)";
		List<Mobile> mobileList  =	getMobileObjList(sql, null);
		return mobileList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Company> getCompaniesList() {
		String sql = "select * from Company order by brandName asc";
		List<Company> companiesList  =	getCompanyObjList(sql, null);
		return companiesList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getRelatedMobilesList(Mobile mobile) {
		String sql = "select * from Mobile where (ram = "+ mobile.getRam()+") and (Company_id = 1 or Company_id = 2)";
		List<Mobile> mobileList  =	getMobileObjList(sql, null);
		return mobileList;
	}

	@Override
	public Company getBrandByName(String brandName) {
		String sql = "select * from Company where brandName = "+brandName;
		List<Company> companiesList  =	getCompanyObjList(sql, null);
		if(!companiesList.isEmpty()){
			return companiesList.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getMobilesByRamSize(Integer ramSize) {
		String sql = "select * from Mobile where ram = "+ramSize;
		List<Mobile> mobileList  =	getMobileObjList(sql, null);
		return mobileList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getMobilesByScreenSize(Integer screenSize) {
		String sql = "select * from Mobile where screenSize = "+screenSize;
		List<Mobile> mobileList  =	getMobileObjList(sql, null);
		return mobileList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getMobilesBySearchCatageory(Map<String, String[]> catagoryUrl) {
		Map<String,List<String>> urlMap = new HashMap<String, List<String>>();
		
		StringBuffer sb = new StringBuffer();
		
		extractURLInfo(catagoryUrl, urlMap);

		List<String> brandList = urlMap.get(MobileConstants.BRAND);
		if (brandList != null && brandList.size() > 0 && urlMap.size() == 1) {
			for (String brand : brandList) {
				sb.append(" or ");
				sb.append(" brand_name = '" + brand + "'");
			}
		} else if (brandList != null && brandList.size() > 0 && urlMap.size() >= 1) {
			for (String brand : brandList) {
				sb.append("or ");
				sb.append("( ");
				sb.append(" brand_name = '" + brand + "'");
				/*if (urlMap.size() > 1) {
					sb.append(" and ");
				}*/

				constructFeaturesQuery(urlMap, sb);
				
				sb.append(" ) ");
			}
		}else if(brandList == null){
			sb.append(" or ");
			constructFeaturesQuery(urlMap, sb);
		}
		
		String  queryStr = sb.toString();
		if(queryStr.trim().startsWith("or")){
			int indexOf = queryStr.indexOf("or");
			queryStr = queryStr.substring(indexOf+2,queryStr.length());
		}
		
		if(queryStr.trim().startsWith("and")){
			int index = queryStr.indexOf("and");
			queryStr = queryStr.substring(index+3,queryStr.length());
		}
		
		System.out.println("select id,brand_name, ram ,Primary_Camera pc, Secondary_Camera sc, Screen_size sz from Mobile where "+ queryStr +" ;");
		
		List<Mobile> mobileList =	getMobileObjList("select * from Mobile where "+queryStr, null);
		return mobileList;
	}

	private void constructFeaturesQuery(Map<String, List<String>> urlMap, StringBuffer sb) {
		// RAM Query
		if(urlMap.get(MobileConstants.RAM)!=null){
			sb.append(" and ");       
			constructRamQuery(urlMap, sb);
		}
		
		// Primary Camera Query
		if(urlMap.get(MobileConstants.PRIMARY_CAMERA)!=null){
			sb.append(" and ");
			constructPrimaryCameraQuery(urlMap, sb);
		}
		
		// Secondary Camera Query
		if(urlMap.get(MobileConstants.SECONDARY_CAMERA)!=null){
			sb.append(" and ");
			constructSecondaryCameraQuery(urlMap, sb);
		}
		
		// Screen Size Query
		if(urlMap.get(MobileConstants.SCREEN_SIZE)!=null){
			sb.append(" and ");
			constructScreenSizeQuery(urlMap,sb);
		}

		// No of SIM
		if(urlMap.get(MobileConstants.SIM)!=null){
			sb.append(" and ");
			constructNoOfSIM(urlMap,sb);
		}
		
		// Availability
		if(urlMap.get(MobileConstants.AVAILABILITY)!=null){
			sb.append(" and ");
			constructAvailablity(urlMap,sb);
		}
	}
	
	private void constructAvailablity(Map<String, List<String>> urlMap, StringBuffer sb) {
		 List<String> availablitiyList = urlMap.get(MobileConstants.AVAILABILITY);
		 if(availablitiyList!=null && !availablitiyList.isEmpty()){
			 sb.append(" ( ");
			 for (String availiable : availablitiyList) {
				if(availiable.contains("upcoming-mobiles")){
					sb.append(" ( Upcoming_mobile = true ) ");
				}
			}
			 
			 sb.append(" ) ");
		 }
	}

	private void constructNoOfSIM(Map<String, List<String>> urlMap, StringBuffer sb) {
		 List<String> simList = urlMap.get(MobileConstants.SIM);
		 int simCount = 1;
		 if(simList!=null && !simList.isEmpty()){
			 sb.append(" ( ");
			 for (String sim : simList) {
				if(sim.contains("dual")){
					sb.append(" ( dual_sim = true ) ");
				}
				if(sim.contains("single")){
					sb.append(" ( dual_sim = false ) ");
				}
				if(simCount != simList.size()){
					sb.append(" or ");
				}
				simCount ++;
			}
			 
			 sb.append(" ) ");
		 }
	}

	private void constructScreenSizeQuery(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> screenSizeList = urlMap.get(MobileConstants.SCREEN_SIZE);
		int szCount = 1;
		if(screenSizeList!=null && !screenSizeList.isEmpty()){
			sb.append(" ( ");
		for (String mp : screenSizeList) {
			String[] split = mp.split("-");
			if(split[0].contains("above")){
				sb.append(" ( Screen_size >= "+split[1] + " ) ");
				if(screenSizeList.size() > 1 && (szCount != screenSizeList.size())){
					sb.append(" or ");
					szCount ++;
				}
				continue;
			}else if(split[0].contains("upto")){
				sb.append(" ( Screen_size <= "+split[1] + " ) ");
				if(screenSizeList.size() > 1 && (szCount != screenSizeList.size())){
					sb.append(" or ");
					szCount ++;
				}
				continue;
			}else {
				sb.append(" ( Screen_size >= "+split[0] + " and ");
				sb.append(" Screen_size <= "+split[1] + " ) ");
			}
			
			if(szCount != screenSizeList.size()){
				sb.append(" or ");
			}
			szCount ++;
		}
		sb.append(" ) ");
		}
	}

	private void constructSecondaryCameraQuery(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> scList = urlMap.get(MobileConstants.SECONDARY_CAMERA);
		int scCount = 1;
		if(scList!=null){
			sb.append(" ( ");
		for (String mp : scList) {
			String[] split = mp.split("-");
			if(split[0].contains("above")){
				sb.append(" ( Secondary_Camera >= "+split[1] + " ) ");
				if(scList.size() > 1 && (scCount != scList.size())){
					sb.append(" or ");
					scCount ++;
				}
				continue;
			}else if(split[0].contains("upto")){
				sb.append(" ( Secondary_Camera <= "+split[1] + " ) ");
				if(scList.size() > 1 && (scCount != scList.size())){
					sb.append(" or ");
					scCount ++;
				}
				continue;
			}else{
				sb.append(" ( Secondary_Camera >= "+split[0] + " and ");
			}
			sb.append(" Secondary_Camera <= "+split[1] + " ) ");
			if(scCount != scList.size()){
				sb.append(" or ");
			}
			scCount ++;
		}
		sb.append(" ) ");
		}		
	}

	private void constructPrimaryCameraQuery(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> pcList = urlMap.get(MobileConstants.PRIMARY_CAMERA);
		int pcCount = 1;
		if(pcList!=null && !pcList.isEmpty()){
			sb.append(" ( ");
		for (String mp : pcList) {
			String[] split = mp.split("-");
			if(split[0].contains("above")){
				sb.append(" ( Primary_Camera >= "+split[1] + " ) ");
				if(pcList.size() > 1 && (pcCount != pcList.size())){
					sb.append(" or ");
					pcCount ++;
				}
				continue;
			}else if(split[0].contains("upto")){
				sb.append(" ( Primary_Camera <= "+split[1] + " ) ");
				if(pcList.size() > 1 && (pcCount != pcList.size())){
					sb.append(" or ");
					pcCount ++;
				}
				continue;
			}else{
				sb.append(" ( Primary_Camera >= "+split[0] + " and ");
				sb.append(" Primary_Camera <= "+split[1] + " ) ");
			}
			if(pcCount != pcList.size()){
				sb.append(" or ");
			}
			pcCount ++;
		}
		sb.append(" ) ");
		}
	}

	private void constructRamQuery(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> ramList = urlMap.get(MobileConstants.RAM);
		if (ramList != null && !ramList.isEmpty()) {
			int ramCount=1;
			sb.append(" ( ");
			for (String ram : ramList) { // RAM
				if (ram.contains("above")) {
					sb.append(" ram >= '" + ram + "'");
				} else {
					sb.append(" ram = '" + ram + "'");
				}
				if(ramCount != ramList.size()){
					sb.append(" or ");
				}
				ramCount ++;
			}
			sb.append(" ) ");
		}
	}

	private void extractURLInfo(Map<String, String[]> catagoryUrl, Map<String, List<String>> urlMap) {
		String[] brands = catagoryUrl.get(MobileConstants.BRAND);
		if(brands != null){
			List<String> strList = new ArrayList<String>();
			for (String brand : brands) {
				System.out.println(brand);
				strList.add(brand.trim());
			}
			urlMap.put(MobileConstants.BRAND, strList);
		}
		
		String[] rams = catagoryUrl.get(MobileConstants.RAM);
		if(rams!=null){
			List<String> strList = new ArrayList<String>();
			for (String ram : rams) {
				strList.add(ram.replaceAll("-gb", "").trim());
				System.out.println("ram:::"+ram);
			}
			urlMap.put(MobileConstants.RAM, strList);
		}
		
		String[] primaryCamera = catagoryUrl.get(MobileConstants.PRIMARY_CAMERA);
		if(primaryCamera!=null){
			List<String> strList = new ArrayList<String>();
			for (String cam : primaryCamera) {
				strList.add(cam);
				System.out.println("cam:::"+cam);
			}
			urlMap.put(MobileConstants.PRIMARY_CAMERA, strList);
		}
		
		String[] secondaryCamera = catagoryUrl.get(MobileConstants.SECONDARY_CAMERA);
		if(secondaryCamera!=null){
			List<String> strList = new ArrayList<String>();
			for (String cam : secondaryCamera) {
				strList.add(cam);
				System.out.println("cam:::"+cam);
			}
			urlMap.put(MobileConstants.SECONDARY_CAMERA, strList);
		}
		
		String[] screen_size = catagoryUrl.get(MobileConstants.SCREEN_SIZE);
		if(screen_size!=null){
			List<String> strList = new ArrayList<String>();
			for (String cam : screen_size) {
				strList.add(cam);
				System.out.println("size:::"+cam);
			}
			urlMap.put(MobileConstants.SCREEN_SIZE, strList);
		}
		
		String[] simList = catagoryUrl.get(MobileConstants.SIM);
		if(simList!=null){
			List<String> strList = new ArrayList<String>();
			for (String cam : simList) {
				strList.add(cam);
				System.out.println("size:::"+cam);
			}
			urlMap.put(MobileConstants.SIM, strList);
		}
		
		String[] availabilityList = catagoryUrl.get(MobileConstants.AVAILABILITY);
		if(availabilityList!=null){
			List<String> strList = new ArrayList<String>();
			for (String cam : availabilityList) {
				strList.add(cam);
				System.out.println("availability :::"+cam);
			}
			urlMap.put(MobileConstants.AVAILABILITY, strList);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getMobileDetailsByQueryString(String queryString) {
		List<Mobile> mobileList = null;
		if (queryString.length() == 1) {
			String sql = "select * from Mobile where title like  '" + queryString + "%'";
			mobileList = getMobileObjList(sql, null);
		} else if (queryString.length() > 1) {
			String sql = "select * from Mobile where title like  '%" + queryString + "%'";
			mobileList = getMobileObjList(sql, null);
		}
		return mobileList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getUpcomingMobileList() {
		String sql = "select * from Mobile where Upcoming_mobile = true";
		List<Mobile> mobileList  = getMobileObjList(sql, null);
		return mobileList;
	}
	

}
