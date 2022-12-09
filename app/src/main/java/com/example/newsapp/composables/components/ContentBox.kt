package com.example.newsapp.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.composables.components.ContentBoxDimens.buttonBottomPadding
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun ContentBox(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(ContentBoxDimens.cornerRadius),
    backgroundColor: Color = Color.White,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape)
            .padding(bottom = buttonBottomPadding)
            .composed { modifier }
    ) {
        Box(
            modifier = Modifier
                .clip(shape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}

private object ContentBoxDimens {
    val cornerRadius = 16.dp
    val buttonBottomPadding = 2.dp
}

@Preview
@Composable
fun ContentBoxPreview() {
    NewsAppTheme {
        ContentBox {
            Text(
                modifier = Modifier.padding(vertical = 30.dp),
                text = "Preview"
            )
        }
    }
}