package com.example.admin.courseregistration.model;

/**
 * Created by trycatch on 2017. 3. 10..
 */

public class MajorModel {
    String major;
    String value;

    public MajorModel(String major, String value) {
        this.major = major;
        this.value = value;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
