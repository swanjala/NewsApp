package com.example.newsapp.composables.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.newsapp.R
import com.google.android.material.R.*

sealed class TopBarAction(
    @DrawableRes val iconResourceId: Int,
    @StringRes val labelResourceId: Int
) {
    object Back: TopBarAction(
        iconResourceId = drawable.material_ic_keyboard_arrow_left_black_24dp,
        labelResourceId = R.string.navigation_back
    )
}
