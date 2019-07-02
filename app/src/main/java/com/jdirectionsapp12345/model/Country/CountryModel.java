package com.jdirectionsapp12345.model.Country;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CountryModel {

    @SerializedName("data")
    private List<CountryDataModel> data = new ArrayList<>();

    @SerializedName("error")
    String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public CountryModel(List<CountryDataModel> data) {
        this.data = data;
    }

    public List<CountryDataModel> getData() {
        return data;
    }

    public void setData(List<CountryDataModel> data) {
        this.data = data;
    }
}
