package com.nhatthanh.ass_mob403_thanhlnph16387.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhatthanh.ass_mob403_thanhlnph16387.model.FPhoto;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    Gson gson=new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    APIService API_SERVICE=new Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

@GET("services/rest/?method=flickr.favorites.getList&api_key=6ce304db4bb0e55e224a39ce1e6050c4&user_id=196095361%40N05&extras" +
        "=views%2C+media%2C+path_alias%2C+url_sq%2C+url_t%2C+url_s%2C+url_q%2C+url_m%2C+url_n%2C+url_z%2C+url_c%2C+url_l%2C+url_o&per_page" +
        "=35&page=1&format=json&nojsoncallback=1")
    Call<FPhoto> getListPhoto(@Query("page") int page,@Query("per_page") int per_page);

}
