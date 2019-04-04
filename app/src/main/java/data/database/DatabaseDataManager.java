package data.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import data.api.ApiManager;
import data.database.accessobjects.ArticleAccessObject;
import data.database.accessobjects.SourcesAccessObject;
import data.datamodels.DataResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatabaseDataManager extends AsyncTask<Void, Void,Void> {

    private  ArticleAccessObject articleAccessObject;
    private  SourcesAccessObject sourcesAccessObject;

    private ApiManager apiManager;

    private  Context context;
    private String query;

    public DatabaseDataManager(Context context, AppDatabase appDatabase, String query) {
        articleAccessObject = appDatabase.articleAccessObject();
        sourcesAccessObject = appDatabase.sourcesAccessObject();
        this.context= context;
        this.query = query;
    }

    public DatabaseDataManager(Context context, AppDatabase appDatabase){
        this.context = context;
        sourcesAccessObject = appDatabase.sourcesAccessObject();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        if (query == null){
            apiManager = new ApiManager(context);

            Call<DataResponse> sourceCall = apiManager.getSources();
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



        } else {

            Log.d("datbase manafer",query);

            apiManager = new ApiManager(context, query);
            Call<DataResponse> call = apiManager.getArticles();
            call.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {


                    if (response.body() != null) {

                        /* Consider removing loop*/

                        Log.d("Data gotten:", String.valueOf(response.body()));

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
        }
        return null;
    }
}
