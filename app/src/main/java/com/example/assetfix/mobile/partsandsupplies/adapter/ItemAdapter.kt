package com.example.assetfix.mobile.partsandsupplies.adapter

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
import com.example.assetfix.mobile.partsandsupplies.PartsAndSuppliesFragment
import com.example.assetfix.mobile.partsandsupplies.model.PartCards
import com.example.assetfix.mobile.workOrder.WorkOrderFragment
import com.example.assetfix.mobile.workOrder.model.WorkOrderCards

class ItemAdapter(
    private val context: PartsAndSuppliesFragment,
    private val dataset: List<PartCards>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val partName: TextView = itemView.findViewById(R.id.partsandsupplies_name_content)
        val partLocation: TextView = itemView.findViewById(R.id.partsandsupplies_location_content)
        val partQuantity: TextView = itemView.findViewById(R.id.partsandsupplies_quantity_content)

        val partAisle: TextView = itemView.findViewById(R.id.partsandsupplies_aisle_content)
        val partRow: TextView = itemView.findViewById(R.id.partsandsupplies_row_content)
        val partBin: TextView = itemView.findViewById(R.id.partsandsupplies_bin_content)
        val partMake: TextView = itemView.findViewById(R.id.partsandsupplies_make_content)
        val partModel: TextView = itemView.findViewById(R.id.partsandsupplies_model_content)
        val partBrand: TextView = itemView.findViewById(R.id.partsandsupplies_brand_content)
        val partBarcode: TextView = itemView.findViewById(R.id.partsandsupplies_bar_code_content)
        val partAdditionalParts: TextView = itemView.findViewById(R.id.partsandsupplies_additional_parts_content)
        val partCost: TextView = itemView.findViewById(R.id.partsandsupplies_cost_content)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.parts_and_supplies_card, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.partName.text = item.partName
        holder.partLocation.text = item.partLocation
        holder.partQuantity.text = item.partQuantity

        holder.partAisle.text = item.partAisle
        holder.partRow.text = item.partRow
        holder.partBin.text = item.partBin
        holder.partMake.text = item.partMake
        holder.partModel.text = item.partModel
        holder.partBrand.text = item.partBrand
        holder.partBarcode.text = item.partBarcode
        holder.partAdditionalParts.text = item.partAdditionalParts
        holder.partCost.text = item.partCost
        holder.partQuantity.text = item.partQuantity

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
        val toolbarTitle = "Parts & Supplies"
        intent.putExtra("cardDetailsFragmentName", toolbarTitle)

        //Pass ancillary Work Order Details to CardDetailsActivity

        intent.putExtra("partName", holder.partName.text)
        intent.putExtra("partLocation", holder.partLocation.text)
        intent.putExtra("partQuantity", holder.partQuantity.text)
        intent.putExtra("partAisle", holder.partAisle.text)
        intent.putExtra("partRow", holder.partRow.text)
        intent.putExtra("partBin", holder.partBin.text)
        intent.putExtra("partMake", holder.partMake.text)
        intent.putExtra("partModel", holder.partModel.text)
        intent.putExtra("partBrand", holder.partBrand.text)
        intent.putExtra("partBarcode", holder.partBarcode.text)
        intent.putExtra("partAdditionalParts", holder.partAdditionalParts.text)
        intent.putExtra("partCost", holder.partCost.text)

        context.startActivity(intent)

    }

    }

