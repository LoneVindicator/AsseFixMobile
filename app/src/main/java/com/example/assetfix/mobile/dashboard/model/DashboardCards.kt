package com.example.assetfix.mobile.dashboard.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DashboardCards (
    @StringRes val cardTitle: Int,
    @StringRes val cardNum: Int,
    @DrawableRes val imageResourceId: Int
    )