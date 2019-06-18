package com.jdirectionsapp123456.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDataModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("closeupimage")
    @Expose
    private String closeupimage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCloseupimage() {
        return closeupimage;
    }

    public void setCloseupimage(String closeupimage) {
        this.closeupimage = closeupimage;
    }

}
