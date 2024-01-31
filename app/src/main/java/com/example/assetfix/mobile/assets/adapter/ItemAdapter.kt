package com.example.assetfix.mobile.assets.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R
import com.example.assetfix.mobile.assets.AssetsFragment
import com.example.assetfix.mobile.assets.model.AssetCards
import com.example.assetfix.mobile.workOrder.WorkOrderFragment
import com.example.assetfix.mobile.workOrder.model.WorkOrderCards

class ItemAdapter(
    private val context: AssetsFragment,
    private val dataset: List<AssetCards>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val assetName: TextView = itemView.findViewById(R.id.asset_name_content)
        val assetCategory: TextView = itemView.findViewById(R.id.asset_category_content)
        val assetLocation: TextView = itemView.findViewById(R.id.asset_location_content)
        val assetDepartment: TextView = itemView.findViewById(R.id.asset_department_content)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.asset_card, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.assetName.text = item.assetName
        holder.assetCategory.text = item.assetCategory
        holder.assetLocation.text = item.assetLocation
        holder.assetDepartment.text = item.assetDepartment

        holder.itemView.setOnClickListener {
            // Open a new activity when the item is clicked


//            openNewActivity(holder)
        }

        }

//    private fun openNewActivity(holder: ItemViewHolder) {
//        // Create an Intent to start the new activity
//        val context = holder.itemView.context
//        val intent = Intent(context, com.example.assetfix.mobile.carddetails.CardDetailsActivity::class.java)
//
//
//        val toolbar: androidx.appcompat.widget.Toolbar? = (holder.itemView.findViewById(R.id.toolbar) as? androidx.appcompat.widget.Toolbar)
//        val toolbarTitle = toolbar?.title?.toString()
//        intent.putExtra("cardDetailsFragmentName", toolbarTitle)
//        intent.putExtra("workOrderNumber", holder.asset.text)
//        context.startActivity(intent)
//
//    }

    }

