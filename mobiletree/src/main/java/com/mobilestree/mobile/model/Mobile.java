/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.persistence.Column
 *  javax.persistence.Entity
 *  javax.persistence.GeneratedValue
 *  javax.persistence.GenerationType
 *  javax.persistence.Id
 *  javax.persistence.JoinColumn
 *  javax.persistence.ManyToOne
 *  javax.persistence.Table
 *  javax.persistence.Temporal
 *  javax.persistence.TemporalType
 */
package com.mobilestree.mobile.model;

import com.mobilestree.mobile.model.Company;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Mobile")
public class Mobile
implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="Brand_Name")
    private String brandName;
    @Column(name="Model")
    private String model;
    @Column(name="Title")
    private String title;
    @Column(name="Screen_Type")
    private String screenType;
    @Column(name="Screen_Size")
    private float screenSize;
    @Column(name="Ram")
    private float ram;
    @Column(name="Ram_Size_In_MB")
    private Integer ramSizeInMB;
    @Temporal(value=TemporalType.DATE)
    @Column(name="Announced_Month")
    private Date announced_Month;
    @Column(name="Technology")
    private String technology;
    @Column(name="2G")
    private String band_2G;
    @Column(name="3G")
    private String band_3G;
    @Column(name="4G")
    private String band_4G;
    @Column(name="Speed")
    private String speed;
    @Column(name="GPRS")
    private String gprs;
    @Column(name="EDGE")
    private String edge;
    @Column(name="Status")
    private String status;
    @Column(name="Dimensions")
    private String dimensions;
    @Column(name="Weight")
    private int weight;
    @Column(name="SIM")
    private String sim;
    @Column(name="Removable_Battery")
    private String removable_Battery;
    @Column(name="Display_Type")
    private String display_Type;
    @Column(name="Resolution")
    private String resolution;
    @Column(name="PPL")
    private int ppl;
    @Column(name="Multitouch")
    private String multitouch;
    @Column(name="Protection")
    private String protection;
    @Column(name="OS")
    private String os;
    @Column(name="Chipset")
    private String chipset;
    @Column(name="Processor")
    private String processor;
    @Column(name="GPU")
    private String gpu;
    @Column(name="Card_Slot")
    private String card_Slot;
    @Column(name="Internal_Memory_In_MB")
    private int internal_Memory_In_MB;
    @Column(name="Internal_Memory")
    private int internal_Memory;
    @Column(name="Internal_Ram_Memory")
    private String internal_Ram_Memory;
    @Column(name="External_Memory")
    private int external_Memory;
    @Column(name="Internal_Memory1")
    private int internal_Memory1;
    @Column(name="Internal_Memory2")
    private int internal_Memory2;
    @Column(name="Internal_Memory3")
    private int internal_Memory3;
    @Column(name="Internal_Memory4")
    private int internal_Memory4;
    @Column(name="Primary_Camera")
    private float primary_Camera;
    @Column(name="Secondary_Camera")
    private float secondary_Camera;
    @Column(name="Flash")
    private Boolean flash;
    @Column(name="Features")
    private String features;
    @Column(name="Video")
    private String video;
    @Column(name="Alert_Types")
    private String alert_Types;
    @Column(name="Loudspeaker")
    private String loudspeaker;
    @Column(name="Headphones")
    private String headphones;
    @Column(name="wi_Fi")
    private Boolean wi_Fi;
    @Column(name="Wi_Fi_Direct")
    private Boolean wi_Fi_Direct;
    @Column(name="Wi_Fi_Standards_Supported")
    private String wi_Fi_Standards_Supported;
    @Column(name="Bluetooth")
    private String bluetooth;
    @Column(name="HDMI")
    private Boolean hdmi;
    @Column(name="GPS")
    private String gps;
    @Column(name="NFC")
    private String nfc;
    @Column(name="Radio")
    private String radio;
    @Column(name="USB")
    private String usb;
    @Column(name="Sensors")
    private String sensors;
    @Column(name="Messaging")
    private String messaging;
    @Column(name="Browser")
    private String browser;
    @Column(name="Java")
    private String java;
    @Column(name="Stand_By")
    private String standBy;
    @Column(name="Talk_Time")
    private String talkTime;
    @Column(name="Colors")
    private String colors;
    @Column(name="Music_Play")
    private String musicPlay;
    @Column(name="Battery_Capactiy")
    private Integer batteryCapactiy;
    @Column(name="Battery_Type")
    private String batteryType;
    @Column(name="Image_path1")
    private String image_path1;
    @Column(name="Image_path2")
    private String image_path2;
    @Column(name="Enabled")
    private Boolean enabled;
    @Column(name="year")
    private Integer year;
    @Column(name="month")
    private String month;
    @Column(name="thinkness")
    private Float thinkness;
    @Column(name="os_version")
    private String osVersion;
    @Column(name="dual_sim")
    private Boolean dualSim;
    @Column(name="triple_sim")
    private Boolean tripleSim;
    @Column(name="Upcoming_mobile")
    private Boolean isUpcomingMobile;
    @ManyToOne
    @JoinColumn(name="Company_id")
    private Company company;

    public Mobile() {
    }

    public Mobile(int id, String brandName, String model, String title, String screenType, float screenSize, float ram, Integer ramSizeInMB, Date announced_Month, String technology, String band_2g, String band_3g, String band_4g, String speed, String gprs, String edge, String status, String dimensions, int weight, String sim, String removable_Battery, String display_Type, String resolution, int ppl, String multitouch, String protection, String os, String chipset, String processor, String gpu, String card_Slot, int internal_Memory_In_MB, int internal_Memory, String internal_Ram_Memory, int external_Memory, int internal_Memory1, int internal_Memory2, int internal_Memory3, int internal_Memory4, float primary_Camera, float secondary_Camera, Boolean flash, String features, String video, String alert_Types, String loudspeaker, String headphones, Boolean wi_Fi, Boolean wi_Fi_Direct, String wi_Fi_Standards_Supported, String bluetooth, Boolean hdmi, String gps, String nfc, String radio, String usb, String sensors, String messaging, String browser, String java, String standBy, String talkTime, String colors, String musicPlay, Integer batteryCapactiy, String batteryType, String image_path1, String image_path2, Boolean enabled, Integer year, String month, Float thinkness, String osVersion, Boolean dualSim, Boolean tripleSim, Boolean isUpcomingMobile, Company company) {
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
        this.band_2G = band_2g;
        this.band_3G = band_3g;
        this.band_4G = band_4g;
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
        this.internal_Ram_Memory = internal_Ram_Memory;
        this.external_Memory = external_Memory;
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
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScreenType() {
        return this.screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public float getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    public float getRam() {
        return this.ram;
    }

    public void setRam(float ram) {
        this.ram = ram;
    }

    public Integer getRamSizeInMB() {
        return this.ramSizeInMB;
    }

    public void setRamSizeInMB(Integer ramSizeInMB) {
        this.ramSizeInMB = ramSizeInMB;
    }

    public Date getAnnounced_Month() {
        return this.announced_Month;
    }

    public void setAnnounced_Month(Date announced_Month) {
        this.announced_Month = announced_Month;
    }

    public String getTechnology() {
        return this.technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getBand_2G() {
        return this.band_2G;
    }

    public void setBand_2G(String band_2g) {
        this.band_2G = band_2g;
    }

    public String getBand_3G() {
        return this.band_3G;
    }

    public void setBand_3G(String band_3g) {
        this.band_3G = band_3g;
    }

    public String getBand_4G() {
        return this.band_4G;
    }

    public void setBand_4G(String band_4g) {
        this.band_4G = band_4g;
    }

    public String getSpeed() {
        return this.speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getGprs() {
        return this.gprs;
    }

    public void setGprs(String gprs) {
        this.gprs = gprs;
    }

    public String getEdge() {
        return this.edge;
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDimensions() {
        return this.dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSim() {
        return this.sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getRemovable_Battery() {
        return this.removable_Battery;
    }

    public void setRemovable_Battery(String removable_Battery) {
        this.removable_Battery = removable_Battery;
    }

    public String getDisplay_Type() {
        return this.display_Type;
    }

    public void setDisplay_Type(String display_Type) {
        this.display_Type = display_Type;
    }

    public String getResolution() {
        return this.resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getPpl() {
        return this.ppl;
    }

    public void setPpl(int ppl) {
        this.ppl = ppl;
    }

    public String getMultitouch() {
        return this.multitouch;
    }

    public void setMultitouch(String multitouch) {
        this.multitouch = multitouch;
    }

    public String getProtection() {
        return this.protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getChipset() {
        return this.chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getProcessor() {
        return this.processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getGpu() {
        return this.gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getCard_Slot() {
        return this.card_Slot;
    }

    public void setCard_Slot(String card_Slot) {
        this.card_Slot = card_Slot;
    }

    public int getInternal_Memory_In_MB() {
        return this.internal_Memory_In_MB;
    }

    public void setInternal_Memory_In_MB(int internal_Memory_In_MB) {
        this.internal_Memory_In_MB = internal_Memory_In_MB;
    }

    public int getInternal_Memory() {
        return this.internal_Memory;
    }

    public void setInternal_Memory(int internal_Memory) {
        this.internal_Memory = internal_Memory;
    }

    public String getInternal_Ram_Memory() {
        return this.internal_Ram_Memory;
    }

    public void setInternal_Ram_Memory(String internal_Ram_Memory) {
        this.internal_Ram_Memory = internal_Ram_Memory;
    }

    public int getExternal_Memory() {
        return this.external_Memory;
    }

    public void setExternal_Memory(int external_Memory) {
        this.external_Memory = external_Memory;
    }

    public int getInternal_Memory1() {
        return this.internal_Memory1;
    }

    public void setInternal_Memory1(int internal_Memory1) {
        this.internal_Memory1 = internal_Memory1;
    }

    public int getInternal_Memory2() {
        return this.internal_Memory2;
    }

    public void setInternal_Memory2(int internal_Memory2) {
        this.internal_Memory2 = internal_Memory2;
    }

    public int getInternal_Memory3() {
        return this.internal_Memory3;
    }

    public void setInternal_Memory3(int internal_Memory3) {
        this.internal_Memory3 = internal_Memory3;
    }

    public int getInternal_Memory4() {
        return this.internal_Memory4;
    }

    public void setInternal_Memory4(int internal_Memory4) {
        this.internal_Memory4 = internal_Memory4;
    }

    public float getPrimary_Camera() {
        return this.primary_Camera;
    }

    public void setPrimary_Camera(float primary_Camera) {
        this.primary_Camera = primary_Camera;
    }

    public float getSecondary_Camera() {
        return this.secondary_Camera;
    }

    public void setSecondary_Camera(float secondary_Camera) {
        this.secondary_Camera = secondary_Camera;
    }

    public Boolean getFlash() {
        return this.flash;
    }

    public void setFlash(Boolean flash) {
        this.flash = flash;
    }

    public String getFeatures() {
        return this.features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getVideo() {
        return this.video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAlert_Types() {
        return this.alert_Types;
    }

    public void setAlert_Types(String alert_Types) {
        this.alert_Types = alert_Types;
    }

    public String getLoudspeaker() {
        return this.loudspeaker;
    }

    public void setLoudspeaker(String loudspeaker) {
        this.loudspeaker = loudspeaker;
    }

    public String getHeadphones() {
        return this.headphones;
    }

    public void setHeadphones(String headphones) {
        this.headphones = headphones;
    }

    public Boolean getWi_Fi() {
        return this.wi_Fi;
    }

    public void setWi_Fi(Boolean wi_Fi) {
        this.wi_Fi = wi_Fi;
    }

    public Boolean getWi_Fi_Direct() {
        return this.wi_Fi_Direct;
    }

    public void setWi_Fi_Direct(Boolean wi_Fi_Direct) {
        this.wi_Fi_Direct = wi_Fi_Direct;
    }

    public String getWi_Fi_Standards_Supported() {
        return this.wi_Fi_Standards_Supported;
    }

    public void setWi_Fi_Standards_Supported(String wi_Fi_Standards_Supported) {
        this.wi_Fi_Standards_Supported = wi_Fi_Standards_Supported;
    }

    public String getBluetooth() {
        return this.bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public Boolean getHdmi() {
        return this.hdmi;
    }

    public void setHdmi(Boolean hdmi) {
        this.hdmi = hdmi;
    }

    public String getGps() {
        return this.gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getNfc() {
        return this.nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public String getRadio() {
        return this.radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getUsb() {
        return this.usb;
    }

    public void setUsb(String usb) {
        this.usb = usb;
    }

    public String getSensors() {
        return this.sensors;
    }

    public void setSensors(String sensors) {
        this.sensors = sensors;
    }

    public String getMessaging() {
        return this.messaging;
    }

    public void setMessaging(String messaging) {
        this.messaging = messaging;
    }

    public String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getJava() {
        return this.java;
    }

    public void setJava(String java) {
        this.java = java;
    }

    public String getStandBy() {
        return this.standBy;
    }

    public void setStandBy(String standBy) {
        this.standBy = standBy;
    }

    public String getTalkTime() {
        return this.talkTime;
    }

    public void setTalkTime(String talkTime) {
        this.talkTime = talkTime;
    }

    public String getColors() {
        return this.colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getMusicPlay() {
        return this.musicPlay;
    }

    public void setMusicPlay(String musicPlay) {
        this.musicPlay = musicPlay;
    }

    public Integer getBatteryCapactiy() {
        return this.batteryCapactiy;
    }

    public void setBatteryCapactiy(Integer batteryCapactiy) {
        this.batteryCapactiy = batteryCapactiy;
    }

    public String getBatteryType() {
        return this.batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getImage_path1() {
        return this.image_path1;
    }

    public void setImage_path1(String image_path1) {
        this.image_path1 = image_path1;
    }

    public String getImage_path2() {
        return this.image_path2;
    }

    public void setImage_path2(String image_path2) {
        this.image_path2 = image_path2;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Float getThinkness() {
        return this.thinkness;
    }

    public void setThinkness(Float thinkness) {
        this.thinkness = thinkness;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public Boolean getDualSim() {
        return this.dualSim;
    }

    public void setDualSim(Boolean dualSim) {
        this.dualSim = dualSim;
    }

    public Boolean getTripleSim() {
        return this.tripleSim;
    }

    public void setTripleSim(Boolean tripleSim) {
        this.tripleSim = tripleSim;
    }

    public Boolean getIsUpcomingMobile() {
        return this.isUpcomingMobile;
    }

    public void setIsUpcomingMobile(Boolean isUpcomingMobile) {
        this.isUpcomingMobile = isUpcomingMobile;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String toString() {
        return "id=" + this.id + ", brandName=" + this.brandName + ", model=" + this.model + "title = " + this.title + "screenType = " + this.screenType + "screenResolution = announced_Month = " + this.announced_Month + "Ram =" + this.ram + ", screenSize=" + this.screenSize;
    }
}