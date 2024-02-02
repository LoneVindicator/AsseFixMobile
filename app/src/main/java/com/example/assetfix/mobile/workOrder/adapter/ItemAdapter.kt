package com.example.assetfix.mobile.workOrder.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R
import com.example.assetfix.mobile.assets.AssetsFragment
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

        holder.itemView.setOnClickListener {
            // Open a new activity when the item is clicked


            openNewActivity(holder)
        }

        }

    private fun openNewActivity(holder: ItemViewHolder) {
        // Create an Intent to start the new activity
        val context = holder.itemView.context
        val intent = Intent(context, com.example.assetfix.mobile.carddetails.CardDetailsActivity::class.java)


        val toolbar: androidx.appcompat.widget.Toolbar? = (holder.itemView.findViewById(R.id.toolbar) as? androidx.appcompat.widget.Toolbar)
        val toolbarTitle = toolbar?.title?.toString()
        intent.putExtra("cardDetailsFragmentName", toolbarTitle)
        intent.putExtra("workOrderNumber", holder.workOrderNumber.text)
        context.startActivity(intent)

    }



    private fun getPriorityDrawable(priority: String): Int {
        return when (priority.toUpperCase()) {
            "HIGH" -> R.drawable.work_order_card_edge_red
            "MEDIUM" -> R.drawable.work_order_card_edge_orange
            "LOW" -> R.drawable.work_order_card_edge_green
            else -> R.drawable.work_order_card_edge_grey
        }
    }



    }

