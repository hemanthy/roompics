
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mobilestree.mobile.dao.MobileDAOImpl;
import com.mobilestree.mobile.service.MobileService;
import com.mobilestree.mobile.service.MobileServiceImpl;

import junit.framework.TestCase;
//@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-container.xml" })
public class AbstractCheckinTestCase {

	public void testCreateUserSession(){
		
		
	}
	
	static MobileDAOImpl MobileService;
	
	public static MobileService MobileServiceImpl;
	
	public static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	
	public	AbstractCheckinTestCase() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext
	            ("G://Hemanth//Hemanth Code//git//roompics//mobiletree//src//main//webapp//WEB-INF//spring//appServlet//servlet-context.xml");
		MobileService = (MobileDAOImpl) context.getBean("MobileDAO");
		
		MobileServiceImpl = (MobileService) context.getBean("MobileService");
		
		System.out.println("person...."+MobileServiceImpl.toString());
		sessionFactory = (SessionFactory) context.getBean("hibernate4AnnotatedSessionFactory");
		
		System.out.println("context::::"+MobileService);
		
	}
	
	public static void main(String[] args) throws Exception {
		
		new AbstractCheckinTestCase();
	}
}
