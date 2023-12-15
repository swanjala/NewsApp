package com.example.newsapp.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.composables.screens.HomeScreenComposableDimens

@Composable
fun CountryButton(imageResource: Int) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(shape = RoundedCornerShape(HomeScreenComposableDimens.roundedCornerDimensions))
            .background(Color.Gray.copy(alpha = 0.2f)),
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}
