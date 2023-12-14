package com.example.newsapp.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.data.model.Article

@Composable
fun TopCard(
    articleItem: Article
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(ArticleCardDimens.roundedCornerDimens))
            .width(350.dp)
            .background(Color.Gray.copy(alpha = 0.2f))
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = articleItem.urlToImage),
            contentDescription = null,
            modifier = Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp)
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.5f),
                            Color.Black.copy(alpha = 0.9f)
                        )
                    )
                )
                .height(90.dp)
                .align(Alignment.BottomCenter)
        ) {
            Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                Text(
                    text = articleItem.title ?: "title",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = ArticleCardDimens.fontSizeLarge
                )
                Text(
                    text = articleItem.description ?: "Description",
                    color = Color.White,
                    fontSize = ArticleCardDimens.fontSizeMedium
                )
            }
        }
    }
}
