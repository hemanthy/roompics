package com.mobile.vo;

public class DatabaseVo {
	
	private Long id;
	
	private String content;
	
	private Boolean isWebContent;
	
	private String URL;
	
	private Long mobileId;
	
	private String brandName;
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getMobileId() {
		return mobileId;
	}

	public void setMobileId(Long mobileId) {
		this.mobileId = mobileId;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsWebContent() {
		return isWebContent;
	}

	public void setIsWebContent(Boolean isWebContent) {
		this.isWebContent = isWebContent;
	}
	
}
