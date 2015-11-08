import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mobilestree.mobile.model.Company;
import com.mobilestree.mobile.model.Mobile;
import com.mobilestree.mobile.service.MobileService;

public class HTMLCrawl  {
	
	
	private static List<Integer> yearList = new ArrayList<Integer>();
	
	private static List<String> monthList = new ArrayList<String>();
	
	private static List<String> companyList = new ArrayList<String>();
	
	private static Map<String,Company> companyMap = new HashMap<String, Company>();
	
	private static Map<String,String> monthMap = new HashMap<String, String>();
	
	private static Map<String,Integer> monthMapIndex = new HashMap<String, Integer>();

	private SessionFactory sessionFactory;
	
	MobileService mobileServiceImpl;
	
	Map<String, String> map = null;
	
	
	public Map<String, String> setMap(Map<String, String> map){
		return this.map = map;
	}
	
	public HTMLCrawl( Map<String, String> map,SessionFactory sessionFactory, MobileService mobileServiceImpl){
		this.map = map;
		this.sessionFactory = sessionFactory;
		this.mobileServiceImpl = mobileServiceImpl;
		

		yearList.add(2015);
		yearList.add(2014);
		yearList.add(2013);
		yearList.add(2012);
		yearList.add(2011);
		yearList.add(2010);
		yearList.add(2009);
		yearList.add(2008);
		
		
		monthList.add("January");
		monthList.add("February");
		monthList.add("March");
		monthList.add("April");
		monthList.add("May");
		monthList.add("June");
		monthList.add("July");
		monthList.add("August");
		monthList.add("September");
		monthList.add("October");
		monthList.add("November");
		monthList.add("December");
		
		
		
		monthMap.put("Jan", "January");
		monthMap.put("Feb", "February");
		monthMap.put("Mar", "March");
		monthMap.put("Apr", "April");
		monthMap.put("May", "May");
		monthMap.put("Jun", "June");
		monthMap.put("Jul", "July");
		monthMap.put("Aug", "August");
		monthMap.put("Sep", "September");
		monthMap.put("Oct", "October");
		monthMap.put("Nov", "November");
		monthMap.put("Dec", "December");
		
		monthMapIndex.put("January",0);
		monthMapIndex.put("February",1);
		monthMapIndex.put("March",2);
		monthMapIndex.put("April",3);
		monthMapIndex.put("May",4);
		monthMapIndex.put("June",5);
		monthMapIndex.put("July",6);
		monthMapIndex.put("August",7);
		monthMapIndex.put("September",8);
		monthMapIndex.put("October",9);
		monthMapIndex.put("November",10);
		monthMapIndex.put("December",11);
		
		
	
	}


	
	
	
	public void saveCompany(String brandName) throws ClassNotFoundException, SQLException{
		
		System.out.println("save company::"+brandName);
		
			// Read HTML
			Mobile mobile = constructMobileObject(map);
		if (mobile == null) {
			System.out.println("....." + brandName);
		} else {
			
			Session openSession = sessionFactory.openSession();
			Transaction transaction = openSession.beginTransaction();
			
			Set<Mobile> mobilesList = new HashSet<Mobile>();
			
			Company cmpy = mobileServiceImpl.getBrandByName(brandName);
			
			if(cmpy == null){
				cmpy = new Company();
				cmpy.setEnabled(false);
				cmpy.setBrandName(brandName.trim());
			}
			
			
			String title = mobile.getTitle();
			Mobile mob = mobileServiceImpl.getMobileByName(title);
			if (mob == null) {
				if (title != null) {
					String[] titleSplit = title.split(" ");
					int i = 0;
					String modelName = null;
					for (String str : titleSplit) {
						str = str + " ";
						if (i != 0) {
							if (modelName != null) {
								modelName += str;
							} else {
								modelName = str;
							}
						}
						i++;
					}
					if (modelName != null) {
						mobile.setModel(modelName.trim());
					}
					mobile.setBrandName(brandName.trim());
				}
				mobile.setCompany(cmpy);
				mobilesList.add(mobile);

				cmpy.setMobile(mobilesList);

				openSession.saveOrUpdate(cmpy);
				transaction.commit();
				openSession.close();
			}
			
				
			
					}
	}



	private Mobile constructMobileObject(Map<String, String> map) {
		
		Mobile mobile = new Mobile();
		String title = map.get("title");
		mobile.setTitle(title);
		
		mobile.setModel(title);
		
		// TODO
				String announced = map.get("Announced");
				if(announced!=null){
					Integer year = 0;
					
					Integer monthIndex = 0;
					
					Set<Entry<String, Integer>> entrySet = monthMapIndex.entrySet();
					for (Entry<String, Integer> entry : entrySet) {
						if(announced.contains(entry.getKey())){
							monthIndex = entry.getValue();
						}
					}
					
					if(announced.contains("Q1")){
						monthIndex = 5;
					}
					if(announced.contains("Q2")){
						monthIndex = 8;
					}
					if(announced.contains("Q3")){
						monthIndex = 11;
					}
					if(announced.contains("Q4")){
						monthIndex = 3;
					}
					
					;
					for (Integer yr : yearList) {
						
						if(announced.contains(yr.toString())){
							year = yr;
						}
						
					}
					
					Calendar cal = Calendar.getInstance();
					cal.set(year, monthIndex, 1);
					mobile.setAnnounced_Month(cal.getTime());
				}
				
				Calendar.getInstance().get(Calendar.YEAR);
				
				
				int year = getYearFromDate(mobile.getAnnounced_Month());
				
				if(year < 2012)
					return null;
		
		String technology = map.get("Technology");
		mobile.setTechnology(technology);
		
		String band_2g = map.get("2G bands");
		mobile.setBand_2G(band_2g);
		
		String band_3g = map.get("3G bands");
		mobile.setBand_3G(band_3g);
		
		String band_4g = map.get("4G bands");
		mobile.setBand_4G(band_4g);
		
		String speed = map.get("Speed");
		mobile.setSpeed(speed);
		
		String gprs = map.get("GPRS");
		mobile.setGprs(gprs);
		
		String edge = map.get("EDGE");
		mobile.setEdge(edge);
		
		String status = map.get("Status");
		mobile.setStatus(status);
		
		String dimensions = map.get("Dimensions");
		if(dimensions!=null && dimensions.contains("(")){
			int indexOf = dimensions.indexOf("(");
			String substring = dimensions.substring(0, indexOf);
			mobile.setDimensions(substring.trim());
		}
		
		// TODO
		String weight = map.get("Weight");
		if(weight!=null && weight.contains("g")){
			String weight1 = weight.split("g")[0].trim();
			if(weight1.contains(".")){
				Integer weg = Float.valueOf(weight1).intValue();
				mobile.setWeight(weg);
			}else{
				Integer weg = Integer.valueOf(weight1);
				mobile.setWeight(weg);
			}
			
		}
		String sim = map.get("SIM");
		if(sim!=null && sim.toLowerCase().contains("dual")){
			mobile.setDualSim(true);
		}else{
			mobile.setDualSim(false);
		}
		if(sim!=null && sim.toLowerCase().contains("triple")){
			mobile.setTripleSim(true);
		}else{
			mobile.setTripleSim(false);
		}
		mobile.setSim(sim);
		
		// TODO
		String screenType_ = map.get("Type");
		if(screenType_!=null && screenType_.contains(",")){
			String screenType = screenType_.split(",")[0];
			mobile.setScreenType(screenType);
		}else {
			mobile.setScreenType(screenType_);
		}
		
		// TODO
		String screenSize_ = map.get("Size");
		if(screenSize_!=null && screenSize_.contains("inches")){
			String screnSize = screenSize_.split("inches")[0];
			Float flot = Float.valueOf(screnSize);
		//	Integer i = (int) (flot * 10);
			mobile.setScreenSize(flot);
		}
		
		String resolution = map.get("Resolution");
		if(resolution!=null && resolution.contains("(")){
			if(resolution.contains("(~")){
				String[] split = resolution.split("ppi");
				
				int indexOf = split[0].indexOf("(~");
				String substring1 = split[0].substring(indexOf+2, split[0].length());
				Integer valueOf = Integer.valueOf(substring1.trim());
				if(valueOf!=null){
					mobile.setPpl(valueOf);
				}
				
				String substring2 = split[0].substring(0, indexOf);
				mobile.setResolution(substring2.trim());
				
			}else if(resolution.contains("(")){
				String[] split = resolution.split("ppi");
				int indexOf = split[0].indexOf("(");
				String substring1 = split[0].substring(indexOf+1, split[0].length());
				Integer valueOf = Integer.valueOf(substring1.trim());
				if(valueOf!=null){
					mobile.setPpl(valueOf);
				}
				
				String substring2 = split[0].substring(0, indexOf);
				mobile.setResolution(substring2.trim());
			}
		}else{
			mobile.setResolution(resolution.trim());
		}
		
		String multitouch = map.get("Multitouch");
		mobile.setMultitouch(multitouch);
		
		String protection = map.get("Protection");
		mobile.setProtection(protection);
		
		String os = map.get("OS");
		mobile.setOs(os);
		
		String chipset = map.get("chipSet");
		mobile.setChipset(chipset);
		
		String processor = map.get("CPU");
		mobile.setProcessor(processor);
		
		String gpu = map.get("GPU");
		mobile.setGpu(gpu);
		
		String card_Slot = map.get("Card slot");
		mobile.setCard_Slot(card_Slot);
		
		String ram = map.get("ram");
		if(ram!=null){
			mobile.setRam(Float.valueOf(ram));
		}
		
		String ramMB = map.get("ram_size_mb");
		if(ramMB!=null){
			mobile.setRamSizeInMB(Integer.valueOf(ramMB));
		}
		
		
		
	// TODO
		String internal_Memory = map.get("Internal");
		if(internal_Memory!=null && internal_Memory.contains("/") && internal_Memory.contains("GB")){
			String[] split = internal_Memory.split("/");
		//	for (String str : split) {
			if(split.length >= 0){
				String string = split[0];
				String str = string;
				if(str.contains(",")){
					String[] split2 = str.split(",");
					if(split2.length > 1){
						String extraRam = split2[1];
						if(extraRam!=null && extraRam.contains("GB") && extraRam.contains("RAM")){
							if(str!=null && str.contains("GB")){
								String string2 = str.split("GB")[0];
								if(string2!=null){
									Integer val = Integer.valueOf(string2.trim());
									mobile.setInternal_Memory1(val);
								}
							}
						}
					}
				} else if (str.contains("/") && str.contains("GB")) {
					String s1 = str.split("GB")[0];
					Integer val = Integer.valueOf(s1.trim());
					mobile.setInternal_Memory1(val);
				} else {
					Integer val = Integer.valueOf(str);
					mobile.setInternal_Memory1(val);
				}
			}
			if(split.length >= 2 && !split[1].contains("GB")){
				String str1 = split[1];
				Integer val1 = Integer.valueOf(str1);
				mobile.setInternal_Memory2(val1);
			}else if(split.length >= 1 && split[1].contains("GB")){
				 String str1 = split[1].substring(0, 3);
				 Integer val1 = null;
				 if(str1.contains("G")){
					 String s1 = split[1].split("GB")[0];
					 val1  = Integer.valueOf(s1.trim());
				 }else{
					 val1  = Integer.valueOf(str1.trim());
				 }
				 mobile.setInternal_Memory2(val1);
			}
			
			if(split.length >= 3 && !split[2].contains("GB") && !split[2].contains("MB") && !split[2].contains("RAM")){
				String str2 = split[2];
				Integer val2 = Integer.valueOf(str2);
				mobile.setInternal_Memory3(val2);
			}else if(split.length >= 3 && split[2].contains("GB")){
				 String str2 = split[2].substring(0, 3);
				 Integer val2 = Integer.valueOf(str2.trim());
				 mobile.setInternal_Memory3(val2);
			}
				
			if(split.length >= 4 && !split[3].contains("GB")){
				String str3 = split[3];
				Integer val3 = Integer.valueOf(str3);
				mobile.setInternal_Memory4(val3);
			}else if(split.length >= 4 && split[2].contains("GB")){
				 String str3 = split[3].substring(0, 3);
				 Integer val3 = Integer.valueOf(str3.trim());
				 mobile.setInternal_Memory4(val3);
			}
			
			/*if(split.length >= 4){
				String str4 = split[4];
				Integer val4 = Integer.valueOf(str4);
				mobile.setInternal_Memory4(val4);
			}*/
				
		//	}
		} else if(internal_Memory!=null && internal_Memory.contains(",") &&
				// TODO check RAM changes on internal memory
				internal_Memory.contains("GB") && internal_Memory.contains("RAM")){
			String str = internal_Memory.split(",")[0];
			
			String s1 = null;
			if(str.contains("GB")){
				s1 = str.split("GB")[0];
			}else{
				s1 = str;
			}
			
			if(s1.contains(".")){
				Double ceil = Math.ceil(Double.valueOf(s1.trim()));
				mobile.setInternal_Memory(Integer.valueOf(ceil.intValue()));
			}else{
				Integer valueOf = Integer.valueOf(s1.trim());
				mobile.setInternal_Memory(valueOf);
			}
		}
		else if(internal_Memory!=null && internal_Memory.contains(",") && internal_Memory.contains("GB")){
		String str = internal_Memory.split(",")[0];
			int indexGB = str.indexOf("GB");
			String substring = str.substring(0, indexGB);
			Integer valueOf = Integer.valueOf(substring.trim());
			mobile.setInternal_Memory(valueOf);
		}
		
		String primary_Camera = map.get("primary_camera");
		if(primary_Camera!=null){
			mobile.setPrimary_Camera(Float.valueOf(primary_Camera));
		}
		
		// flash
		String primary_Camera_flash = map.get("Primary");
		if(primary_Camera_flash!=null && primary_Camera_flash.contains("flash")){
			mobile.setFlash(true);
		}
		
		
		String features = map.get("Features");
		mobile.setFeatures(features);
		
		String video = map.get("video");
		mobile.setVideo(video);
	
	// TODO
		String secondary_Camera = map.get("Secondary");
		if(secondary_Camera!=null && secondary_Camera.contains("VGA/") && secondary_Camera.contains("MP")){
			secondary_Camera = secondary_Camera.split("VGA/")[1];
			int indexOf = secondary_Camera.indexOf("MP");
			String substring = secondary_Camera.substring(0, indexOf);
			Float valueOf = Float.valueOf(substring.trim());
			if(valueOf!=null){
				mobile.setSecondary_Camera(valueOf);
			}
		
		}else if(secondary_Camera!=null && secondary_Camera.contains("VGA@") && secondary_Camera.contains("MP")){
			secondary_Camera = secondary_Camera.split("VGA@")[1];
			int indexOf = secondary_Camera.indexOf("MP");
			String substring = secondary_Camera.substring(0, indexOf);
			Float valueOf = Float.valueOf(substring.trim());
			if(valueOf!=null){
				mobile.setSecondary_Camera(valueOf);
			}
		
		}else if(secondary_Camera!=null && secondary_Camera.contains("MP")){
			int indexOf = secondary_Camera.indexOf("MP");
			String substring = secondary_Camera.substring(0, indexOf);
			Float valueOf = Float.valueOf(substring.trim());
			if(valueOf!=null){
				mobile.setSecondary_Camera(valueOf);
			}
		}
		
		String alert_Types = map.get("Alert types");
		mobile.setAlert_Types(alert_Types);
		
		String loudspeaker = map.get("Loudspeaker");
		mobile.setLoudspeaker(loudspeaker);
		
		String headphones = map.get("3.5mm jack");
		mobile.setHeadphones(headphones);
		
		String wlan = map.get("WLAN");
		
		if(wlan.contains("Wi-Fi Direct")){
			mobile.setWi_Fi_Direct(true);
		}else{
			mobile.setWi_Fi_Direct(false);
		}
		
		if(wlan.contains("Wi-Fi")){
			mobile.setWi_Fi(true);;
		}else{
			mobile.setWi_Fi(false);
		}
		
		if(wlan.contains("Wi-Fi") && wlan.contains(",")){
			String wifiStd = wlan.split(",")[0];
			mobile.setWi_Fi_Standards_Supported(wifiStd);
		}else{
			mobile.setWi_Fi(false);
		}
		
		String bluetooth = map.get("Bluetooth");
		mobile.setBluetooth(bluetooth);
		
		String gps = map.get("GPS");
		mobile.setGps(gps);
		
		String nfc = map.get("NFC");
		mobile.setNfc(nfc);
		
		String radio = map.get("Radio");
		mobile.setRadio(radio);
		
		String usb = map.get("USB");
		mobile.setUsb(usb);
		
		String sensors = map.get("Sensors");
		mobile.setSensors(sensors);
		
		String messaging = map.get("Messaging");
		mobile.setMessaging(messaging);
		
		String browser = map.get("Browser");
		mobile.setBrowser(browser);
		
		String java = map.get("Java");
		mobile.setJava(java);
		
		String standBy = map.get("Stand-by");
		mobile.setStandBy(standBy);
		
		String talkTime = map.get("Talk time");
		mobile.setTalkTime(talkTime);
		
		String batteryCapactiy = map.get("batteryCapacity");
		if (batteryCapactiy != null && batteryCapactiy.contains("/")) {
			mobile.setBatteryCapactiy(Integer.valueOf(batteryCapactiy.split("/")[0]));
		} else if (batteryCapactiy != null) {
			mobile.setBatteryCapactiy(Integer.valueOf(batteryCapactiy));
		}
		
		String batteryType = map.get("batteryType");
		mobile.setBatteryType(batteryType);
		
		String colors = map.get("Colors");
		mobile.setColors(colors);
		
		String year1 = map.get("year");
		if(year1!=null){
			mobile.setYear(Integer.valueOf(year1));
		}
		
		String month = map.get("month");
		if(month!=null){
			mobile.setMonth(month);
		}
		
		String version = map.get("version");
		System.out.println("version::"+version);
		if(version!=null){
			mobile.setOsVersion(version);
		}
		
		String internal_storage = map.get("internal_storage");
		if(internal_storage!=null && internal_storage.contains("/") && internal_storage.split("/").length > 1){
			String ram1 = internal_storage.split("/")[0];
			mobile.setInternal_Memory(Integer.valueOf(ram1));
			
			String ram2 = internal_storage.split("/")[1];
			mobile.setInternal_Memory1(Integer.valueOf(ram2));
			
			if(internal_storage.split("/").length > 2){
				String ram3 = internal_storage.split("/")[2];
				mobile.setInternal_Memory2(Integer.valueOf(ram3));
			}
			
			if(internal_storage.split("/").length > 3){
				String ram4 = internal_storage.split("/")[3];
				mobile.setInternal_Memory3(Integer.valueOf(ram4));
			}
		}else if(internal_storage!=null){
			if(internal_storage.contains(".")){
				Double internalMemory = Math.ceil(Double.valueOf(internal_storage));
				mobile.setInternal_Memory(Integer.valueOf(internalMemory.intValue()));
			}else{
				mobile.setInternal_Memory(Integer.valueOf(internal_storage));
			}
				
		}
		
		String internal_storage_in_mb = map.get("internal_storage_mb");
		if(internal_storage_in_mb!=null){
			mobile.setInternal_Memory_In_MB(Integer.valueOf(internal_storage_in_mb));
		}
		
		
		String thickness = map.get("thickness");
		if(thickness!=null){
			mobile.setThinkness(Float.valueOf(thickness));
		}
		return mobile;
	}
	
	public  Map<String, String> readHtml(String fileName) throws ClassNotFoundException, SQLException{

		Map<String, String> map = null;

		try {

			Document doc = null;
			
			HtmldbTest htmldbTest = new HtmldbTest();
			
		
			String content = htmldbTest.crawlData(fileName);
			
			if(content!=null){
				doc	= Jsoup.parse(content);
			}
			
			map = getColumnNameAndValues(doc);
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	

	public static Map<String, String> getColumnNameAndValues(Document doc) {
		
		
		Map<String, String> mp = new LinkedHashMap<String, String>();
		
		String title = doc.select("h1.specs-phone-name-title").text();
		mp.put("title", title);
		
		ListIterator<Element> specList = doc.select(".specs-brief-accent").listIterator();
		
		while (specList.hasNext()) {
			// System.out.println( specList.next() );
			Document doc1 = Jsoup.parse(specList.next().toString());
			System.out.println("document::::" + doc1.text());
			String str = doc1.text();
			for (Integer i : yearList) {
				if (str.contains(i.toString())) {
					mp.put("year", i.toString());
				}
			}
			
			Set<Entry<String, String>> entrySet = monthMap.entrySet();
			for (Entry<String, String> key : entrySet) {
				if (str.toLowerCase().contains(key.getKey().toLowerCase()) || str.toLowerCase().contains(key.getValue().toLowerCase())) {
					mp.put("month", key.getValue());
					break;
				}
			}
			
			
			if(str.contains("OS")){
				String[] split = str.split("OS,");
				if(split.length>1){
					String version = split[1].trim();
					mp.put("version", version.substring(1, version.length()));
				}
			}
			
			if(str.contains("storage") && str.contains("GB")){
				String[] split = str.split("GB");
				String storge = split[0];
				mp.put("internal_storage", storge);
			}else if(str.contains("storage") && str.contains("MB")){
				String[] split = str.split("MB");
				String storge = split[0];
				mp.put("internal_storage_mb", storge);
			}
			
			
			if(str.contains("thickness") && str.contains("mm")){
				String[] split = str.split("mm");
				String thickness = split[0];
				String[] split2 = thickness.split("g,");
				if( split2.length >1 && split2[1]!=null){
					mp.put("thickness", split2[1].trim());
				}
			}
			
			Document helpDoc = Jsoup.parse(doc.toString().replace("help accented help-display", "help-accented-help-display")
					.replace("help accented help-camera", "help-accented-help-camera")
					.replace("help accented help-expansion", "help-accented-help-expansion")
					.replace("help accented help-battery", "help-accented-help-battery"));
			
			String display = helpDoc.select(".help-accented-help-display").text();	
			System.out.println("display::"+display);
			
			String camera= helpDoc.select(".help-accented-help-camera").text();	
			System.out.println("camera::"+camera);
			
			if(camera.contains("MP")){
				String[] split = camera.split("MP");
				mp.put("primary_camera", split[0]);
			}
			
			String expansion = helpDoc.select(".help-accented-help-expansion").text();	
			System.out.println("expansion::"+expansion);
			if(expansion.contains("GB") && expansion.contains("RAM")){
				String ram = expansion.split("GB")[0];
				if(expansion.split("RAM").length > 1){
					String chipSet = expansion.split("RAM")[1].trim();
					if (chipSet != null) {
						mp.put("chipSet", chipSet);
					}
				}
				mp.put("ram", ram);
			}else if(expansion.contains("MB") && expansion.contains("RAM")){
				String ram = expansion.split("MB")[0];
				if(expansion.split("RAM").length > 1){
					String chipSet = expansion.split("RAM")[1].trim();
					if (chipSet != null) {
						mp.put("chipSet", chipSet);
					}
				}
				mp.put("ram_size_mb", ram);
			}
			
			String battery = helpDoc.select(".help-accented-help-battery").text();	
			System.out.println("battery::"+battery);
			
			if(battery.contains("mAh")){
			String batteryCapacity = 	battery.split("mAh")[0];
			String battery_type = battery.split("mAh")[1];
			mp.put("batteryCapacity", batteryCapacity);
			mp.put("batteryType", battery_type);
			}
		}
		
		 
		 System.out.println("specs-brief-accent::"+doc.select(".specs-brief-accent").text());
		
		return mp;
	}



	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public static int getYearFromDate(Date date) {
	    int result = -1;
	    if (date != null) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        result = cal.get(Calendar.YEAR);
	    }
	    return result;
	}


}


