package com.example.newsapp.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.R
import com.example.newsapp.composables.components.ArticleCardDimens.childColumBottomPadding
import com.example.newsapp.composables.components.ArticleCardDimens.childColumnWidth
import com.example.newsapp.composables.components.ArticleCardDimens.fontSizeMedium
import com.example.newsapp.composables.components.ArticleCardDimens.genericPadding
import com.example.newsapp.composables.components.ArticleCardDimens.imageWidth
import com.example.newsapp.composables.components.ArticleCardDimens.parentRowHeight
import com.example.newsapp.composables.components.ArticleCardDimens.roundedCornerDimens
import com.example.newsapp.composables.components.ArticleCardDimens.sharedRowPadding
import com.example.newsapp.composables.components.ArticleCardDimens.sharedSpacerHeight
import com.example.newsapp.composables.components.ArticleCardDimens.surfaceTopPadding
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.ArticleSource
import com.example.newsapp.ui.theme.NewsAppTheme
import java.util.Locale

@Composable
fun ArticleCard(
    sourceType: SourceType,
    article: Article,
    handleArticleSelection: (SourceType, Article) -> Unit
) = with(article) {

    Surface(
        modifier = Modifier.padding(
            start = genericPadding,
            end = genericPadding,
            top = surfaceTopPadding,
            bottom = genericPadding
        )
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(roundedCornerDimens))
                .height(parentRowHeight)
                .background(Color.Gray.copy(alpha = 0.2f))
                .selectable(
                    selected = false,
                    onClick = { handleArticleSelection(sourceType, this) }
                )
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = urlToImage),
                contentDescription = null,
                modifier = Modifier
                    .width(imageWidth)
                    .fillMaxHeight(),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(sharedSpacerHeight))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .width(childColumnWidth)
                    .padding(bottom = childColumBottomPadding, end = sharedRowPadding)
            ) {
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = title ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = ArticleCardDimens.fontSizeLarge
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = author ?: "", fontSize = fontSizeMedium)
                Text(
                    text = publishedAt?.format(locale = Locale.getDefault()) ?: "",
                    fontSize = fontSizeMedium
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = description ?: "",
                    color = Color.Gray,
                    fontSize = ArticleCardDimens.fontSizeSmall
                )
            }
        }
    }
}

object ArticleCardDimens {
    val fontSizeXXLarge = 17.sp
    val fontSizeXLarge = 15.sp
    val fontSizeLarge = 13.sp
    val fontSizeMedium = 10.sp
    val fontSizeSmall = 9.sp

    val parentRowHeight = 150.dp
    val sharedRowPadding = 10.dp
    val imageWidth = 120.dp
    val roundedCornerDimens = 16.dp
    val sharedSpacerHeight = 10.dp
    val childColumnWidth = 160.dp
    val childColumBottomPadding = 20.dp
    val genericPadding = 5.dp
    val surfaceTopPadding = 2.dp
}

@Preview
@Composable
fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(
            sourceType = SourceType.ONLINE,
            article = Article(
                id = R.drawable.add_news,
                author = "Fantastic Author",
                title = "Tectonic surprises",
                description = "A seismic event across the globe",
                source = ArticleSource(
                    id = null,
                    name = "String"
                ),
                url = "",
                urlToImage = "",
                publishedAt = "",
                content = ""
            ),
            handleArticleSelection = { _, _ -> }
        )
    }
}
