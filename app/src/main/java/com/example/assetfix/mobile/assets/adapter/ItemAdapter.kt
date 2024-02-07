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

        val assetDescription: TextView = itemView.findViewById(R.id.asset_description_content)
        val assetPreferredVendor: TextView = itemView.findViewById(R.id.asset_preferred_vendor_content)
        val assetOtherVendors: TextView = itemView.findViewById(R.id.asset_other_vendors_content)
        val assetSubCategory: TextView = itemView.findViewById(R.id.asset_sub_category_content)
        val assetMake: TextView = itemView.findViewById(R.id.asset_make_content)
        val assetModel: TextView = itemView.findViewById(R.id.asset_model_content)
        val assetBrand: TextView = itemView.findViewById(R.id.asset_brand_content)
        val assetSerialNumber: TextView = itemView.findViewById(R.id.asset_serial_number_content)
        val assetPurchaseDate: TextView = itemView.findViewById(R.id.asset_purchase_date_content)
        val assetUsefuLife: TextView = itemView.findViewById(R.id.asset_useful_life_content)
        val assetPurchasePrice: TextView = itemView.findViewById(R.id.asset_purchase_price_content)
        val assetResidualValue: TextView = itemView.findViewById(R.id.asset_residual_value_content)
        val assetBarcode: TextView = itemView.findViewById(R.id.asset_barcode_content)
        val assetNotes: TextView = itemView.findViewById(R.id.asset_notes_content)
        val assetFiles: TextView = itemView.findViewById(R.id.asset_files_content)

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

        holder.assetDescription.text = item.assetDescription
        holder.assetPreferredVendor.text = item.assetPreferredVendor
        holder.assetOtherVendors.text = item.assetOtherVendors
        holder.assetSubCategory.text = item.assetSubCategory
        holder.assetModel.text = item.assetMake
        holder.assetModel.text = item.assetModel
        holder.assetBrand.text = item.assetBrand
        holder.assetSerialNumber.text = item.assetSerialNumber
        holder.assetPurchaseDate.text = item.assetPurchaseDate
        holder.assetUsefuLife.text = item.assetUsefulLife
        holder.assetPurchasePrice.text = item.assetPurchasePrice
        holder.assetResidualValue.text = item.assetResidualValue
        holder.assetBarcode.text = item.assetBarCode
        holder.assetNotes.text = item.assetNotes
        holder.assetFiles.text = item.assetFiles


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
        val toolbarTitle = "Assets"
        intent.putExtra("cardDetailsFragmentName", toolbarTitle)

        //Pass ancillary Work Order Details to CardDetailsActivity

        intent.putExtra("assetName", holder.assetName.text)
        intent.putExtra("assetDescription", holder.assetDescription.text)
        intent.putExtra("assetPreferredVendor", holder.assetPreferredVendor.text)
        intent.putExtra("assetOtherVendors", holder.assetOtherVendors.text)
        intent.putExtra("assetLocation", holder.assetLocation.text)
        intent.putExtra("assetDepartment", holder.assetDepartment.text)
        intent.putExtra("assetCategory", holder.assetCategory.text)
        intent.putExtra("assetSubCategory", holder.assetSubCategory.text)
        intent.putExtra("assetMake", holder.assetMake.text)
        intent.putExtra("assetModel", holder.assetModel.text)
        intent.putExtra("assetBrand", holder.assetBrand.text)
        intent.putExtra("assetSerialNumber", holder.assetSerialNumber.text)
        intent.putExtra("assetPurchaseDate", holder.assetPurchaseDate.text)
        intent.putExtra("assetUsefulLife", holder.assetUsefuLife.text)
        intent.putExtra("assetPurchasePrice", holder.assetPurchasePrice.text)
        intent.putExtra("assetResidualValue", holder.assetResidualValue.text)
        intent.putExtra("assetBarcode", holder.assetBarcode.text)
        intent.putExtra("assetNotes", holder.assetNotes.text)
        intent.putExtra("assetFiles", holder.assetFiles.text)

        context.startActivity(intent)

    }

    }

