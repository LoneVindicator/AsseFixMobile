package com.example.assetfix.mobile.workOrder.data

import com.example.assetfix.mobile.workOrder.model.WorkOrderCards

class Datasource() {

    fun loadWorkOrderCards(): List<WorkOrderCards> {
        return listOf<WorkOrderCards>(

            WorkOrderCards("743279", "In Progress", "Medium", "Corrective", "Backup Generator", "Juliette Magua", "12/07/19"),
            WorkOrderCards("125643", "Completed", "High", "Preventive", "HVAC System", "John Doe", "02/15/20"),
            WorkOrderCards("987654", "Pending", "Low", "Emergency", "Fire Suppression", "Alice Smith", "08/23/21"),
            WorkOrderCards("456789", "On Hold", "Low", "Inspection", "Elevator", "Bob Johnson", "04/05/22"),
            WorkOrderCards("789012", "Scheduled", "Low", "Corrective", "Plumbing", "Emily Rodriguez", "09/10/21"),
            WorkOrderCards("543210", "In Progress", "Medium", "Preventive", "Electrical Panel", "David Lee", "06/18/20"),
            WorkOrderCards("654321", "Completed", "High", "Emergency", "Roof Repair", "Sophia Chen", "03/29/22"),
            WorkOrderCards("135792", "Pending", "High", "Inspection", "Security System", "Matthew Turner", "11/12/19"),
            WorkOrderCards("246813", "On Hold", "Low", "Corrective", "Painting", "Olivia Nguyen", "07/14/20"),
            WorkOrderCards("112233", "Scheduled", "Medium","Preventive", "Grounds Maintenance", "Daniel Kim", "05/06/21"),
            WorkOrderCards("445566", "In Progress", "High", "Emergency", "IT Equipment", "Ava Wilson", "10/01/22")

            )
    }
}