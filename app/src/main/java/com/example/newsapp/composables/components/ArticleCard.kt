package com.example.newsapp.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.R
import com.example.newsapp.composables.components.ArticleCardDimens.bottomSpacer
import com.example.newsapp.composables.components.ArticleCardDimens.childColumBottomPadding
import com.example.newsapp.composables.components.ArticleCardDimens.childColumnWidth
import com.example.newsapp.composables.components.ArticleCardDimens.fontSizeMedium
import com.example.newsapp.composables.components.ArticleCardDimens.imageWidth
import com.example.newsapp.composables.components.ArticleCardDimens.parentRowHeight
import com.example.newsapp.composables.components.ArticleCardDimens.parentRowPadding
import com.example.newsapp.composables.components.ArticleCardDimens.roundedCornerDimens
import com.example.newsapp.composables.components.ArticleCardDimens.sharedSpacerHeight
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Source
import com.example.newsapp.news.fragments.NewsFragmentDirections
import com.example.newsapp.ui.theme.NewsAppTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale

@Composable
fun ArticleCard(
    article: Article,
    navController: NavController?,
    onArticleClicked: () -> Unit
) = with(article) {

    val articleLink = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(parentRowHeight)
            .padding(parentRowPadding)
            .selectable(
                selected = false,
                onClick = {
                   val action = NewsFragmentDirections.nextAction(articleLink)
                    navController?.navigate(action)
                }
            )
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = urlToImage),
            contentDescription = null,
            modifier = Modifier
                .width(imageWidth)
                .fillMaxHeight()
                .clip(RoundedCornerShape(roundedCornerDimens)),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(sharedSpacerHeight))
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .wrapContentHeight()
                .width(childColumnWidth)
                .padding(bottom = childColumBottomPadding)
        ) {
            Text(
                text = title ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = ArticleCardDimens.fontSizeLarge
            )
            Text(text = author ?: "", fontSize = fontSizeMedium)
            Text(
                text = publishedAt?.format(locale = Locale.getDefault()) ?: "",
                fontSize = fontSizeMedium
            )
            Text(
                text = description ?: "",
                color = Color.Gray,
                fontSize = ArticleCardDimens.fontSizeSmall
            )
        }
        Spacer(modifier = Modifier.width(sharedSpacerHeight))
        Column(
            modifier = Modifier.fillMaxHeight(),
           verticalArrangement = Arrangement.Center,
        ) {
            SaveButton(
                onArticleClicked
            )
        }
    }
    Spacer(modifier = Modifier.height(bottomSpacer))
}

@Composable
fun SaveButton(clickAction: () -> Unit) {
    Box(modifier = Modifier.clickable(true, onClick =
        clickAction
    )) {
        Icon(
            tint = Color.Unspecified,
            painter = painterResource(id = R.drawable.add_news),
            contentDescription = "Add"
        )
    }
}

object ArticleCardDimens {
    //fonts
    val fontSizeLarge = 16.sp
    val fontSizeMedium = 13.sp
    val fontSizeSmall = 12.sp

    //views
    val parentRowHeight =200.dp
    val parentRowPadding = 10.dp
    val imageWidth = 120.dp
    val roundedCornerDimens = 16.dp
    val sharedSpacerHeight = 10.dp
    val childColumnWidth = 160.dp
    val childColumBottomPadding = 20.dp
    val bottomSpacer = 30.dp
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(
            article = Article(
                id = 0,
                author = "John Doe",
                title = "Title",
                description = "A seismic event across the globe",
                source = Source(
                    id = null,
                    name = "String"
                ),
                url = "",
                urlToImage = "",
                publishedAt = "",
                content = ""
            ),
            navController = null,
            {}
        )
    }
}
