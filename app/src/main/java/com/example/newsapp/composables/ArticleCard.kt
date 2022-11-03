package com.example.newsapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.network.model.Article
import java.util.*


@Composable
fun ArticleCard(article: Article) = with(article) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(10.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = urlToImage),
            contentDescription = null,
            modifier = Modifier
                .width(90.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(16.dp)),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState()).wrapContentHeight().padding(bottom = 20.dp)) {
            Text(
                text = title ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = ArticleCardDimens.fontSizeLarge
            )
            Text(text = author ?: "", fontSize = 13.sp)
            Text(
                text = publishedAt?.format(locale = Locale.getDefault()) ?: "",
                fontSize = ArticleCardDimens.fontSizeMedium
            )
            Text(
                text = description ?: "",
                color = Color.Gray,
                fontSize = ArticleCardDimens.fontSizeSmall
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
    }
    Spacer(modifier = Modifier.height(30.dp))
}

object ArticleCardDimens {
    val fontSizeLarge = 16.sp
    val fontSizeMedium = 13.sp
    val fontSizeSmall = 12.sp

}
