package data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import data.api.ApiManager;
import data.database.utils.ArticleAccessObject;
import data.database.utils.SourcesAccessObject;
import data.datamodels.DataResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopulateDbAsync extends AsyncTask<Void, Void,Void> {

    private final ArticleAccessObject articleAccessObject;
    private final SourcesAccessObject sourcesAccessObject;

    private ApiManager apiManager;

    private  Context context;

    public PopulateDbAsync(Context context, AppDatabase appDatabase) {
        articleAccessObject = appDatabase.articleAccessObject();
        sourcesAccessObject = appDatabase.sourcesAccessObject();
        this.context= context;
    }

    @Override
    protected Void doInBackground(final Void... params) {
        articleAccessObject.createDataIfNotExists();


        apiManager = new ApiManager(context);
        Call<DataResponse> call = apiManager.getArticles();
        Call<DataResponse> sourceCall = apiManager.getSources();

        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {


                Log.d("Response Check", String.valueOf(response.body().getTotalResults()));
                if (response.body() != null) {

                    for (int i = 0; i < response.body().getArticles().size(); i++) {

                        articleAccessObject.createDataIfNotExists(response.body().getArticles().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.d("Data", t.getLocalizedMessage());

            }
        });

        sourceCall.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> sourceResponse) {
                Log.d("Response sources data", String.valueOf(sourceResponse.body().getSources()));

                if (sourceResponse.body() != null){
                    for (int index = 0; index < sourceResponse.body().getSources().size() ; index++) {
                        sourcesAccessObject.createSourceDataIfNotExists(sourceResponse.body()
                                .getSources().get(index));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.d("Sources Error", t.getLocalizedMessage());
            }
        });
        return null;
    }
}
