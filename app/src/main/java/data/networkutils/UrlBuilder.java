package data.networkutils;

import android.net.Uri;


public class UrlBuilder {

    public String buildArticleSearchUrl(String search,String apiKey ){

        Uri.Builder builder = new Uri.Builder();
        builder
                .appendPath("everything")
                .appendQueryParameter("q",search)
                .appendQueryParameter("apiKey",apiKey);
        String myUrl = builder.build().toString();

        return myUrl;
    }
}
