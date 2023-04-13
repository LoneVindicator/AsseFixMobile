package com.example.assetfix.mobile.dashboard.data

import com.example.assetfix.R
import com.example.assetfix.mobile.dashboard.model.DashboardCards

class Datasource() {

    fun loadDashboardCards(): List<DashboardCards> {
        return listOf<DashboardCards>(
            DashboardCards(R.string.open_work_orders,R.string.number, R.drawable.openworkorder),
            DashboardCards(R.string.open_work_orders,R.string.number, R.drawable.openworkorder),
            DashboardCards(R.string.open_work_orders,R.string.number, R.drawable.openworkorder),

            )
    }
}