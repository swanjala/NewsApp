package com.example.newsapp.data.api;

import com.example.newsapp.data.datamodels.DataResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {

    @GET("everything")
    Call<DataResponse> getAllNews(@Query("q") String query,
                                  @Query("apiKey") String apiKey);

    @GET("everything")
    Call<DataResponse> getNewsByDomains(@Query("domains") String domains,
                                        @Query("apiKey") String apiKey);

    @GET("sources")
    Call<DataResponse> getSources(@Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<DataResponse> getTopHeadlines(@Query("country") String country,
                                       @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<DataResponse> getTopHeadlinesByCountryCategory(@Query("country") String country,
                                                @Query("category") String category,
                                                @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<DataResponse> getTopHeadlineBySearch(@Query("q") String query,
                                              @Query("apiKey") String apiKey);


}
