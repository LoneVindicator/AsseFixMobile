package com.example.assetfix.mobile.workOrder.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
    val id: String?,
    val issue_summary: String?,
    val assetable: Assetable,
    val project_id: String?,
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
    val parent_location_id: String?,
    val uuid: String?,
    val organization_id: String?,
    val is_active: Boolean,

    )

data class Requestor(
    val id: String?,
    val name: String,
    val email: String,
    val phone_number: String,
    val organizationId: Int
)

// Define a function to format the date
// Define a function to format the date
fun formatDate(inputDate: String?): String {
    if (inputDate.isNullOrEmpty()) {
        return ""
    }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dateTime = LocalDateTime.parse(inputDate, formatter)
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return dateTime.format(outputFormatter)
}

fun mapMaintenanceDataToWorkOrderCards(maintenanceData: MaintenanceData): List<WorkOrderCards> {
    val statusMapping = mapOf(
        14 to "Complete",
        12 to "Open",
        13 to "In Progress",
        11 to "Draft",
        15 to "Pending Approval",
        16 to "Rejected"
    )

    val maintenanceTypeMapping = mapOf(
        6 to "Corrective",
        5 to "Damage",
        9 to "Inspection",
        10 to "Other",
        4 to "Preventative",
        7 to "Safety",
        8 to "Upgrade"
    )

    return maintenanceData.data.map {
        WorkOrderCards(
            workOrderNumber = it.id.toString(),
            workOrderIssueSummary = it.issue_summary?: "",
            workOrderAsset = it.assetable?.name ?: "",
            workOrderProject = it.project_id.toString(),
            workOrderStatus = statusMapping[it.status_id] ?: it.status_id.toString(),
            workOrderType = maintenanceTypeMapping[it.maintenance_type_id] ?: it.maintenance_type_id.toString(),
            workOrderPriority = it.priority,
            workOrderDueDate = formatDate(it.due_date ?: ""),
            workOrderEstimatedType = it.estimated_labour_hours.toString(),
            workOrderAssignedTo = it.requestor?.name ?: "",
            workOrderTasks = "",  // You can set appropriate values for these fields
            workOrderFiles = ""   // based on your requirements
        )
    }
}


