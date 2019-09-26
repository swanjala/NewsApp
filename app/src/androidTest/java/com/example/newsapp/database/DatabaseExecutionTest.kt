package com.example.newsapp.database

import com.example.newsapp.datamodel.DataModelTestBuilder

import org.junit.Test

import java.util.ArrayList

import com.example.newsapp.data.datamodels.Articles

import com.example.newsapp.LiveDataTestManager.retrieveValue

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.notNullValue


class DatabaseExecutionTest : DatabaseTest() {

    @Test
    @Throws(InterruptedException::class)
    fun testDbSavesRecord() {
        val articlesList = ArrayList<Articles>()

        val articles = DataModelTestBuilder.createArticle(
                "Wolf Blitzer",
                "Sky is falling",
                "Movie about a law firm",
                "12", "cnn.com",
                "2019-03-24T15:13:00Z",
                "Medical journalism")
        articlesList.add(articles)

        appDatabase.articleAccessObject().createDataIfNotExists(articles)

        val databaseResult = retrieveValue(appDatabase.articleAccessObject()
                .fetchAllData())
        assertThat(databaseResult, notNullValue())

        assertThat<String>(databaseResult[0].title, `is`("Sky is falling"))

    }


}
