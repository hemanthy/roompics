package com.mobile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.exception.MobileException;
import com.mobile.model.Company;
import com.mobile.model.Mobile;
import com.mobile.model.Person;
import com.mobile.util.DbUtil;
import com.mobile.vo.MobileConstants;


public class MobileDAOImpl implements MobileDAO {
	

	private Connection connection = null;

	public MobileDAOImpl() throws MobileException {
		connection = DbUtil.getConnection();
	}
	
	private  List<Mobile> getMobileObjList(String sql,Map<String,String> map) throws MobileException{
		PreparedStatement preparedStatement = null;
		List<Mobile> mobileList =  new ArrayList<Mobile>();
		try {
			if(connection == null || connection.isClosed()){
				connection = DbUtil.getConnection();
			}
			preparedStatement = connection.prepareStatement(sql.toLowerCase());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Mobile mobile = new Mobile();
				mobile.setId(rs.getInt("id"));
				mobile.setBrandName(rs.getString("Brand_Name"));
				mobile.setTitle(rs.getString("Title"));
				mobile.setImage_path1(rs.getString("Image_path1"));
				mobile.setCompany_Id(rs.getInt("Company_id"));
				mobileList.add(mobile);
			}
			rs.close();
			preparedStatement.close();
		//	connection.close();
		} catch (SQLException e) {
			throw new MobileException(e);
		}
		return mobileList;
	}
	
	public List<Company> getCompanyObjList(String sql,Map<String,String> map) throws MobileException{
		List<Company> cmpyList =  new ArrayList<Company>();
		PreparedStatement preparedStatement;
		try {
			if(connection == null || connection.isClosed()){
				connection = DbUtil.getConnection();
			}
		preparedStatement = connection.prepareStatement(sql.toLowerCase());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Company company = new Company();
			company.setId(rs.getInt("id"));
			company.setBrandName(rs.getString("brandName"));
			cmpyList.add(company);
		}
		rs.close();
		preparedStatement.close();
		//connection.close();
		} catch (SQLException e) {
			throw new MobileException(e);
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
	public List<Company> listCompanies() throws MobileException {
		String sql = "select * from Company";
		List<Company> companyList = getCompanyObjList(sql, null);
		return companyList;
	}

	@Override
	public Mobile getMobileById(Integer id) throws MobileException {
		String sql = "select * from Mobile where id ="+id;
		List<Mobile> mobileList =	getMobileObjList(sql, null);
		if(!mobileList.isEmpty()){
			return mobileList.get(0);
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getLatestMobile() throws MobileException {
		String sql = "select * from mobile order by Announced_Month desc limit 0,10";
		List<Mobile> mobileList  =	getMobileObjList(sql, null);
		return mobileList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getTopBrandMobileList() throws MobileException {
		String sql = "select * from mobile where (ram >= 4.7 or Screen_Size >= 5) and (Company_id = 7 or Company_id = 8) limit 0,10";
		List<Mobile> mobileList  =	getMobileObjList(sql, null);
		return mobileList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Company> getCompaniesList() throws MobileException {
		String sql = "select * from Company order by brandName asc";
		List<Company> companiesList  =	getCompanyObjList(sql, null);
		return companiesList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getRelatedMobilesList(Mobile mobile) throws MobileException {
		String sql = "select * from Mobile where (ram = "+ mobile.getRam()+") and (Company_id = 7 or Company_id = 14)";
		List<Mobile> mobileList  =	getMobileObjList(sql, null);
		return mobileList;
	}

	@Override
	public Company getBrandByName(String brandName) throws MobileException {
		String sql = "select * from Company where brandName = '"+brandName+"'";
		List<Company> companiesList  =	getCompanyObjList(sql, null);
		if(!companiesList.isEmpty()){
			return companiesList.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getMobilesByRamSize(Integer ramSize) throws MobileException {
		String sql = "select * from Mobile where ram = "+ramSize;
		List<Mobile> mobileList  =	getMobileObjList(sql, null);
		return mobileList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getMobilesByScreenSize(Integer screenSize) throws MobileException {
		String sql = "select * from Mobile where Screen_size = "+screenSize;
		List<Mobile> mobileList  =	getMobileObjList(sql, null);
		return mobileList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Mobile> getMobilesBySearchCatageory(Map<String, String[]> catagoryUrl) throws MobileException {
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
	public List<Mobile> getMobileDetailsByQueryString(String queryString) throws MobileException {
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
	public List<Mobile> getUpcomingMobileList() throws MobileException {
		String sql = "select * from mobile where Upcoming_mobile = true";
		List<Mobile> mobileList  = getMobileObjList(sql, null);
		return mobileList;
	}
	
	public Mobile getMobileByName(String mobileName) throws MobileException {
		
		String sql = "select * from Mobile where title like  '" + mobileName + "'";
		
		return getMobileDetails(sql, null);
	}
	
	@Override
	public List<Mobile> getMobilesBySecondaryCamera(Integer secondaryCamera) throws MobileException {
		String sql = "select * from Mobile where secondary_Camera >= " + secondaryCamera;
		List<Mobile> mobileList  = getMobileObjList(sql, null);
		return mobileList;
	}
	
	@Override
	public List<Mobile> getDualMobiles() throws MobileException {
		String sql = "select * from Mobile where dual_sim = true";
		List<Mobile> mobileList  = getMobileObjList(sql, null);
		return mobileList;
	}
	
	@Override
	public Company getCompanyById(int id) throws MobileException {
		String sql = "select * from Company where id = "+id;
		List<Company> companyList = getCompanyObjList(sql, null);
		return companyList.get(0);
	}
	

	@Override
	public List<Mobile> getMobilesByCompanyId(int id) throws MobileException {
		String sql = "select * from Mobile where Company_id = "+id;
		List<Mobile> mobileList  = getMobileObjList(sql, null);
		return mobileList;
	}
	
	@Override
	public List<Mobile> getMobilesByBrandName(String brandName) throws MobileException {
		String sql = "select * from Mobile where Brand_Name = '" + brandName+"'";
		List<Mobile> mobileList  = getMobileObjList(sql, null);
		return mobileList;
	}

	private Mobile getMobileDetails(String sql, Object object) throws MobileException {
		PreparedStatement preparedStatement;
		try {
			if(connection == null || connection.isClosed()){
				connection = DbUtil.getConnection();
			}
			preparedStatement = connection.prepareStatement(sql.toLowerCase());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Mobile mobile = new Mobile();
				mobile.setId(rs.getInt("id"));
				mobile.setBrandName(rs.getString("Brand_Name"));
				mobile.setModel(rs.getString("Model"));
				mobile.setTitle(rs.getString("Title"));
				mobile.setScreenType(rs.getString("Screen_Type"));
				mobile.setScreenSize(rs.getFloat("Screen_Size"));
				mobile.setRam(rs.getFloat("Ram"));
				mobile.setRamSizeInMB(rs.getInt("Ram_Size_In_MB"));
				mobile.setTechnology(rs.getString("Technology"));
				mobile.setBand_2G(rs.getString("2G"));
				mobile.setBand_3G(rs.getString("3G"));
				
				mobile.setBand_4G(rs.getString("4G"));
				mobile.setSpeed(rs.getString("Speed"));
				
				mobile.setGprs(rs.getString("GPRS"));
				mobile.setEdge(rs.getString("EDGE"));
				mobile.setStatus(rs.getString("Status"));
				mobile.setDimensions(rs.getString("Dimensions"));
				mobile.setWeight(rs.getInt("Weight"));
				
				mobile.setSim(rs.getString("SIM"));
				mobile.setRemovable_Battery(rs.getString("Removable_Battery"));
				mobile.setDisplay_Type(rs.getString("Display_Type"));
				mobile.setResolution(rs.getString("Resolution"));
				mobile.setPpl(rs.getInt("PPL"));
				mobile.setMultitouch(rs.getString("Multitouch"));
				mobile.setProtection(rs.getString("Protection"));
				mobile.setOs(rs.getString("OS"));
				
				mobile.setChipset(rs.getString("Chipset"));
				mobile.setProcessor(rs.getString("Processor"));
				mobile.setGpu(rs.getString("GPU"));
				mobile.setCard_Slot(rs.getString("Card_Slot"));
				
				mobile.setInternal_Memory(rs.getInt("Internal_Memory"));
				mobile.setInternal_Memory1(rs.getInt("Internal_Memory1"));
				mobile.setInternal_Memory2(rs.getInt("Internal_Memory2"));
				mobile.setInternal_Memory3(rs.getInt("Internal_Memory3"));
				mobile.setInternal_Memory4(rs.getInt("Internal_Memory4"));
				
			//	mobile.sete(rs.getInt("External_memory"));
				
				mobile.setInternal_Memory_In_MB(rs.getInt("Internal_Memory_In_MB"));
				mobile.setPrimary_Camera(rs.getFloat("Primary_Camera"));
				mobile.setSecondary_Camera(rs.getFloat("Secondary_Camera"));
				mobile.setFlash(rs.getBoolean("Flash"));
				mobile.setFeatures(rs.getString("Features"));
				
				mobile.setVideo(rs.getString("Video"));
				mobile.setAlert_Types(rs.getString("Alert_Types"));
				mobile.setLoudspeaker(rs.getString("Loudspeaker"));
				mobile.setHeadphones(rs.getString("Headphones"));
				
				mobile.setWi_Fi(rs.getBoolean("Wi_Fi"));
				mobile.setWi_Fi_Direct(rs.getBoolean("Wi_Fi_Direct"));
				mobile.setWi_Fi_Standards_Supported(rs.getString("Wi_Fi_Standards_Supported"));
				mobile.setBluetooth(rs.getString("Bluetooth"));
				
				mobile.setHdmi(rs.getBoolean("HDMI"));
				mobile.setGps(rs.getString("GPS"));
				mobile.setNfc(rs.getString("NFC"));
				mobile.setRadio(rs.getString("Radio"));
				
				mobile.setUsb(rs.getString("USB"));
				mobile.setSensors(rs.getString("Sensors"));
				mobile.setMessaging(rs.getString("Messaging"));
				mobile.setBrowser(rs.getString("Browser"));
				
				mobile.setJava(rs.getString("Java"));
				mobile.setStandBy(rs.getString("Stand_By"));
				mobile.setTalkTime(rs.getString("Talk_Time"));
				mobile.setColors(rs.getString("Colors"));
				
				mobile.setMusicPlay(rs.getString("Music_Play"));
				mobile.setBatteryCapactiy(rs.getInt("Battery_Capactiy"));
				mobile.setBatteryType(rs.getString("Battery_Type"));
				mobile.setAnnounced_Month(rs.getDate("Announced_Month"));
				
				mobile.setImage_path1(rs.getString("Image_path1"));
				mobile.setImage_path2(rs.getString("Image_path2"));
				mobile.setYear(rs.getInt("year"));
				mobile.setMonth(rs.getString("month"));
				mobile.setThinkness(rs.getFloat("thinkness"));
				mobile.setOsVersion(rs.getString("os_version"));
				mobile.setDualSim(rs.getBoolean("dual_sim"));
				mobile.setTripleSim(rs.getBoolean("triple_sim"));
				mobile.setEnabled(rs.getBoolean("Enabled"));
				mobile.setIsUpcomingMobile(rs.getBoolean("Upcoming_mobile"));
				mobile.setCompany_Id(rs.getInt("Company_id"));
				return mobile;
				
			}
			rs.close();
			preparedStatement.close();
		//	connection.close();
		} catch (SQLException e) {
			throw new MobileException(e);
		}
		return null;
	}


}
