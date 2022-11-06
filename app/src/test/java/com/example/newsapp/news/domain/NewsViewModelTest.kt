package com.example.newsapp.news.domain

import com.example.newsapp.di.DaggerTestAppComponent
import com.example.newsapp.di.TestAppComponent
import com.example.newsapp.di.TestBaseApplication
import com.example.newsapp.news.module.NewsRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class NewsViewModelTest {
    @Inject
    lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        val component: TestAppComponent = DaggerTestAppComponent.factory()
            .create(TestBaseApplication())
        component.inject(this)
    }

    @Test
    fun testFetchNews_articles_greater_than_zero() = runBlocking{

        val itemSize = newsRepository.getNewsData()?.articles?.size
        val hasDataValues = itemSize?.compareTo(0)

        assertThat( hasDataValues, `is`(greaterThan(0)))
    }
}
