/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.mobilestree.mobile.model.Mobile
 *  javax.persistence.Column
 *  javax.persistence.Entity
 *  javax.persistence.FetchType
 *  javax.persistence.GeneratedValue
 *  javax.persistence.GenerationType
 *  javax.persistence.Id
 *  javax.persistence.OneToMany
 *  javax.persistence.Table
 *  org.hibernate.annotations.Cascade
 *  org.hibernate.annotations.CascadeType
 *  org.hibernate.annotations.Fetch
 *  org.hibernate.annotations.FetchMode
 */
package com.mobilestree.mobile.model;

import com.mobilestree.mobile.model.Mobile;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Company")
public class Company {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="brandName")
    private String brandName;
    @Column(name="enabled")
    private boolean enabled;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="company")
    @Cascade(value={CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @Fetch(value=FetchMode.JOIN)
    private Set<Mobile> mobile;

    public Company(int id, String brandName, boolean enabled, Set<Mobile> mobile) {
        this.id = id;
        this.brandName = brandName;
        this.enabled = enabled;
        this.mobile = mobile;
    }

    public Company() {
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

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Mobile> getMobile() {
        return this.mobile;
    }

    public void setMobile(Set<Mobile> mobile) {
        this.mobile = mobile;
    }

    public String toString() {
        return "id=" + this.id + ", name=" + this.brandName;
    }
}