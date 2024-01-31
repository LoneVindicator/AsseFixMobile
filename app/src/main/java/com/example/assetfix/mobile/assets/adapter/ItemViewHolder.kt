package com.example.assetfix.mobile.assets.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // References to TextView elements in item_layout.xml

    val assetName: TextView = itemView.findViewById(R.id.asset_name_content)
    val assetCategory: TextView = itemView.findViewById(R.id.asset_category_content)
    val assetLocation: TextView = itemView.findViewById(R.id.asset_location_content)
    val assetDepartment: TextView = itemView.findViewById(R.id.asset_department_content)



}