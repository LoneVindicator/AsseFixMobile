package com.example.assetfix.mobile.workOrder.model

import androidx.annotation.StringRes

data class WorkOrderCards(
    val workOrderNumber: String,
    val workOrderStatus: String,
    val workOrderPriority: String,
    val workOrderType: String,
    val workOrderAsset: String,
    val workOrderAssignedTo: String,
    val workOrderDueDate: String,
    )