package com.example.assetfix.mobile.workOrder.model

data class WorkOrderCards(
    val workOrderNumber: String,
    val workOrderIssueSummary: String?,
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
    val issue_summary: String?,
    val assetable: Assetable,
    val project_id: Int,
    val status_id: Int,
    val maintenance_type_id: Int,
    val priority: String,
    val due_date: String,
    val estimated_labour_hours: Double,
    val requestor: Requestor,
    val workOrderAssignedTo: String,
) {

}

data class Assetable(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val country: String,
    val latitude: String,
    val longitude: String,
    val parent_location_id: Int,
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

fun mapMaintenanceDataToWorkOrderCards(maintenanceData: MaintenanceData): List<WorkOrderCards> {
    return maintenanceData.data.map {
        WorkOrderCards(
            workOrderNumber = it.id.toString(),
            workOrderIssueSummary = it.issue_summary?.split("\n").toString()?: "",
            workOrderAsset = it.assetable?.name ?: "",
            workOrderProject = it.project_id.toString(),
            workOrderStatus = it.status_id.toString(),
            workOrderType = it.maintenance_type_id.toString(),
            workOrderPriority = it.priority,
            workOrderDueDate = it.due_date ?: "",
            workOrderEstimatedType = it.estimated_labour_hours.toString(),
            workOrderAssignedTo = it.requestor?.name ?: "",
            workOrderTasks = "",  // You can set appropriate values for these fields
            workOrderFiles = ""   // based on your requirements
        )
    }
}

