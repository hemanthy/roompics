/*
 * Decompiled with CFR 0_102.
 */
package com.mobilestree.mobile;

public class DatabaseVo {
    private Long id;
    private String content;
    private Boolean isWebContent;
    private String URL;
    private Long mobileId;
    private String brandName;
    private String imgPath;

    public String getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getMobileId() {
        return this.mobileId;
    }

    public void setMobileId(Long mobileId) {
        this.mobileId = mobileId;
    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String uRL) {
        this.URL = uRL;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsWebContent() {
        return this.isWebContent;
    }

    public void setIsWebContent(Boolean isWebContent) {
        this.isWebContent = isWebContent;
    }
}