package com.example.newsapp.data.datastore

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.ArticleSource
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.flow.flowOf

import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class DataRepositoryImplTest {

    private val newsRemoteDataStore = mockk<NewsRemoteDataStore>()
    private val newsLocalDataStore = mockk<NewsLocalDataStore>()

    private val subject: DataRepository by lazy {
        DataRepositoryImpl(newsRemoteDataStore, newsLocalDataStore)
    }

    @Test
    fun `test returns saved articles`() {
        coEvery { subject.getSavedArticles() } returns
                flowOf(
                    listOf(
                        Article(
                            id = null,
                            author = "",
                            title = "",
                            description = "",
                            source = ArticleSource(
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

    @Test
    fun `test save news article`() {
        coEvery {
            subject.insertNewArticle(
                Article(
                    id = null,
                    author = "",
                    title = "",
                    description = "",
                    source = ArticleSource(
                        id = null,
                        name = ""
                    ),
                    url = "",
                    urlToImage = "",
                    publishedAt = "",
                    content = ""
                )
            )
        } returns (true)
    }
}