package com.example.newsapp.data.datastore

import android.provider.ContactsContract.Data
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.Source
import com.example.newsapp.news.module.DataRepository
import com.example.newsapp.news.module.DataRepositoryImpl
import com.example.newsapp.news.module.NewsRemoteDataStore
import com.example.newsapp.utils.UnconfirmedTestDispatcherExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.junit5.MockKExtension

import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class, UnconfirmedTestDispatcherExtension::class)
class DataRepositoryImplTest {

    private val newsRemoteDataStore = mockk<NewsRemoteDataStore>()
    private val newsLocalDataStore = mockk<NewsLocalDataStore>()

    private val subject: DataRepository by lazy {
        DataRepositoryImpl(newsRemoteDataStore, newsLocalDataStore)
    }

    @Test
    fun `test returns data`() {
        coEvery { subject.getNewsData() } returns
                News(
                    "",
                    30,
                    mutableListOf(
                        Article(
                            id = null,
                            author = "",
                            title = "",
                            description = "",
                            source = Source(
                                id = null,
                                name = ""
                            ),
                            url = "",
                            urlToImage = "",
                            publishedAt = "",
                            content = ""
                        )
                    )
                )
    }
}