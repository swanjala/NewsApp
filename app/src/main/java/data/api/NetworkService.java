package data.api;

import android.util.Log;

import data.datamodels.DataResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {

    @GET("everything")
    Call<DataResponse> getAllNews(@Query("q") String query, @Query("apiKey") String apiKey);

    @GET("sources")
    Call<DataResponse> getSources(@Query("apiKey") String apiKey);

}
