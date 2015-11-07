
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mobilestree.mobile.model.Company;
import com.mobilestree.mobile.model.Mobile;




public class UserDetailsServiceTestcase extends AbstractCheckinTestCase {
	
	public UserDetailsServiceTestcase() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testCreateUserSession(){
		
		String name = "Hemanth";
		String password ="abc";
		String role ="admin";
		//userDetailsService.createUser(name, password, role);
		System.out.println(MobileService);
	}
	
	@Test
	public void testCompany(){
		
		//userDetailsService.createUser("hh", "kds");
		
		Session openSession = sessionFactory.openSession();
		Transaction transaction = openSession.beginTransaction();
		
		
		
		Company c1 = new Company();
		c1.setEnabled(false);
		//c.setName("samsung");
		//c.setId(1);
		
		
		
		Set<Mobile> mobileList1 = new HashSet<Mobile>();
		for(int i=1;i<5;i++){
		Mobile mobile = new Mobile();
		mobile.setModel("12");
	//	mobile.setName("galaxy-"+i);
		//mobile.setScreenResolution(5);
		mobile.setRam(4);
		mobile.setScreenType("HD");
		mobile.setTitle("Moto E");
		mobile.setAnnounced_Month(new Date());
		mobile.setCompany(c1);
		mobileList1.add(mobile);
		}
		c1.setMobile(mobileList1);
		
		Company c2 = new Company();
		c2.setEnabled(false);
		//c2.setName("Motorala");
		Set<Mobile> mobileList2 = new HashSet<Mobile>();
		for(int i=0;i<6;i++){
			Mobile mobile = new Mobile();
			mobile.setModel("178");
//			mobile.setName("Moto-"+i);
	//		mobile.setScreenResolution(1000);
			mobile.setRam(2);
			mobile.setScreenType("HD");
			mobile.setTitle("Moto E");
			mobile.setAnnounced_Month(new Date());
			mobile.setCompany(c2);
			mobileList2.add(mobile);
			}
		c2.setMobile(mobileList2);
		
		openSession.save(c1);
		openSession.save(c2);
		//openSession.save(mobile);
		//MobileServiceImpl.addCompany(c);;
		//MobileServiceImpl.addCompany(c);;
		
		//user.setUserId(21L);
		//openSession.save(listPersons.size());
		transaction.commit();
		openSession.close();
		
	}
	

}
