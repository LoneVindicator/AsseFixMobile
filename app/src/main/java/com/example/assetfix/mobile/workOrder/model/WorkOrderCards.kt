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

data class MaintenanceData(
    val data: List<MaintenanceItem>
)

data class MaintenanceItem(
    val id: Int,
    val issue_summary: String,
    val assetable: Assetable,
    val project_id: String,
    val status_id: String,
    val maintenance_type_id: Int,
    val priority: String,
    val due_date: String,
    val estimated_labour_hours: Double,
    val requestor: Requestor,
    val workOrderAssignedTo: String,

    )

data class Assetable(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val country: String,
    val latitude: String,
    val longitude: String,
    val parent_location_id: String,
    val uuid: Int,
    val organization_id: Int,
    val is_active: Boolean,

    )

data class Requestor(
    val id: Int,
    val name: String,
    val email: String,
    val phone_number: String,
    val organizationId: Int
)

