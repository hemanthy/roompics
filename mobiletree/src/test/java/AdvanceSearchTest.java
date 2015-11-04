import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.mobilestree.mobile.MobileConstants;
import com.mobilestree.mobile.dao.MobileDAOImpl;

import junit.framework.TestCase;

public class AdvanceSearchTest extends TestCase {
	
	
	MobileDAOImpl mobileDAOImpl = new MobileDAOImpl();
	
	@Test
	public void testURL(){
		Map<String, String[]> catagoryUrl = new HashMap<String, String[]>();
		
		String[] brndList = new String[] {"sony","samsung"};
		catagoryUrl.put(MobileConstants.BRAND, brndList);
		
		String[] ramList = new String[] {"1","2"};
	 	catagoryUrl.put(MobileConstants.RAM, (String[]) ramList);
		
		String[] pcList = new String[] {"upto-5"};
		catagoryUrl.put(MobileConstants.PRIMARY_CAMERA, (String[]) pcList);
		
		String[] scList = new String[] {"above-1"};
//		 catagoryUrl.put(MobileConstants.SECONDARY_CAMERA, (String[]) scList);
		
		String[] szList = new String[] {"3-4","upto-4.5"};
		catagoryUrl.put(MobileConstants.SCREEN_SIZE, (String[]) szList);
		
		String[] simList = new String[] {"dual-sim"};
		catagoryUrl.put(MobileConstants.SIM, (String[]) simList);
		
		mobileDAOImpl.getMobilesBySearchCatageory(catagoryUrl);
	}

}

//  select id,name,Screen_size sz,ram,primary_camera pc,secondary_camera sc from mobile;