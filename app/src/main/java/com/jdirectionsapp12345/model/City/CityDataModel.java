package com.jdirectionsapp12345.model.City;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityDataModel {


    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("country_id")
    @Expose
    private String countryid;

    public String getCountryid() {
        return countryid;
    }

    public void setCountryid(String countryid) {
        this.countryid = countryid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
