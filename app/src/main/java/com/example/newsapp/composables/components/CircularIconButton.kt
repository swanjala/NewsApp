package com.example.newsapp.composables.components

import android.service.autofill.OnClickAction
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.android.material.R.*

@Composable
fun CircularIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    labelResource: String?  = null,
    clickAction:() -> Unit
) {
    Box(modifier = modifier) {
        ContentBox(
            modifier = Modifier.clickable { clickAction() },
            shape = CircleShape
        ) {
            Icon(
               modifier = Modifier.padding(
                   horizontal = CircularIconButtonDimens.iconPadding,
                   vertical = CircularIconButtonDimens.iconPadding),
                painter = icon,
                tint = Color.Unspecified,
                contentDescription = labelResource

               )
        }
    }
}

private object CircularIconButtonDimens {
    val iconPadding = 8.dp
}

@Preview
@Composable
fun CircularIconButtonPreview() {
    NewsAppTheme {
        CircularIconButton(
            icon = painterResource(
                id = drawable.material_ic_keyboard_arrow_left_black_24dp
            ),
            clickAction = {}
        )
    }
}
