package com.mobile.vo;

import java.util.ArrayList;
import java.util.List;

public class DataMobileVO {
	
	private String queryString;
	
	private List<AutoCompleteMobileVo> mobileVoList = new ArrayList<AutoCompleteMobileVo>();

	public String getQueryString() {
		return queryString;
	}


	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}


	public List<AutoCompleteMobileVo> getMobileVoList() {
		return mobileVoList;
	}


	public void setMobileVoList(List<AutoCompleteMobileVo> mobileVoList) {
		this.mobileVoList = mobileVoList;
	}


	@Override
	public String toString() {
	   return "DataObject [queryString=" + queryString+" List: "+ mobileVoList + "]";
	}

}