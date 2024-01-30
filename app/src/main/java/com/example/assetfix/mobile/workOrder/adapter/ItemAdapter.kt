package com.example.assetfix.mobile.workOrder.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R
import com.example.assetfix.mobile.workOrder.WorkOrderFragment
import com.example.assetfix.mobile.workOrder.model.WorkOrderCards

class ItemAdapter(
    private val context: WorkOrderFragment,
    private val dataset: List<WorkOrderCards>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val workOrderNumber: TextView = itemView.findViewById(R.id.work_order_number_content)
        val workOrderStatus: TextView = itemView.findViewById(R.id.work_order_status_content)
        val workOrderPriority: TextView = itemView.findViewById(R.id.work_order_priority_content)
        val workOrderType: TextView = itemView.findViewById(R.id.work_order_type_content)
        val workOrderAsset: TextView = itemView.findViewById(R.id.work_order_asset_content)
        val workOrderAssignedTo: TextView = itemView.findViewById(R.id.work_order_assigned_to_content)
        val workOrderDueDate: TextView = itemView.findViewById(R.id.work_order_due_date_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.work_order_card, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.workOrderNumber.text = item.workOrderNumber
        holder.workOrderStatus.text = item.workOrderStatus
        holder.workOrderPriority.text = item.workOrderPriority
        holder.workOrderType.text = item.workOrderType
        holder.workOrderAsset.text = item.workOrderAsset
        holder.workOrderAssignedTo.text = item.workOrderAssignedTo
        holder.workOrderDueDate.text = item.workOrderDueDate

        // Set the background drawable based on workOrderPriority
        val priorityMarker = holder.itemView.findViewById<LinearLayout>(R.id.work_order_priority_marker_content)
        val priorityDrawable = getPriorityDrawable(item.workOrderPriority)
        priorityMarker.setBackgroundResource(priorityDrawable)

        }

    private fun getPriorityDrawable(priority: String): Int {
        return when (priority) {
            "High" -> R.drawable.work_order_card_edge_red
            "Medium" -> R.drawable.work_order_card_edge_orange
            "Low" -> R.drawable.work_order_card_edge_green
            else -> R.drawable.work_order_card_edge_grey
        }
    }



    }

