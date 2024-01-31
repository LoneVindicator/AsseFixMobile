package com.example.assetfix.mobile.workOrder.model

import androidx.annotation.StringRes

data class WorkOrderCards(
    val workOrderNumber: String,
    val workOrderIssueSummary: String,
    val workOrderAsset: String,
    val workOrderProject: String,
    val workOrderStatus: String,
    val workOrderType: String,
    val workOrderPriority: String,
    val workOrderDueDate: String,
    val workOrderEstimatedType: String,
    val workOrderAssignedTo: String,
    val workOrderTasks: String,
    val workOrderFiles: String,
    )

