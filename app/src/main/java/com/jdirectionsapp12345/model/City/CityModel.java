package com.jdirectionsapp12345.model.City;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CityModel {

    @SerializedName("data")
    private List<CityDataModel> data = new ArrayList<>();

    @SerializedName("error")
    String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public CityModel(List<CityDataModel> data) {
        this.data = data;
    }

    public List<CityDataModel> getData() {
        return data;
    }

    public void setData(List<CityDataModel> data) {
        this.data = data;
    }
}
