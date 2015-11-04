/*
 * Decompiled with CFR 0_102.
 */
package com.mobilestree.mobile.common;

import java.util.Map;

public class CommonHelper {
    public static void extractCatageryName(Map<String, String[]> catagoryUrl, String url, String mobileConstants) {
        if (url.contains((CharSequence)"_")) {
            String substring = url.substring(mobileConstants.length() + 1);
            String[] spec = substring.split("_");
            catagoryUrl.put(mobileConstants, spec);
        } else {
            String substring = url.substring(mobileConstants.length() + 1);
            String[] spec = new String[]{substring};
            catagoryUrl.put(mobileConstants, spec);
        }
    }
}