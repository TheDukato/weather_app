package com.Study_6.proj;

public class Measurement {

    private String value;
    private String timestampOf;

    public Measurement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getTimestampOf() {
        return timestampOf;
    }

    public void setTimestampOf(String timestampOf) {
        this.timestampOf = timestampOf;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
