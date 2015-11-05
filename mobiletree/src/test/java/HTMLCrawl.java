import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.mobilestree.mobile.model.Company;
import com.mobilestree.mobile.model.Mobile;

public class HTMLCrawl extends AbstractCheckinTestCase {
	
	
	private static List<Integer> yearList = new ArrayList<Integer>();
	
	private static List<String> monthList = new ArrayList<String>();
	
	private static List<String> companyList = new ArrayList<String>();
	
	private static Map<String,Company> companyMap = new HashMap<String, Company>();
	
	private static Map<String,String> monthMap = new HashMap<String, String>();
	


	public HTMLCrawl(){
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
		
		
	}
	
	
	
	@Test
	public void testReadHTML() throws ClassNotFoundException, SQLException{
		
		
		Session openSession = sessionFactory.openSession();
		Transaction transaction = openSession.beginTransaction();
		
		
		Set<Mobile> mobilesList = new HashSet<Mobile>();
		List<String> fileNameList = new ArrayList<String>();
		
		//Samsung
		fileNameList.add("http://www.gsmarena.com/samsung_galaxy_folder-7453.php");
		fileNameList.add("http://www.gsmarena.com/samsung_galaxy_s6_edge+_duos-7509.php");
		/*fileNameList.add("http://www.gsmarena.com/samsung_galaxy_s5_neo-6506.php");
		fileNameList.add("http://www.gsmarena.com/samsung_galaxy_s4_mini_i9195i-7468.php");
		fileNameList.add("http://www.gsmarena.com/samsung_galaxy_a8_duos-7506.php");
		fileNameList.add("http://www.gsmarena.com/samsung_galaxy_a8-7249.php");
		fileNameList.add("http://www.gsmarena.com/samsung_xcover_550-7119.php");
		fileNameList.add("http://www.gsmarena.com/samsung_galaxy_s6_active-7114.php");
		
		
		// Lenovo
		fileNameList.add("http://www.gsmarena.com/lenovo_phab_plus-7557.php");
		fileNameList.add("http://www.gsmarena.com/lenovo_vibe_s1-7354.php");
		fileNameList.add("http://www.gsmarena.com/lenovo_vibe_shot-7046.php");
		fileNameList.add("http://www.gsmarena.com/lenovo_a319-6789.php");
		fileNameList.add("http://www.gsmarena.com/lenovo_a7000-7088.php");
		fileNameList.add("http://www.gsmarena.com/lenovo_golden_warrior_note_8-6842.php");
		
		// Micromax
		fileNameList.add("http://www.gsmarena.com/micromax_canvas_xpress_2_e313-7452.php");
		fileNameList.add("http://www.gsmarena.com/micromax_bolt_d303-7432.php");
		fileNameList.add("http://www.gsmarena.com/micromax_canvas_selfie_lens_q345-7401.php");
		fileNameList.add("http://www.gsmarena.com/micromax_canvas_sliver_5-7306.php");
		fileNameList.add("http://www.gsmarena.com/micromax_canvas_knight_2_e471-7276.php");
		fileNameList.add("http://www.gsmarena.com/micromax_q372_unite_3-7223.php");
		fileNameList.add("http://www.gsmarena.com/micromax_canvas_spark_q380-7213.php");
		fileNameList.add("http://www.gsmarena.com/micromax_bolt_s300-7162.php");
		fileNameList.add("http://www.gsmarena.com/micromax_bolt_d320-7163.php");
		fileNameList.add("http://www.gsmarena.com/micromax_canvas_juice_2_aq5001-7140.php");
		fileNameList.add("http://www.gsmarena.com/micromax_a109_canvas_xl2-7139.php");
		fileNameList.add("http://www.gsmarena.com/micromax_canvas_4_plus_a315-7143.php");
		
		//HTC Desire
		fileNameList.add("http://www.gsmarena.com/htc_desire_626_(usa)-7421.php");
		fileNameList.add("http://www.gsmarena.com/htc_desire_626s-7418.php");
		fileNameList.add("http://www.gsmarena.com/htc_desire_526-7419.php");
		fileNameList.add("http://www.gsmarena.com/htc_one_me-7275.php");
		fileNameList.add("http://www.gsmarena.com/htc_desire_820g+_dual_sim-7267.php");
		fileNameList.add("http://www.gsmarena.com/htc_desire_326g_dual_sim-7194.php");
		fileNameList.add("http://www.gsmarena.com/htc_one_m9+-6977.php");
		fileNameList.add("http://www.gsmarena.com/htc_desire_820s_dual_sim-7115.php");
		fileNameList.add("http://www.gsmarena.com/htc_one_m9-6891.php");
		fileNameList.add("http://www.gsmarena.com/htc_desire_526g+_dual_sim_-6947.php");
		fileNameList.add("http://www.gsmarena.com/htc_desire_620_dual_sim-6830.php");
		fileNameList.add("http://www.gsmarena.com/htc_desire_816g_dual_sim-6736.php");
		fileNameList.add("http://www.gsmarena.com/htc_one_(m8_eye)-6713.php");
		fileNameList.add("http://www.gsmarena.com/htc_desire_820_dual_sim-6637.php");
		
		// Sony
		fileNameList.add("http://www.gsmarena.com/sony_xperia_z5_premium_dual-7538.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_z5_premium-7536.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_z5_dual-7537.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_m5-7464.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_c5_ultra-7463.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_z4v-7296.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_z3+-6878.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_m4_aqua_dual-7100.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_c5_ultra-7463.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_e3-6634.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_e4-6882.php");
		fileNameList.add("http://www.gsmarena.com/sony_xperia_z3v-6796.php");*/
		
		

			

			
		
	//	fileNameList.add("http://www.gsmarena.com/huawei_honor_4c-7203.php");
	//  fileNameList.add("http://www.gsmarena.com/samsung_galaxy_v_plus-7395.php");
	 	/*fileNameList.add("Motorola Moto E (2nd gen) - Full phone specifications.html");
		fileNameList.add("Huawei G8 - Full phone specifications.html");
		fileNameList.add("Motorola Moto G (2nd gen) - Full phone specifications.html");
		fileNameList.add("Motorola Moto G (3rd gen) - Full phone specifications.html");
		fileNameList.add("Samsung Galaxy Note5 (CDMA) - Full phone specifications.html"); */
		Company company = new Company();
		//company.setId(1);
		company.setEnabled(false);
		company.setBrandName("Samsung");
	//	company.setName("Samsung");
		
		
		for (String fileName : fileNameList) {
			// Read HTML
			Map<String, String> map = readHtml(fileName);
			Mobile mobile = constructMobileObject(map);
				String title = mobile.getTitle();
				if (title != null) {
					String[] titleSplit = title.split(" ");
					int i = 0;
					String modelName = null ;
					for (String str : titleSplit) {
						str = str + " ";
						if(i!=0){
							if(modelName!=null){
								modelName +=  str;
							}else{
								modelName = str;
							}
						}
						i++;
					}
					if(modelName!=null){
						mobile.setModel(modelName.trim());
					}
					mobile.setBrandName(titleSplit[0].trim());
				}
			mobile.setCompany(company);
			mobilesList.add(mobile);
		}
	
	
		
		company.setMobile(mobilesList);
		
		openSession.saveOrUpdate(company);
		transaction.commit();
		openSession.close();
		
	}



	private Mobile constructMobileObject(Map<String, String> map) {
		
		Mobile mobile = new Mobile();
		String title = map.get("title");
		mobile.setTitle(title);
		
		mobile.setModel(title);
		
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
		
		// TODO
		String Announced = map.get("Announced");
		mobile.setAnnounced_Month(new Date());
		
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
		if(internal_Memory!=null && internal_Memory.contains("/")){
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
				}else{
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
				 Integer val1 = Integer.valueOf(str1.trim());
				 mobile.setInternal_Memory2(val1);
			}
			
			if(split.length >= 3 && !split[2].contains("GB")){
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
		}else if(internal_Memory!=null && internal_Memory.contains(",")){
		String str = internal_Memory.split(",")[0];
			int indexGB = str.indexOf("GB");
			String substring = str.substring(0, indexGB);
			Integer valueOf = Integer.valueOf(substring.trim());
			mobile.setInternal_Memory(valueOf);
		}
		
		
		String primary_Camera = map.get("primary_camera");
		mobile.setPrimary_Camera(Float.valueOf(primary_Camera));
		
		
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
		if(secondary_Camera!=null && secondary_Camera.contains("MP")){
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
		
	// TODO
	//	String wlan = map.get("WLAN");
		
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
		if (batteryCapactiy != null) {
			mobile.setBatteryCapactiy(Integer.valueOf(batteryCapactiy));
		}
		
		String batteryType = map.get("batteryType");
		mobile.setBatteryType(batteryType);
		
		String colors = map.get("Colors");
		mobile.setColors(colors);
		
		String year = map.get("year");
		if(year!=null){
			mobile.setYear(Integer.valueOf(year));
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
			mobile.setInternal_Memory(Integer.valueOf(internal_storage));
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
	
	private Map<String, String> readHtml(String fileName) throws ClassNotFoundException, SQLException{

		Map<String, String> map = null;

		try {

			Document doc = null;
			
		//	FileInputStream fileInputStream = new FileInputStream("C://Users//BOSS//Downloads//Compressed//hemanth//gsmarena//"+fileName);
			
		//	Document doc = Jsoup.parse(fileInputStream, "UTF-8", "http://example.com/");
			
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
	

	private static Map<String, String> getColumnNameAndValues(Document doc) {
		Integer colCount = 0;
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
	//	mp.put("weight", weight);
		
		
	/*	String os = doc.select("i.head-icon").text();
		int beginIndex = os.lastIndexOf("v");
		os = os.substring(beginIndex, os.length());
		mp.put("os", os);*/
		
		Elements divList = doc.select("div#specs-list");
		System.out.println("div size : " + divList.size());
		// for(int i=1;i<= divList.size();i++){

		Elements tables = divList.get(0).select("table");
		for (int i = 0; i < tables.size(); i++) {
			Elements trList = tables.get(i).select("tr");
			for (int j = 0; j < trList.size(); j++) {
				Elements tdttlList = trList.select("td.ttl");
				Elements tdnfoList = trList.select("td.nfo");
				for (int y = 0; y < tdttlList.size(); y++) {
		//			System.out.print("tdttlList......."+y+"....."+tdttlList.get(y).text());
					mp.put(tdttlList.get(y).text().trim(), "");	
					if(tdnfoList.size() > y){
						
						Elements aElementList = tdnfoList.get(0).select("a");
						if(aElementList!=null && aElementList.size() >0 && aElementList.get(0)!=null){
							String text = aElementList.get(0).text();
			//				System.out.println("a....."+text);
						}
						
			//			System.out.println("......."+y+"....."+tdnfoList.get(y).text());
						
						if(tdttlList.get(y).text().trim().length()==0 || tdttlList.get(y).text().trim().contains("nbsp")){
							mp.put((++colCount).toString(), tdnfoList.get(y).text().trim().replaceAll("check quality", "").trim());
						}else{
							mp.put(tdttlList.get(y).text().trim(), tdnfoList.get(y).text().trim().replaceAll("check quality", "").trim());								
						}
						
						if(tdnfoList.get(y).text().trim().contains("removable")){
							mp.put("protection", tdnfoList.get(y).text().trim());
						}
						
						if(tdnfoList.get(y).text().trim().contains("mAh")){
							String[] split = tdnfoList.get(y).text().trim().split("mAh");
							String string = split[0];
							String[] split2 = string.split(" ");
							String string2 = split2[split2.length - 1];
							mp.put("batteryCapacity", string2.trim());
						}
						
					}
				}
				
				/*for (int y = 0; y < tdnfoList.size(); y++) {
					System.out.println("tdnfoList......."+y+"....."+tdnfoList.get(y).text());					
				}*/
									
				
				/*for (int y = 0; y < tdList.size(); y++) {
					Element element = tdList.get(y);
					if (element != null) {
						System.out.println(element.text());
					}
				}*/

			}
//		System.out.println("tr........."+trList.size());
//		System.out.println("table...." + i);
	//		System.out.println(colCount);
		}
		return mp;
	}


}


