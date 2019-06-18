package com.jdirectionsapp123456.response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.jdirectionsapp123456.model.City.CityModel;
import com.jdirectionsapp123456.model.Country.CountryModel;
import com.jdirectionsapp123456.model.category.CategoryModel;
import com.jdirectionsapp123456.model.profile.ProfileModel;

public interface ApiInterface {

    @GET("service.php?action=country")
    Call<CountryModel> getCountryList();

    @GET("service.php?action=city")
    Call<CityModel> getSubCityList(@Query("country") String city_name);

    @GET("service.php?action=categoryList")
    Call<CategoryModel> getCategoryList(@Query("name") String name);

//    @GET("service.php?action=people")
//    Call<ProfileModel> getProfileList(@Query("city") String city, @Query("category") String category);

    @GET("service.php?action=peopleInCity")
    Call<ProfileModel> getProfileList(@Query("city") String city, @Query("gender") String gender);
}
