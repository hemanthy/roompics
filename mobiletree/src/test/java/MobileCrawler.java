import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mobilestree.mobile.model.Company;

public class MobileCrawler  {
	
static Document doc;
	
	static String title;
	
public	Map<String, String> map = null;
	
	public MobileCrawler(String url) throws IOException, ClassNotFoundException, SQLException {
		//url = "http://www.gsmarena.com/sony_xperia_tipo_dual-4832.php";
		System.out.println("Reading Mobile url : "+url);
		doc = Jsoup.connect(url).userAgent("Chrome").timeout(10000).get();
		
		title = getTitle();
		
		map = getColumnNameAndValues(doc);
	
	}
	
	
	public Document getPageContentByUrl(String url) throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Chrome").timeout(10000).get();
		return doc;
	}
	
	public String getTitle() {
		String title = doc.select("h1.article-info-name").text();
		System.out.println("Mobile title :"+ title);
		return title;
	}
	

	private static Map<String, String> getColumnNameAndValues(Document doc) {
		
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
			
			if (battery.contains("mAh")) {
				String batteryCapacity = battery.split("mAh")[0];
				mp.put("batteryCapacity", batteryCapacity);
				if (battery.split("mAh").length > 1) {
					String battery_type = battery.split("mAh")[1];
					mp.put("batteryType", battery_type);
				}
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
		
		Integer count = 0;
		
		Element element = doc.select("div#specs-list").get(0);
			
			Elements elements = element.getElementsByTag("table");
			
			for (Element element2 : elements) {
				
				Elements rows = element2.getElementsByTag("tr");
				
			for (Element row : rows) {
				
				String header = null;
				String key = "";
				String value = "";
						

				if(row.getElementsByTag("th").size() > 0){
					header = row.getElementsByTag("th").get(0).text();
				//	System.out.println(header);
				}
				
				
				if (row.getElementsByTag("td").size() > 0) {
					key = row.getElementsByTag("td").get(0).text();
					//System.out.println("key::" + key);

				}
				if (row.getElementsByTag("td").size() > 1) {
					value = row.getElementsByTag("td").get(1).text();
					//System.out.println("value::" + value);
				}
				
				
				if(key.contains("nbsp")){
					mp.put((count++).toString(), key.trim().replaceAll("check quality", "").trim());
					continue;
				}
				
				if(value.contains("mAh")){
					String[] split = value.trim().split("mAh");
					String string = split[0];
					String[] split2 = string.split(" ");
					String string2 = split2[split2.length - 1];
					mp.put("batteryCapacity", string2.trim());
				}
				
				if(value.trim().contains("removable")){
					mp.put("protection", value.trim());
				}
				
				mp.put(key.trim(), value.trim().replaceAll("check quality", "").trim());
				

			}
			}
				System.out.println(mp);
		return mp;
	
	}
	
private static List<Integer> yearList = new ArrayList<Integer>();
	
	private static List<String> monthList = new ArrayList<String>();
	
	private static List<String> companyList = new ArrayList<String>();
	
	private static Map<String,Company> companyMap = new HashMap<String, Company>();
	
	private static Map<String,String> monthMap = new HashMap<String, String>();
	



}
