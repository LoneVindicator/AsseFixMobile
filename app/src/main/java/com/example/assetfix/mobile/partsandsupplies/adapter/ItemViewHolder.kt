package com.example.assetfix.mobile.partsandsupplies.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // References to TextView elements in item_layout.xml

    val partName: TextView = itemView.findViewById(R.id.partsandsupplies_name_content)
    val partLocation: TextView = itemView.findViewById(R.id.partsandsupplies_location_content)
    val partAisle: TextView = itemView.findViewById(R.id.partsandsupplies_aisle_content)
    val partRow: TextView = itemView.findViewById(R.id.partsandsupplies_row_content)
    val partBin: TextView = itemView.findViewById(R.id.partsandsupplies_bin_content)
    val partMake: TextView = itemView.findViewById(R.id.partsandsupplies_make_content)
    val partModel: TextView = itemView.findViewById(R.id.partsandsupplies_model_content)
    val partBrand: TextView = itemView.findViewById(R.id.partsandsupplies_brand_content)
    val partBarcode: TextView = itemView.findViewById(R.id.partsandsupplies_bar_code_content)
    val partAdditionalParts: TextView = itemView.findViewById(R.id.partsandsupplies_additional_parts_content)
    val partCost: TextView = itemView.findViewById(R.id.partsandsupplies_cost_content)
    val partQuantity: TextView = itemView.findViewById(R.id.partsandsupplies_quantity_content)


}