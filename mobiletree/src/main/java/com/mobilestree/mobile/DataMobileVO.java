/*
 * Decompiled with CFR 0_102.
 */
package com.mobilestree.mobile;

import com.mobilestree.mobile.AutoCompleteMobileVo;
import java.util.ArrayList;
import java.util.List;

public class DataMobileVO {
    private String queryString;
    private List<AutoCompleteMobileVo> mobileVoList = new ArrayList<AutoCompleteMobileVo>();

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public List<AutoCompleteMobileVo> getMobileVoList() {
        return this.mobileVoList;
    }

    public void setMobileVoList(List<AutoCompleteMobileVo> mobileVoList) {
        this.mobileVoList = mobileVoList;
    }

    public String toString() {
        return "DataObject [queryString=" + this.queryString + " List: " + this.mobileVoList + "]";
    }
}