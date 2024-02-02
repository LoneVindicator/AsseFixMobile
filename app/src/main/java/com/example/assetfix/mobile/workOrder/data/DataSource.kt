package com.example.assetfix.mobile.workOrder.data

import com.example.assetfix.mobile.workOrder.model.WorkOrderCards

class Datasource {

    private val workOrderCardsList: List<WorkOrderCards> = listOf(
        WorkOrderCards("743279", "Work complete but had a slight delay of 2 weeks", "Identigate HQ", "Kilimani Hills Build", "In Progress", "Corrective", "Low", "19/03/24", "43hrs 11min", "contractor@gmail.com", "--", "No files uploaded"),
        WorkOrderCards("123456", "Scheduled maintenance for HVAC system", "TechCorp Solutions", "TechCorp Office", "Completed", "Preventive", "Medium", "19/05/12", "22hrs 30min", "maintenance@example.com", "--", "Report.pdf"),
        WorkOrderCards("789012", "Installation of new network infrastructure", "NetTech Solutions", "DataCenter Project", "Pending", "Installation", "High", "20/01/08", "60hrs 45min", "network@example.com", "192.168.1.1", "NetworkDiagram.png"),
        WorkOrderCards("456789", "Emergency repair of water leakage", "Plumbing Masters", "Apartment Complex", "Completed", "Corrective", "High", "20/07/19", "10hrs 15min", "plumbing@example.com", "--", "Photos.zip"),
        WorkOrderCards("234567", "Inspection and testing of fire alarm system", "SafetyTech Services", "Commercial Building", "Scheduled", "Preventive", "Medium", "20/11/30", "8hrs 0min", "safetytech@example.com", "--", "No files uploaded"),
        WorkOrderCards("890123", "Upgrade servers and improve system performance", "TechGenius Solutions", "IT Department", "In Progress", "Upgrade", "High", "21/03/05", "34hrs 20min", "techgenius@example.com", "--", "ServerLogs.log"),
        WorkOrderCards("345678", "Landscaping and garden maintenance", "GreenScapes Landscaping", "Residential Area", "Scheduled", "Preventive", "Low", "21/08/15", "15hrs 30min", "greenscapes@example.com", "--", "GardenPlan.pdf"),
        WorkOrderCards("567890", "Repair and painting of exterior walls", "PaintPros Contractors", "Community Center", "Pending", "Corrective", "Medium", "22/02/10", "25hrs 55min", "paintpros@example.com", "--", "ColorPalette.jpg"),
        WorkOrderCards("901234", "Installation of security cameras", "SecureTech Solutions", "Retail Store", "Completed", "Installation", "High", "22/07/02", "18hrs 45min", "securitytech@example.com", "192.168.1.10", "SecurityLayout.pdf"),
        WorkOrderCards("678901", "Roof repair and maintenance", "RoofMaster Services", "Industrial Facility", "Pending", "Corrective", "Medium", "22/12/18", "12hrs 10min", "roofmaster@example.com", "--", "No files uploaded")
    )

    fun loadWorkOrderCards(): List<WorkOrderCards> {
        return workOrderCardsList
    }

    // Retrieve a specific WorkOrderCards by work order number
    fun getWorkOrderCardByNumber(workOrderNumber: String): WorkOrderCards? {
        return workOrderCardsList.find { it.workOrderNumber == workOrderNumber }
    }
}
