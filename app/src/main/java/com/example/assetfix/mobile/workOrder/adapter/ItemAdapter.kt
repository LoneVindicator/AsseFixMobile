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

        // Change the background color of the priority marker based on workOrderPriority
        setPriorityMarkerColor(R.id.work_order_priority_marker_content, item.workOrderPriority)


        }

    private fun setPriorityMarkerColor(linearLayoutId: Int, priorityValue: String) {
        val linearLayout = context.view?.findViewById<LinearLayout>(linearLayoutId)

        // Log priority value
        Log.d("PriorityLog", "Priority value: $priorityValue")

        when (priorityValue.toLowerCase()) {
            "low" -> {
                linearLayout?.setBackgroundResource(R.drawable.work_order_card_edge_green)
                // Log the selected option
                Log.d("PriorityLog", "Selected option: ${priorityValue.toLowerCase()}")
            }
            "medium" -> {
                linearLayout?.setBackgroundResource(R.drawable.work_order_card_edge_orange)
                // Log the selected option
                Log.d("PriorityLog", "Selected option: ${priorityValue.toLowerCase()}")
            }
            "high" -> {
                linearLayout?.setBackgroundResource(R.drawable.work_order_card_edge_red)
                // Log the selected option
                Log.d("PriorityLog", "Selected option: ${priorityValue.toLowerCase()}")
            }
            else -> {
                linearLayout?.setBackgroundResource(R.drawable.work_order_card_edge_grey)
                // Log the selected option
                Log.d("PriorityLog", "Selected option: ${priorityValue.toLowerCase()}")
            }


        }

        // Ensure the view is redrawn
        linearLayout?.post {
            linearLayout.invalidate()
        }
    }


}