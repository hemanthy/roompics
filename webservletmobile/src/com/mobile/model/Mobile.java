package com.mobile.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author Hemanth
 *
 */
public class Mobile implements Serializable {
	
	private int id;

	private String brandName;

	private String model;

	private String title;

	private String screenType;

	private float  screenSize;

	private float ram;
	
	private Integer ramSizeInMB;
	
	private Date announced_Month;
	
	private String technology;
	
	private String band_2G;
	
	private String band_3G;
	
	private String band_4G;
	
	private String speed;
	
	private String gprs;
	
	private String edge;
	
	private String status;
	
	private String dimensions;
	
	private int weight;
	
	private String sim;
	
	private String removable_Battery;
	
	private String display_Type;
	
	private String resolution;
	
	private int ppl;
	
	private String multitouch;
	
	private String protection;
	
	private String os;
	
	private String chipset;

	private String processor;
	
	private String gpu;

	private String card_Slot;
	
	private int internal_Memory_In_MB;
	
	private int internal_Memory;
	
	private int internal_Memory1;
	
	private int internal_Memory2;
	
	private int internal_Memory3;
	
	private int internal_Memory4;
	
	private float primary_Camera;
	
	private float secondary_Camera;
	
	private Boolean flash;
	
	private String features;
	
	private String video;

	private String alert_Types;
	
	private String loudspeaker;
	
	private String headphones;
	
	private Boolean wi_Fi;
	
	private Boolean wi_Fi_Direct;
	
	private String wi_Fi_Standards_Supported;
	
	private String bluetooth;
	
	private Boolean hdmi;
	
	private String gps;
	
	private String nfc;
	
	private String radio;
	
	private String usb;
	
	private String sensors;
	
	private String messaging;
	
	private String browser;
	
	private String java;
	
	private String standBy;
	
	private String talkTime;
	
	private String colors;
	
	private String musicPlay;
	
	private Integer batteryCapactiy;
	
	private String batteryType;
	
	private String image_path1;
	
	private String image_path2;
	
	private Boolean enabled;
	
	private Integer year;
	
	private String month;
	
	private Float thinkness;
	
	private String osVersion;
	
	private Boolean dualSim;
	
	private Boolean tripleSim;
	
	private Boolean isUpcomingMobile;
	
    private Company company;

    private Integer company_Id;
	

	public Mobile() {
	}


	

	public Mobile(int id, String brandName, String model, String title, String screenType, float screenSize, float ram,
			Integer ramSizeInMB, Date announced_Month, String technology, String band_2g, String band_3g,
			String band_4g, String speed, String gprs, String edge, String status, String dimensions, int weight,
			String sim, String removable_Battery, String display_Type, String resolution, int ppl, String multitouch,
			String protection, String os, String chipset, String processor, String gpu, String card_Slot,
			int internal_Memory_In_MB, int internal_Memory, int internal_Memory1, int internal_Memory2,
			int internal_Memory3, int internal_Memory4, float primary_Camera, float secondary_Camera, Boolean flash,
			String features, String video, String alert_Types, String loudspeaker, String headphones, Boolean wi_Fi,
			Boolean wi_Fi_Direct, String wi_Fi_Standards_Supported, String bluetooth, Boolean hdmi, String gps,
			String nfc, String radio, String usb, String sensors, String messaging, String browser, String java,
			String standBy, String talkTime, String colors, String musicPlay, Integer batteryCapactiy,
			String batteryType, String image_path1, String image_path2, Boolean enabled, Integer year, String month,
			Float thinkness, String osVersion, Boolean dualSim, Boolean tripleSim, Boolean isUpcomingMobile,
			Company company,Integer company_Id) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.model = model;
		this.title = title;
		this.screenType = screenType;
		this.screenSize = screenSize;
		this.ram = ram;
		this.ramSizeInMB = ramSizeInMB;
		this.announced_Month = announced_Month;
		this.technology = technology;
		band_2G = band_2g;
		band_3G = band_3g;
		band_4G = band_4g;
		this.speed = speed;
		this.gprs = gprs;
		this.edge = edge;
		this.status = status;
		this.dimensions = dimensions;
		this.weight = weight;
		this.sim = sim;
		this.removable_Battery = removable_Battery;
		this.display_Type = display_Type;
		this.resolution = resolution;
		this.ppl = ppl;
		this.multitouch = multitouch;
		this.protection = protection;
		this.os = os;
		this.chipset = chipset;
		this.processor = processor;
		this.gpu = gpu;
		this.card_Slot = card_Slot;
		this.internal_Memory_In_MB = internal_Memory_In_MB;
		this.internal_Memory = internal_Memory;
		this.internal_Memory1 = internal_Memory1;
		this.internal_Memory2 = internal_Memory2;
		this.internal_Memory3 = internal_Memory3;
		this.internal_Memory4 = internal_Memory4;
		this.primary_Camera = primary_Camera;
		this.secondary_Camera = secondary_Camera;
		this.flash = flash;
		this.features = features;
		this.video = video;
		this.alert_Types = alert_Types;
		this.loudspeaker = loudspeaker;
		this.headphones = headphones;
		this.wi_Fi = wi_Fi;
		this.wi_Fi_Direct = wi_Fi_Direct;
		this.wi_Fi_Standards_Supported = wi_Fi_Standards_Supported;
		this.bluetooth = bluetooth;
		this.hdmi = hdmi;
		this.gps = gps;
		this.nfc = nfc;
		this.radio = radio;
		this.usb = usb;
		this.sensors = sensors;
		this.messaging = messaging;
		this.browser = browser;
		this.java = java;
		this.standBy = standBy;
		this.talkTime = talkTime;
		this.colors = colors;
		this.musicPlay = musicPlay;
		this.batteryCapactiy = batteryCapactiy;
		this.batteryType = batteryType;
		this.image_path1 = image_path1;
		this.image_path2 = image_path2;
		this.enabled = enabled;
		this.year = year;
		this.month = month;
		this.thinkness = thinkness;
		this.osVersion = osVersion;
		this.dualSim = dualSim;
		this.tripleSim = tripleSim;
		this.isUpcomingMobile = isUpcomingMobile;
		this.company = company;
		this.company_Id = company_Id;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getBrandName() {
		return brandName;
	}




	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}




	public String getModel() {
		return model;
	}




	public void setModel(String model) {
		this.model = model;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getScreenType() {
		return screenType;
	}




	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}




	public float getScreenSize() {
		return screenSize;
	}




	public void setScreenSize(float screenSize) {
		this.screenSize = screenSize;
	}




	public float getRam() {
		return ram;
	}




	public void setRam(float ram) {
		this.ram = ram;
	}




	public Integer getRamSizeInMB() {
		return ramSizeInMB;
	}




	public void setRamSizeInMB(Integer ramSizeInMB) {
		this.ramSizeInMB = ramSizeInMB;
	}




	public Date getAnnounced_Month() {
		return announced_Month;
	}




	public void setAnnounced_Month(Date announced_Month) {
		this.announced_Month = announced_Month;
	}




	public String getTechnology() {
		return technology;
	}




	public void setTechnology(String technology) {
		this.technology = technology;
	}




	public String getBand_2G() {
		return band_2G;
	}




	public void setBand_2G(String band_2g) {
		band_2G = band_2g;
	}




	public String getBand_3G() {
		return band_3G;
	}




	public void setBand_3G(String band_3g) {
		band_3G = band_3g;
	}




	public String getBand_4G() {
		return band_4G;
	}




	public void setBand_4G(String band_4g) {
		band_4G = band_4g;
	}




	public String getSpeed() {
		return speed;
	}




	public void setSpeed(String speed) {
		this.speed = speed;
	}




	public String getGprs() {
		return gprs;
	}




	public void setGprs(String gprs) {
		this.gprs = gprs;
	}




	public String getEdge() {
		return edge;
	}




	public void setEdge(String edge) {
		this.edge = edge;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getDimensions() {
		return dimensions;
	}




	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}




	public int getWeight() {
		return weight;
	}




	public void setWeight(int weight) {
		this.weight = weight;
	}




	public String getSim() {
		return sim;
	}




	public void setSim(String sim) {
		this.sim = sim;
	}




	public String getRemovable_Battery() {
		return removable_Battery;
	}




	public void setRemovable_Battery(String removable_Battery) {
		this.removable_Battery = removable_Battery;
	}




	public String getDisplay_Type() {
		return display_Type;
	}




	public void setDisplay_Type(String display_Type) {
		this.display_Type = display_Type;
	}




	public String getResolution() {
		return resolution;
	}




	public void setResolution(String resolution) {
		this.resolution = resolution;
	}




	public int getPpl() {
		return ppl;
	}




	public void setPpl(int ppl) {
		this.ppl = ppl;
	}




	public String getMultitouch() {
		return multitouch;
	}




	public void setMultitouch(String multitouch) {
		this.multitouch = multitouch;
	}




	public String getProtection() {
		return protection;
	}




	public void setProtection(String protection) {
		this.protection = protection;
	}




	public String getOs() {
		return os;
	}




	public void setOs(String os) {
		this.os = os;
	}




	public String getChipset() {
		return chipset;
	}




	public void setChipset(String chipset) {
		this.chipset = chipset;
	}




	public String getProcessor() {
		return processor;
	}




	public void setProcessor(String processor) {
		this.processor = processor;
	}




	public String getGpu() {
		return gpu;
	}




	public void setGpu(String gpu) {
		this.gpu = gpu;
	}




	public String getCard_Slot() {
		return card_Slot;
	}




	public void setCard_Slot(String card_Slot) {
		this.card_Slot = card_Slot;
	}




	public int getInternal_Memory_In_MB() {
		return internal_Memory_In_MB;
	}




	public void setInternal_Memory_In_MB(int internal_Memory_In_MB) {
		this.internal_Memory_In_MB = internal_Memory_In_MB;
	}




	public int getInternal_Memory() {
		return internal_Memory;
	}




	public void setInternal_Memory(int internal_Memory) {
		this.internal_Memory = internal_Memory;
	}




	public int getInternal_Memory1() {
		return internal_Memory1;
	}




	public void setInternal_Memory1(int internal_Memory1) {
		this.internal_Memory1 = internal_Memory1;
	}




	public int getInternal_Memory2() {
		return internal_Memory2;
	}




	public void setInternal_Memory2(int internal_Memory2) {
		this.internal_Memory2 = internal_Memory2;
	}




	public int getInternal_Memory3() {
		return internal_Memory3;
	}




	public void setInternal_Memory3(int internal_Memory3) {
		this.internal_Memory3 = internal_Memory3;
	}




	public int getInternal_Memory4() {
		return internal_Memory4;
	}




	public void setInternal_Memory4(int internal_Memory4) {
		this.internal_Memory4 = internal_Memory4;
	}




	public float getPrimary_Camera() {
		return primary_Camera;
	}




	public void setPrimary_Camera(float primary_Camera) {
		this.primary_Camera = primary_Camera;
	}




	public float getSecondary_Camera() {
		return secondary_Camera;
	}




	public void setSecondary_Camera(float secondary_Camera) {
		this.secondary_Camera = secondary_Camera;
	}




	public Boolean getFlash() {
		return flash;
	}




	public void setFlash(Boolean flash) {
		this.flash = flash;
	}




	public String getFeatures() {
		return features;
	}




	public void setFeatures(String features) {
		this.features = features;
	}




	public String getVideo() {
		return video;
	}




	public void setVideo(String video) {
		this.video = video;
	}




	public String getAlert_Types() {
		return alert_Types;
	}




	public void setAlert_Types(String alert_Types) {
		this.alert_Types = alert_Types;
	}




	public String getLoudspeaker() {
		return loudspeaker;
	}




	public void setLoudspeaker(String loudspeaker) {
		this.loudspeaker = loudspeaker;
	}




	public String getHeadphones() {
		return headphones;
	}




	public void setHeadphones(String headphones) {
		this.headphones = headphones;
	}




	public Boolean getWi_Fi() {
		return wi_Fi;
	}




	public void setWi_Fi(Boolean wi_Fi) {
		this.wi_Fi = wi_Fi;
	}




	public Boolean getWi_Fi_Direct() {
		return wi_Fi_Direct;
	}




	public void setWi_Fi_Direct(Boolean wi_Fi_Direct) {
		this.wi_Fi_Direct = wi_Fi_Direct;
	}




	public String getWi_Fi_Standards_Supported() {
		return wi_Fi_Standards_Supported;
	}




	public void setWi_Fi_Standards_Supported(String wi_Fi_Standards_Supported) {
		this.wi_Fi_Standards_Supported = wi_Fi_Standards_Supported;
	}




	public String getBluetooth() {
		return bluetooth;
	}




	public void setBluetooth(String bluetooth) {
		this.bluetooth = bluetooth;
	}




	public Boolean getHdmi() {
		return hdmi;
	}




	public void setHdmi(Boolean hdmi) {
		this.hdmi = hdmi;
	}




	public String getGps() {
		return gps;
	}




	public void setGps(String gps) {
		this.gps = gps;
	}




	public String getNfc() {
		return nfc;
	}




	public void setNfc(String nfc) {
		this.nfc = nfc;
	}




	public String getRadio() {
		return radio;
	}




	public void setRadio(String radio) {
		this.radio = radio;
	}




	public String getUsb() {
		return usb;
	}




	public void setUsb(String usb) {
		this.usb = usb;
	}




	public String getSensors() {
		return sensors;
	}




	public void setSensors(String sensors) {
		this.sensors = sensors;
	}




	public String getMessaging() {
		return messaging;
	}




	public void setMessaging(String messaging) {
		this.messaging = messaging;
	}




	public String getBrowser() {
		return browser;
	}




	public void setBrowser(String browser) {
		this.browser = browser;
	}




	public String getJava() {
		return java;
	}




	public void setJava(String java) {
		this.java = java;
	}




	public String getStandBy() {
		return standBy;
	}




	public void setStandBy(String standBy) {
		this.standBy = standBy;
	}




	public String getTalkTime() {
		return talkTime;
	}




	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime;
	}




	public String getColors() {
		return colors;
	}




	public void setColors(String colors) {
		this.colors = colors;
	}




	public String getMusicPlay() {
		return musicPlay;
	}




	public void setMusicPlay(String musicPlay) {
		this.musicPlay = musicPlay;
	}




	public Integer getBatteryCapactiy() {
		return batteryCapactiy;
	}




	public void setBatteryCapactiy(Integer batteryCapactiy) {
		this.batteryCapactiy = batteryCapactiy;
	}




	public String getBatteryType() {
		return batteryType;
	}




	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}




	public String getImage_path1() {
		return image_path1;
	}




	public void setImage_path1(String image_path1) {
		this.image_path1 = image_path1;
	}




	public String getImage_path2() {
		return image_path2;
	}




	public void setImage_path2(String image_path2) {
		this.image_path2 = image_path2;
	}




	public Boolean getEnabled() {
		return enabled;
	}




	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}




	public Integer getYear() {
		return year;
	}




	public void setYear(Integer year) {
		this.year = year;
	}




	public String getMonth() {
		return month;
	}




	public void setMonth(String month) {
		this.month = month;
	}




	public Float getThinkness() {
		return thinkness;
	}




	public void setThinkness(Float thinkness) {
		this.thinkness = thinkness;
	}




	public String getOsVersion() {
		return osVersion;
	}




	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}




	public Boolean getDualSim() {
		return dualSim;
	}




	public void setDualSim(Boolean dualSim) {
		this.dualSim = dualSim;
	}




	public Boolean getTripleSim() {
		return tripleSim;
	}




	public void setTripleSim(Boolean tripleSim) {
		this.tripleSim = tripleSim;
	}




	public Boolean getIsUpcomingMobile() {
		return isUpcomingMobile;
	}




	public void setIsUpcomingMobile(Boolean isUpcomingMobile) {
		this.isUpcomingMobile = isUpcomingMobile;
	}




	public Company getCompany() {
		return company;
	}




	public void setCompany(Company company) {
		this.company = company;
	}


	public Integer getCompany_Id() {
		return company_Id;
	}


	public void setCompany_Id(Integer company_Id) {
		this.company_Id = company_Id;
	}




	@Override
	public String toString() {
		return "id=" + id + ", brandName=" + brandName+", model="+model+"title = "+title+"screenType = "+screenType+
				"screenResolution = announced_Month = "+announced_Month+ "Ram ="+ram+", screenSize=" +screenSize;
	}
}
