package data.database;

import android.os.AsyncTask;
import android.util.Log;

import data.api.ApiManager;
import data.database.utils.ArticleAccessObject;
import data.datamodels.DataResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopulateDbAsync extends AsyncTask<Void, Void,Void> {

    private final ArticleAccessObject articleAccessObject;

    private ApiManager apiManager;

    public PopulateDbAsync(AppDatabase appDatabase) {
        articleAccessObject = appDatabase.articleAccessObject();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        articleAccessObject.createDataIfNotExists();


        apiManager = new ApiManager();
        Call<DataResponse> call = apiManager.getArticles();

        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {

                Log.d("DataResponse data", String.
                        valueOf(response.body().getArticles().get(2).getAuthor()));
                articleAccessObject.createDataIfNotExists(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.d("Data", t.getLocalizedMessage());

            }
        });
        return null;
    }
}
