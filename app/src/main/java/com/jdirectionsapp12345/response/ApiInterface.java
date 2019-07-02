package com.jdirectionsapp12345.response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.jdirectionsapp12345.model.City.CityModel;
import com.jdirectionsapp12345.model.Country.CountryModel;
import com.jdirectionsapp12345.model.category.CategoryModel;
import com.jdirectionsapp12345.model.profile.ProfileModel;

public interface ApiInterface {

    @GET("service.php?action=country")
    Call<CountryModel> getCountryList(@Query("gender") String gender);

    @GET("service.php?action=city")
    Call<CityModel> getSubCityList(@Query("country") String city_name, @Query("gender") String gender);

    @GET("service.php?action=categoryList")
    Call<CategoryModel> getCategoryList(@Query("name") String name);

//    @GET("service.php?action=people")
//    Call<ProfileModel> getProfileList(@Query("city") String city, @Query("category") String category);

    @GET("service.php?action=peopleInCity")
    Call<ProfileModel> getProfileList(@Query("city") String city, @Query("gender") String gender);
}