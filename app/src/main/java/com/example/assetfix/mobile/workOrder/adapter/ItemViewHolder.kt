package com.example.assetfix.mobile.workOrder.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // References to TextView elements in item_layout.xml

    val workOrderNumber: TextView = itemView.findViewById(R.id.work_order_number_content)
    val workOrderIssueSummary: TextView = itemView.findViewById(R.id.work_order_issue_summary_content)
    val workOrderStatus: TextView = itemView.findViewById(R.id.work_order_status_content)
    val workOrderPriority: TextView = itemView.findViewById(R.id.work_order_priority_content)
    val workOrderType: TextView = itemView.findViewById(R.id.work_order_type_content)
    val workOrderAsset: TextView = itemView.findViewById(R.id.work_order_asset_content)
    val workOrderAssignedTo: TextView = itemView.findViewById(R.id.work_order_assigned_to_content)
    val workOrderDueDate: TextView = itemView.findViewById(R.id.work_order_due_date_content)



}