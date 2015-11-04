/*
 * Decompiled with CFR 0_102.
 */
package com.mobilestree.mobile;

public class AutoCompleteMobileVo {
    private int mobileId;
    private String name;
    private String imagePath;
    private String title;
    private String label;
    private String urlTitle;

    public String getUrlTitle() {
        return this.urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getMobileId() {
        return this.mobileId;
    }

    public void setMobileId(int mobileId) {
        this.mobileId = mobileId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return "AutoCompleteMobileVo [mobileId=" + this.mobileId + " name=" + this.name + " title=" + this.title + "]";
    }
}