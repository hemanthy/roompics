package com.mobilestree.mobile.dao;

import com.mobilestree.mobile.dao.MobileDAO;
import com.mobilestree.mobile.model.Company;
import com.mobilestree.mobile.model.Mobile;
import com.mobilestree.mobile.model.Person;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MobileDAOImpl implements MobileDAO {
	private static final Logger logger = LoggerFactory.getLogger(MobileDAOImpl.class);
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void addPerson(Person p) {
		Session session = this.getSession();
		session.persist((Object) p);
		logger.info("Person saved successfully, Person Details=" + (Object) p);
	}

	public void updatePerson(Person p) {
		Session session = this.getSession();
		session.update((Object) p);
		logger.info("Person updated successfully, Person Details=" + (Object) p);
	}

	public List<Person> listPersons() {
		Session session = this.getSession();
		List<Person> personsList = session.createQuery("from Person").list();
		for (Person p : personsList) {
			logger.info("Person List::" + (Object) p);
		}
		return personsList;
	}

	public Person getPersonById(int id) {
		Session session = this.getSession();
		Person p = (Person) session.load((Class) Person.class, (Serializable) new Integer(id));
		logger.info("Person loaded successfully, Person details=" + (Object) p);
		return p;
	}

	public void removePerson(int id) {
		Session session = this.getSession();
		Person p = (Person) session.load((Class) Person.class, (Serializable) new Integer(id));
		if (p != null) {
			session.delete((Object) p);
		}
		logger.info("Person deleted successfully, person details=" + (Object) p);
	}

	public void addMobile(Mobile m) {
		Session session = this.getSession();
		session.persist((Object) m);
		logger.info("Person saved successfully, Mobile Details=" + (Object) m);
	}

	public void adCompany(Company c) {
		Session session = this.getSession();
		session.persist((Object) c);
		logger.info("Person saved successfully, Company Details=" + (Object) c);
	}

	public Session getSession() {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (Exception e) {
			e.getStackTrace();
			session = this.sessionFactory.openSession();
		}
		return session;
	}

	public void addCompany(Company c) {
		Session session = this.getSession();
		session.persist((Object) c);
	}

	public void updateCompany(Company c) {
		Session session = this.getSession();
		session.update((Object) c);
	}

	public List<Company> listCompanies() {
		logger.info("Entered listCompanies Method");
		Session session = this.getSession();
		List<Company> companyList = session.createQuery("from Company").list();
		for (Company c : companyList) {
			logger.info("company List::" + (Object) c);
		}
		logger.info("Exit listCompanies Method");
		return companyList;
	}

	public List<Mobile> getMobilesList(int id) {
		logger.info("Entered getMobilesList Method");
		Session session = this.getSession();
		List<Mobile> mobileList = session.createQuery("from Mobile where id =" + id).list();
		for (Mobile m : mobileList) {
			logger.info("Mobile List::" + (Object) m);
		}
		logger.info("Exit getMobilesList Method");
		return mobileList;
	}

	public Mobile getMobileById(Integer id) {
		logger.info("Entered getMobileById Method");
		Session session = this.getSession();
		Mobile m = (Mobile) session.load((Class) Mobile.class, (Serializable) new Integer(id));
		logger.info("Mobile loaded successfully, Mobile details=" + (Object) m);
		logger.info("Exit getMobileById Method");
		return m;
	}

	public List<Mobile> getLatestMobile() {
		logger.info("Entered getLatestMobile Method");
		Session session = this.getSession();
		List<Mobile> mobileList = session.createQuery("from Mobile order by Announced_Month desc").list();
		for (Mobile m : mobileList) {
			logger.info("Mobile Latest List::" + (Object) m);
		}
		logger.info("Exit getLatestMobile Method");
		return mobileList;
	}

	public List<Mobile> getTopBrandMobileList() {
		logger.info("Entered getTopBrandMobileList Method");
		Session session = this.getSession();
		List<Mobile> mobileList = session
				.createQuery(
						"from Mobile where (ram >= 4.7 or screenSize >= 5) and (company.id = 1 or company.id = 2) ")
				.list();
		for (Mobile m : mobileList) {
			logger.info("Mobile Brand List::" + (Object) m);
		}
		logger.info("Exit getTopBrandMobileList Method");
		return mobileList;
	}

	public List<Company> getCompaniesList() {
		logger.info("Entered getCompaniesList Method");
		Session session = this.getSession();
		List<Company> companiesList = session.createQuery("from Company order by brandName asc").list();
		for (Company c : companiesList) {
			logger.info("Company List::" + (Object) c);
			for (Mobile mobile : c.getMobile()) {
				System.out.println("mobile List :" + (Object) mobile);
			}
		}
		logger.info("Exit getCompaniesList Method");
		return companiesList;
	}

	public Company getCompanyById(int id) {
		logger.info("Entered getCompanyById Method");
		Session session = this.getSession();
		Company cmpy = (Company) session.load((Class) Company.class, (Serializable) new Integer(id));
		logger.info("Company loaded successfully, Company details=" + (Object) cmpy);
		logger.info("Exit getCompanyById Method");
		return cmpy;
	}

	public List<Mobile> getRelatedMobilesList(Mobile mobile) {
		logger.info("Entered getRelatedMobilesList Method");
		Session session = this.getSession();
		List<Mobile> mobileList = session
				.createQuery(
						"from Mobile where (ram = :ram) and (company.id = :company_id1 or company.id = :company_id2) ")
				.setParameter("ram", (Object) Float.valueOf(mobile.getRam())).setParameter("company_id1", (Object) 1)
				.setParameter("company_id2", (Object) 2).list();
		for (Mobile m : mobileList) {
			logger.info("Mobile Brand List::" + (Object) m);
		}
		logger.info("Exit getRelatedMobilesList Method");
		return mobileList;
	}

	public Company getBrandByName(String brandName) {
		logger.info("Entered getBrandByName Method");
		Session session = this.getSession();
		List<Company> cmpy = session.createQuery("from Company where brandName = :brandName")
				.setParameter("brandName", brandName).list();
		if (cmpy != null && !cmpy.isEmpty()) {
			return  cmpy.get(0);
		}
		logger.info("Exit getBrandByName Method");
		return null;
	}

	public List<Mobile> getMobilesByRamSize(Integer ramSize) {
		logger.info("Entered getMobilesByRamSize Method");
		Session session = this.getSession();
		List<Mobile> mobileList = session.createQuery("from Mobile where ram = " + ramSize).list();
		logger.info("Exit getMobilesByRamSize Method");
		return mobileList;
	}

	public List<Mobile> getMobilesByScreenSize(Integer screenSize) {
		logger.info("Entered getMobilesByScreenSize Method");
		Session session = this.getSession();
		List<Mobile> mobileList = session.createQuery("from Mobile where screenSize = " + screenSize).list();
		logger.info("Exit getMobilesByScreenSize Method");
		return mobileList;
	}

	public List<Mobile> getMobilesBySearchCatageory(Map<String, String[]> catagoryUrl) {
		logger.info("Entered getMobilesBySearchCatageory Method");
		HashMap<String, List<String>> urlMap = new HashMap<String, List<String>>();
		StringBuffer sb = new StringBuffer();
		this.extractURLInfo(catagoryUrl, urlMap);
		List<String> brandList = urlMap.get("brand");
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
				this.constructFeaturesQuery(urlMap, sb);
				sb.append(" ) ");
			}
		} else if (brandList == null) {
			sb.append(" or ");
			this.constructFeaturesQuery(urlMap, sb);
		}
		String queryStr = sb.toString();
		if (queryStr.trim().startsWith("or")) {
			int indexOf = queryStr.indexOf("or");
			queryStr = queryStr.substring(indexOf + 2, queryStr.length());
		}
		if (queryStr.trim().startsWith("and")) {
			int index = queryStr.indexOf("and");
			queryStr = queryStr.substring(index + 3, queryStr.length());
		}
		System.out.println(
				"select id,brand_name, ram ,Primary_Camera pc, Secondary_Camera sc, Screen_size sz from Mobile where "
						+ queryStr + " ;");
		Session session = this.getSession();
		List<Mobile> mobileList = session.createQuery("from Mobile where " + queryStr + " ").list();
		logger.info("Exit getMobilesBySearchCatageory Method");
		return mobileList;
	}

	private void constructFeaturesQuery(Map<String, List<String>> urlMap, StringBuffer sb) {
		if (urlMap.get("ram") != null) {
			sb.append(" and ");
			this.constructRamQuery(urlMap, sb);
		}
		if (urlMap.get("primary-camera") != null) {
			sb.append(" and ");
			this.constructPrimaryCameraQuery(urlMap, sb);
		}
		if (urlMap.get("secondary-camera") != null) {
			sb.append(" and ");
			this.constructSecondaryCameraQuery(urlMap, sb);
		}
		if (urlMap.get("screen-size") != null) {
			sb.append(" and ");
			this.constructScreenSizeQuery(urlMap, sb);
		}
		if (urlMap.get("sim") != null) {
			sb.append(" and ");
			this.constructNoOfSIM(urlMap, sb);
		}
		if (urlMap.get("availability") != null) {
			sb.append(" and ");
			this.constructAvailablity(urlMap, sb);
		}
		if (urlMap.get("internal-memory") != null) {
			sb.append(" and ");
			this.constructInternalMemory(urlMap, sb);
		}
		if (urlMap.get("external-memory") != null) {
			sb.append(" and ");
			this.constructExternalMemory(urlMap, sb);
		}
	}

	private void constructExternalMemory(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> external_memory = urlMap.get("external-memory");
		if (!(external_memory == null || external_memory.isEmpty())) {
			int memoryCount = 1;
			sb.append(" ( ");
			for (String rom : external_memory) {
				if (rom.contains((CharSequence) "above")) {
					sb.append(" external_Memory >= '" + rom.split("above-")[1] + "'");
				} else {
					sb.append(" external_Memory = '" + rom + "'");
				}
				if (memoryCount != external_memory.size()) {
					sb.append(" or ");
				}
				++memoryCount;
			}
			sb.append(" ) ");
		}
	}

	private void constructInternalMemory(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> internal_memory = urlMap.get("internal-memory");
		if (!(internal_memory == null || internal_memory.isEmpty())) {
			int memoryCount = 1;
			sb.append(" ( ");
			for (String rom : internal_memory) {
				if (rom.contains((CharSequence) "above")) {
					sb.append(" internal_Memory >= '" + rom.split("above-")[1] + "'");
				} else {
					sb.append(" internal_Memory = '" + rom + "'");
				}
				if (memoryCount != internal_memory.size()) {
					sb.append(" or ");
				}
				++memoryCount;
			}
			sb.append(" ) ");
		}
	}

	private void constructAvailablity(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> availablitiyList = urlMap.get("availability");
		if (!(availablitiyList == null || availablitiyList.isEmpty())) {
			sb.append(" ( ");
			for (String availiable : availablitiyList) {
				if (!availiable.contains((CharSequence) "upcoming-mobiles"))
					continue;
				sb.append(" ( Upcoming_mobile = true ) ");
			}
			sb.append(" ) ");
		}
	}

	private void constructNoOfSIM(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> simList = urlMap.get("sim");
		int simCount = 1;
		if (!(simList == null || simList.isEmpty())) {
			sb.append(" ( ");
			for (String sim : simList) {
				if (sim.contains((CharSequence) "dual")) {
					sb.append(" ( dual_sim = true ) ");
				}
				if (sim.contains((CharSequence) "single")) {
					sb.append(" ( dual_sim = false ) ");
				}
				if (simCount != simList.size()) {
					sb.append(" or ");
				}
				++simCount;
			}
			sb.append(" ) ");
		}
	}

	private void constructScreenSizeQuery(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> screenSizeList = urlMap.get("screen-size");
		int szCount = 1;
		if (!(screenSizeList == null || screenSizeList.isEmpty())) {
			sb.append(" ( ");
			for (String mp : screenSizeList) {
				String[] split = mp.split("-");
				if (split[0].contains((CharSequence) "above")) {
					sb.append(" ( Screen_size >= " + split[1] + " ) ");
					if (screenSizeList.size() <= 1 || szCount == screenSizeList.size())
						continue;
					sb.append(" or ");
					++szCount;
					continue;
				}
				if (split[0].contains((CharSequence) "upto")) {
					sb.append(" ( Screen_size <= " + split[1] + " ) ");
					if (screenSizeList.size() <= 1 || szCount == screenSizeList.size())
						continue;
					sb.append(" or ");
					++szCount;
					continue;
				}
				sb.append(" ( Screen_size >= " + split[0] + " and ");
				sb.append(" Screen_size <= " + split[1] + " ) ");
				if (szCount != screenSizeList.size()) {
					sb.append(" or ");
				}
				++szCount;
			}
			sb.append(" ) ");
		}
	}

	private void constructSecondaryCameraQuery(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> scList = urlMap.get("secondary-camera");
		int scCount = 1;
		if (scList != null) {
			sb.append(" ( ");
			for (String mp : scList) {
				String[] split = mp.split("-");
				if (split[0].contains((CharSequence) "above")) {
					sb.append(" ( Secondary_Camera >= " + split[1] + " ) ");
					if (scList.size() <= 1 || scCount == scList.size())
						continue;
					sb.append(" or ");
					++scCount;
					continue;
				}
				if (split[0].contains((CharSequence) "upto")) {
					sb.append(" ( Secondary_Camera <= " + split[1] + " ) ");
					if (scList.size() <= 1 || scCount == scList.size())
						continue;
					sb.append(" or ");
					++scCount;
					continue;
				}
				sb.append(" ( Secondary_Camera >= " + split[0] + " and ");
				sb.append(" Secondary_Camera <= " + split[1] + " ) ");
				if (scCount != scList.size()) {
					sb.append(" or ");
				}
				++scCount;
			}
			sb.append(" ) ");
		}
	}

	private void constructPrimaryCameraQuery(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> pcList = urlMap.get("primary-camera");
		int pcCount = 1;
		if (!(pcList == null || pcList.isEmpty())) {
			sb.append(" ( ");
			for (String mp : pcList) {
				String[] split = mp.split("-");
				if (split[0].contains((CharSequence) "above")) {
					sb.append(" ( Primary_Camera >= " + split[1] + " ) ");
					if (pcList.size() <= 1 || pcCount == pcList.size())
						continue;
					sb.append(" or ");
					++pcCount;
					continue;
				}
				if (split[0].contains((CharSequence) "upto")) {
					sb.append(" ( Primary_Camera <= " + split[1] + " ) ");
					if (pcList.size() <= 1 || pcCount == pcList.size())
						continue;
					sb.append(" or ");
					++pcCount;
					continue;
				}
				sb.append(" ( Primary_Camera >= " + split[0] + " and ");
				sb.append(" Primary_Camera <= " + split[1] + " ) ");
				if (pcCount != pcList.size()) {
					sb.append(" or ");
				}
				++pcCount;
			}
			sb.append(" ) ");
		}
	}

	private void constructRamQuery(Map<String, List<String>> urlMap, StringBuffer sb) {
		List<String> ramList = urlMap.get("ram");
		if (!(ramList == null || ramList.isEmpty())) {
			int ramCount = 1;
			sb.append(" ( ");
			for (String ram : ramList) {
				if (ram.contains((CharSequence) "above")) {
					sb.append(" ram >= '" + ram.split("above-")[1] + "'");
				} else {
					sb.append(" ram = '" + ram + "'");
				}
				if (ramCount != ramList.size()) {
					sb.append(" or ");
				}
				++ramCount;
			}
			sb.append(" ) ");
		}
	}

	private void extractURLInfo(Map<String, String[]> catagoryUrl, Map<String, List<String>> urlMap) {
		String[] simList;
		String[] internalMemoryList;
		String[] primaryCamera;
		String[] rams;
		String[] secondaryCamera;
		String[] screen_size;
		String[] availabilityList;
		String[] externalMemoryList;
		String[] brands = catagoryUrl.get("brand");
		if (brands != null) {
			ArrayList<String> strList = new ArrayList<String>();
			String[] arrstring = brands;
			int n = arrstring.length;
			for (int i = 0; i < n; ++i) {
				String brand = arrstring[i];
				System.out.println(brand);
				strList.add(brand.trim());
			}
			urlMap.put("brand", strList);
		}
		if ((rams = catagoryUrl.get("ram")) != null) {
			ArrayList<String> strList = new ArrayList<String>();
			for (String ram : rams) {
				strList.add(ram.replaceAll("-gb", "").trim());
				System.out.println("ram:::" + ram);
			}
			urlMap.put("ram", strList);
		}
		if ((primaryCamera = catagoryUrl.get("primary-camera")) != null) {
			ArrayList<String> strList = new ArrayList<String>();
			for (String cam : primaryCamera) {
				strList.add(cam);
				System.out.println("cam:::" + cam);
			}
			urlMap.put("primary-camera", strList);
		}
		if ((secondaryCamera = catagoryUrl.get("secondary-camera")) != null) {
			ArrayList<String> strList = new ArrayList<String>();
			for (String cam : secondaryCamera) {
				strList.add(cam);
				System.out.println("cam:::" + cam);
			}
			urlMap.put("secondary-camera", strList);
		}
		if ((screen_size = catagoryUrl.get("screen-size")) != null) {
			ArrayList<String> strList = new ArrayList<String>();
			for (String cam : screen_size) {
				strList.add(cam);
				System.out.println("size:::" + cam);
			}
			urlMap.put("screen-size", strList);
		}
		if ((simList = catagoryUrl.get("sim")) != null) {
			ArrayList<String> strList = new ArrayList<String>();
			for (String cam : simList) {
				strList.add(cam);
				System.out.println("size:::" + cam);
			}
			urlMap.put("sim", strList);
		}
		if ((availabilityList = catagoryUrl.get("availability")) != null) {
			ArrayList<String> strList = new ArrayList<String>();
			for (String cam : availabilityList) {
				strList.add(cam);
				System.out.println("availability :::" + cam);
			}
			urlMap.put("availability", strList);
		}
		if ((internalMemoryList = catagoryUrl.get("internal-memory")) != null) {
			ArrayList<String> strList = new ArrayList<String>();
			for (String rom : internalMemoryList) {
				strList.add(rom.replaceAll("-gb", "").trim());
				System.out.println("Internal Memory:::" + rom);
			}
			urlMap.put("internal-memory", strList);
		}
		if ((externalMemoryList = catagoryUrl.get("external-memory")) != null) {
			ArrayList<String> strList = new ArrayList<String>();
			for (String rom : externalMemoryList) {
				strList.add(rom.replaceAll("-gb", "").trim());
				System.out.println("External Memory:::" + rom);
			}
			urlMap.put("external-memory", strList);
		}
	}

	public List<Mobile> getMobileDetailsByQueryString(String queryString) {
		Session session = this.getSession();
		List<Mobile> mobileList = null;
		if (queryString.length() == 1) {
			mobileList = session.createQuery("from Mobile where title like  '" + queryString + "%'").setMaxResults(10)
					.list();
		} else if (queryString.length() > 1) {
			mobileList = session.createQuery("from Mobile where title like '%" + queryString + "%'").setMaxResults(10)
					.list();
		}
		return mobileList;
	}

	public List<Mobile> getUpcomingMobileList() {
		Session session = this.getSession();
		List<Mobile> mobileList = session.createQuery("from Mobile where isUpcomingMobile = true").list();
		return mobileList;
	}

	public List<Mobile> getMobilesBySecondaryCamera(Integer secondaryCamera) {
		logger.info("Entered getMobilesBySecondaryCamera Method");
		Session session = this.getSession();
		List<Mobile> mobileList = session.createQuery("from Mobile where secondary_Camera >= " + secondaryCamera)
				.list();
		logger.info("Exit getMobilesBySecondaryCamera Method");
		return mobileList;
	}

	public List<Mobile> getDualMobiles() {
		Session session = this.getSession();
		List<Mobile> mobileList = session.createQuery("from Mobile where dualSim = true").list();
		return mobileList;
	}

	public Mobile getMobileByName(String mobileName) {
		Session session = this.getSession();
		List<Mobile> mobileList = session.createQuery("from Mobile where title like  '" + mobileName + "'").list();
		if (!(mobileList == null || mobileList.isEmpty())) {
			return  mobileList.get(0);
		}
		return null;
	}
}