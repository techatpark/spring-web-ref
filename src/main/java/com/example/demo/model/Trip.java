package com.example.demo.model;

public class Trip {
    private  Integer avilableSheets;
    private String rootName;
    private  String fare;
    private  String busType;
    public Trip(Integer avilableSheets, String rootName, String fare, String busType) {
        this.avilableSheets = avilableSheets;
        this.rootName = rootName;
        this.fare = fare;
        this.busType = busType;
    }

    public Integer getAvilableSheets() {
        return avilableSheets;
    }

    public String getRootName() {
        return rootName;
    }

    public String getFare() {
        return fare;
    }

    public String getBusType() {
        return busType;
    }
}
