package com.example.newsapp.composables.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.R

@Composable
fun InputCard(
    onSearchClicked: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_news_search),
                    contentDescription = "",
                    modifier = Modifier.clickable(enabled = true, onClick = {
                        onSearchClicked.invoke(text)
                    })
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            placeholder = { Text(text = stringResource(id = R.string.home_label_search_example)) }
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}
