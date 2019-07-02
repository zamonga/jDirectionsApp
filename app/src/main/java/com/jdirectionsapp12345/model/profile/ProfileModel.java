package com.jdirectionsapp12345.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProfileModel {

    @SerializedName("data")
    private List<ProfileDataModel> data = new ArrayList<>();

    @SerializedName("error")
    String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ProfileModel(List<ProfileDataModel> data) {
        this.data = data;
    }

    public List<ProfileDataModel> getData() {
        return data;
    }

    public void setData(List<ProfileDataModel> data) {
        this.data = data;
    }
}
