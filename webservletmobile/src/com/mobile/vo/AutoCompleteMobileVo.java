package com.mobile.vo;

public class AutoCompleteMobileVo { 
	
	private int mobileId;
	
	private String name;
	
	private String imagePath;
	
	private String title;
	
	private String label;
	
	private String urlTitle;
	
	
	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getMobileId() {
		return mobileId;
	}

	public void setMobileId(int mobileId) {
		this.mobileId = mobileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@Override
	public String toString() {
	   return "AutoCompleteMobileVo [mobileId=" + mobileId+" name="+name+" title="+title+"]";
	}
	
}
