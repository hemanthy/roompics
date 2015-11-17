import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mobilestree.mobile.common.CommonHelper;
import com.mobilestree.mobile.model.Mobile;

public class MobileCrawlerNdtv {
	
static Document doc;
	
	static String title;

    //The path of the folder that you want to save the images to
	
    public	Map<String, String> map = null;
    
    static CommonHelper commonHelper = new CommonHelper();
    
    public Mobile mobile = null;
    
    public static String brandName = null;
	
	public MobileCrawlerNdtv(String url,String brandName) throws IOException, ClassNotFoundException, SQLException {
		//url = "http://www.gsmarena.com/sony_xperia_tipo_dual-4832.php";
		System.out.println("Reading Mobile url : "+url);
		this.brandName = brandName;
		
		doc = Jsoup.connect(url).userAgent("Chrome").timeout(10000).get();
		//FileInputStream fileInputStream = new FileInputStream(
		//		"C:/Users/BOSS/Downloads/Compressed/hemanth/gadgets.ndtv/honor-4c.html");

		//doc = Jsoup.parse(fileInputStream, "UTF-8", "http://gadgets.ndtv.com/huawei-honor-4c-2603");
		
		title = getTitle();
		
		init();
		
		map = getColumnNameAndValues(doc);
		mobile = constructMobileObject();
	
		
	}


	private void init() {
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
	
	
	private Mobile constructMobileObject() {
		Mobile mobile = new Mobile();
		
		mobile.setTitle(title+"2");
		
		String releaseDate = map.get("release date");
		if (releaseDate != null) {
			Integer year = 0;
			Integer monthIndex = null;
			for (Integer yr : yearList) {
				if (releaseDate.contains(yr.toString())) {
					year = yr;
				}
			}
			
			Set<Entry<String, Integer>> entrySet = monthMapIndex.entrySet();
			for (Entry<String, Integer> entry : entrySet) {
				if(releaseDate.contains(entry.getKey())){
					monthIndex = entry.getValue();
				}
			}
			
			Calendar cal = Calendar.getInstance();
			cal.set(year, monthIndex, 1);
			mobile.setAnnounced_Month(cal.getTime());
		}
		
		String screenType = map.get("form factor");
		if(screenType!=null){
			mobile.setScreenType(screenType);
		}
		
		String dimensions = map.get("dimensions (mm)");
		if(dimensions!=null){
			mobile.setDimensions(dimensions);
		}
		
		String weight = map.get("weight (g)");
		if (weight != null) {
			mobile.setWeight(Integer.valueOf(Float.valueOf(weight).intValue()));
		}
		
		String batteryCapacity = map.get("battery capacity (mah)");
		if (batteryCapacity != null) {
			mobile.setBatteryCapactiy(Integer.valueOf(batteryCapacity));
		}
		
		String removableBattery = map.get("removable battery");
		if (removableBattery != null) {
		//	mobile.setBatteryCapactiy(Integer.valueOf(removableBattery));
			// TODO
		}
		
		String colours = map.get("colours");
		if (colours != null) {
			mobile.setColors(colours);
		}
		
		String sar = map.get("sar value");
		if (sar != null) {
			// TODO
		}
		
		String screenSize = map.get("screen size (inches)");
		if (screenSize != null) {
			mobile.setScreenSize(Integer.valueOf(Float.valueOf(screenSize).intValue()));
		}
		
		String touchscreen = map.get("touchscreen");
		if (touchscreen != null) {
			//mobile.setTechnology(technology);
			// TODO
		}
		
		String resolution = map.get("resolution");
		if (resolution != null) {
			mobile.setResolution(resolution);
		}
		
		String ppl = map.get("pixels per inch (ppi)");
		if (ppl != null) {
			mobile.setPpl(Integer.valueOf(ppl));
		}
		
		String processor = map.get("processor");
		if (processor != null) {
			mobile.setProcessor(processor);
		}
		
		String chipSet = map.get("processor make");
		if (chipSet != null) {
			mobile.setChipset(chipSet);
		}
		
		String ram = map.get("ram");
		if (ram != null) {
			if(ram.contains("GB")){
				String	ram1 =	ram.split("GB")[0];
				mobile.setRam(Float.valueOf(ram1));
			}
		}
		
		String rom = map.get("internal storage");
		if (rom != null) {
			if(rom.contains("GB")){
				String	rom1 =	rom.split("GB")[0];
				mobile.setInternal_Memory(Integer.valueOf(rom1));
			}
		}
		
		String storage = map.get("expandable storage type");
		String storageGB = map.get("expandable storage up to (gb)");
		
		if (storage != null) {
			mobile.setCard_Slot(storage+", upto "+storageGB+" GB");
		}
		
		String primaryCamera = map.get("rear camera");
		if (primaryCamera != null) {
			String pc = primaryCamera.split("-megapixel")[0];
			mobile.setPrimary_Camera(Float.valueOf(pc));
		}
		
		String secondaryCamera = map.get("front camera");
		if (secondaryCamera != null) {
			String sc = secondaryCamera.split("-megapixel")[0];
			mobile.setSecondary_Camera(Float.valueOf(sc));
		}
		
		String flash = map.get("flash");
		if (flash != null && flash.contains("Yes")) {
			mobile.setFlash(true);
		}
		
		String os = map.get("operating system");
		if (os != null) {
			mobile.setOs(os);
		}
		
		if (os != null && os.contains("Android")) {
			String version = os.split("Android")[1];
			mobile.setOsVersion(version.trim());
		}
		
		/*if (os != null && os.contains("Android")) {
			String version = os.split("Android")[1];
			mobile.setOsVersion(version);
		}
		*/
		String skin = map.get("skin");
		// TODO
		
		String wifi = map.get("wi-fi");
		if (wifi != null && wifi.contains("Yes")) {
			mobile.setWi_Fi(true);
		}else{
			mobile.setWi_Fi(false);
		}
		
		
		String wifi_support = map.get("wi-fi standards supported");
		if (wifi_support != null) {
			mobile.setWi_Fi_Standards_Supported(wifi_support);
		}
		
		String gps = map.get("gps");
		if (gps != null) {
			mobile.setGps(gps);
		}
		
		String bluetooth = map.get("bluetooth");
		if (bluetooth != null) {
			mobile.setBluetooth(bluetooth);
		}
		
		String nfc = map.get("nfc");
		if (nfc != null) {
			mobile.setNfc(nfc);
		}
		
		String wifi_direct = map.get("wi-fi direct");
		if (wifi_direct != null && wifi_direct.equalsIgnoreCase("Yes")) {
			mobile.setWi_Fi_Direct(true);
		}else{
			mobile.setWi_Fi_Direct(false);
		}
		
		// TODO MHL Out,HDMI
		
		String headphones = map.get("headphones");
		if (headphones != null) {
			mobile.setHeadphones(headphones);
		}
		
		String noOfSims = map.get("number of sims");
		if (noOfSims != null) {
			if(noOfSims.contains("2")){
				mobile.setDualSim(true);
			}else if(noOfSims.contains("3")){
				mobile.setTripleSim(true);
			}else{
				mobile.setDualSim(false);
				mobile.setTripleSim(false);
			}
		}
		
		String g3 = map.get("3g");
		String g3_2 = map.get("3g2");
		if (g3 != null && g3_2!=null){
			if(g3.equalsIgnoreCase("Yes") || g3_2.equalsIgnoreCase("Yes")){
				mobile.setBand_3G("Yes");
			}
		}
		
		String g4 = map.get("4g/ lte");
		String g4_2 = map.get("4g/ lte2");
		if (g4 != null && g4_2!=null){
			if(g4.equalsIgnoreCase("Yes") || g4_2.equalsIgnoreCase("Yes")){
				mobile.setBand_4G("Yes");
			}
		}
		
		return mobile;
	}


	public Document getPageContentByUrl(String url) throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Chrome").timeout(10000).get();
		return doc;
	}
	
	public String getTitle() {
		
		doc = Jsoup.parse(doc.toString().replace("header_wrap marginb0","header_wrap-marginb0"));
		
		
		String title = doc.select("div.header_wrap-marginb0").text();
		System.out.println(title);
		
		return title;
	}
	

	private static Map<String, String> getColumnNameAndValues(Document doc) throws IOException {
		
		Map<String, String> mp = new LinkedHashMap<String, String>();
		
		Elements tableDiv = doc.select(".details_content");
		for (Element element : tableDiv) {

			Elements elements = element.getElementsByTag("table");

			for (Element element2 : elements) {

				Elements rows = element2.getElementsByTag("tr");

				for (Element row : rows) {

					String header = null;
					String key = "";
					String value = "";

					if (row.getElementsByTag("th").size() > 0) {
						header = row.getElementsByTag("th").get(0).text();
					}

					if (row.getElementsByTag("td").size() > 0) {
						key = row.getElementsByTag("td").get(0).text();

					}
					if (row.getElementsByTag("td").size() > 1) {
						value = row.getElementsByTag("td").get(1).text();
					}

					
					if (mp.get("3g") != null && key.trim().contains("3G")) {
						mp.put("3G2".toLowerCase(), value.trim());
					} else if (mp.get("4g/ lte") != null && key.trim().contains("4G/ LTE")) {
						mp.put("4g/ lte2".toLowerCase(), value.trim());
					} else {
						mp.put(key.trim().toLowerCase(), value.trim());
					}

					System.out.println(key.trim() + "..." + value.trim());

				}
			}

		}
		
		CommonHelper.downloadImgAndGetImgPath(doc, mp,title,"st_main_image",brandName);
         
		
		
				System.out.println(mp);
		return mp;
	
	}

	
	private static List<Integer> yearList = new ArrayList<Integer>();
	
	private static List<String> monthList = new ArrayList<String>();
	
	private static Map<String,String> monthMap = new HashMap<String, String>();
	
	private static Map<String,Integer> monthMapIndex = new HashMap<String, Integer>();



}
