package data.api;

import data.datamodels.DataResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkService {

    //https://newsapi.org/v2/everything?q=bitcoin&apiKey=8dc32f53a41142188e754552063131a7

    @GET("everything?q=bitcoin&apiKey=8dc32f53a41142188e754552063131a7")
    Call<DataResponse> getAllNews();


}
