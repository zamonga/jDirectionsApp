package com.jdirectionsapp12345.model.category;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryModel {

    @SerializedName("data")
    private List<CategoryListModel> data = new ArrayList<>();

    @SerializedName("error")
    String error;

    public CategoryModel(List<CategoryListModel> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<CategoryListModel> getData() {
        return data;
    }

    public void setData(List<CategoryListModel> data) {
        this.data = data;
    }
}
