package com.example.newsapp.database;

import android.util.Log;

import com.example.newsapp.datamodel.DataModelTestBuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import data.datamodels.Articles;

import static com.example.newsapp.LiveDataTestManager.retrieveValue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;


public class DatabaseExecutionTest extends DatabaseTest {

    @Test
    public void testDbSavesRecord() throws InterruptedException{
        List<Articles> articlesList = new ArrayList<>();

        Articles articles = DataModelTestBuilder.createArticle(
                "Wolf Blitzer",
                "Sky is falling",
                "Movie about a law firm",
                 "12","cnn.com",
                "2019-03-24T15:13:00Z",
                "Medical journalism");
        articlesList.add(articles);

        appDatabase.articleAccessObject().createDataIfNotExists(articles);

        List<Articles> databaseResult =
                retrieveValue(appDatabase.articleAccessObject()
                        .fetchAllData());
        assertThat(databaseResult, notNullValue());

        assertThat(databaseResult.get(0).getTitle(), is("Sky is falling"));

    }


}
