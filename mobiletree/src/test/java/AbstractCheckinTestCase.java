
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mobilestree.mobile.dao.MobileDAOImpl;
import com.mobilestree.mobile.service.MobileService;
import com.mobilestree.mobile.service.MobileServiceImpl;

import junit.framework.TestCase;
//@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-container.xml" })
public class AbstractCheckinTestCase  extends TestCase {

	@Test
	public void testCreateUserSession(){
		
		
	}
	
	MobileDAOImpl MobileService;
	
	MobileService MobileServiceImpl;
	
	SessionFactory sessionFactory;
	
	
	public	AbstractCheckinTestCase(){
		ApplicationContext context = new FileSystemXmlApplicationContext
	            ("G://Hemanth//Hemanth Code//Spring Security//mobilestree//src//main//webapp//WEB-INF//spring//appServlet//servlet-context.xml");
		MobileService = (MobileDAOImpl) context.getBean("MobileDAO");
		
		MobileServiceImpl = (MobileService) context.getBean("MobileService");
		
		System.out.println("person...."+MobileServiceImpl.toString());
		sessionFactory = (SessionFactory) context.getBean("hibernate4AnnotatedSessionFactory");
		
		System.out.println("context::::"+MobileService);
	}
}
