package com.example.newsapp.composables.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.composables.components.SourcesCardDimens.roundedCornerDimens

import com.example.newsapp.data.model.NewsSource
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun SourcesCard(
    newsSources: NewsSource
) = with(newsSources) {
    Surface{
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(roundedCornerDimens))
                .background(Color.Gray.copy(alpha = 0.2f))
                .fillMaxWidth()
                .defaultMinSize()
                .padding(top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = name)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = description)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = category)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = language)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = country)
        }
    }
}

object SourcesCardDimens {
    val roundedCornerDimens = 16.dp
}

@Preview
@Composable
fun SourceCardPreview() {
    NewsAppTheme {
        SourcesCard(
            newsSources = NewsSource(
                id = "id",
                name = "Name",
                description = "Description",
                url = Uri.EMPTY,
                category = "Category",
                language = "Language",
                country = "Country"
            )
        )
    }
}