package com.example.assetfix.mobile.assets.model

import android.util.Log
import androidx.annotation.StringRes
import com.example.assetfix.mobile.workOrder.model.*

data class AssetCards(
    val assetName: String,
    val assetDescription: String,
    val assetPreferredVendor: String,
    val assetOtherVendors: String,
    val assetLocation: String,
    val assetDepartment: String,
    val assetCategory: String,
    val assetSubCategory: String,
    val assetMake: String,
    val assetModel: String,
    val assetSerialNumber: String,
    val assetPurchaseDate: String,
    val assetUsefulLife: String,
    val assetPurchasePrice: String,
    val assetResidualValue: String,
    val assetBarCode: String,
    val assetNotes: String,
    val assetFiles: String,

    )

data class AssetList(
    val data: List<AssetItem>
)

data class AssetItem(
    val name: String?,
    val description: String?,
    val preferred_vendor: String?,
    val other_vendors: String?,
    val location: String?,
    val department: String?,
    val category: String?,
    val sub_category: String?,
    val make: String?,
    val model: String?,
    val brand: String?,
    val serial_number: String?,
    val purchase_date: String?,
    val useful_life: String?,
    val purchase_price: String?,
    val residual_value: String?,
    val barcode: String?,
    val notes: String?,
    val files: String?
)

fun mapAssetListToAssetCards(assetList: AssetList): List<AssetCards> {

    return assetList.data.map {
        AssetCards(
            assetName = it.name?: "",
            assetDescription = it.description?: "Poop",
            assetPreferredVendor = it.preferred_vendor?: "",
            assetOtherVendors = it.other_vendors?: "",
            assetLocation = it.location?: "",
            assetDepartment = it.department?: "",
            assetCategory = it.category?: "",
            assetSubCategory = it.sub_category?: "",
            assetMake = it.make?: "",
            assetModel = it.model?: "",
            assetSerialNumber = it.serial_number?: "",
            assetPurchaseDate = it.purchase_date?: "",
            assetUsefulLife = it.useful_life?: "",
            assetPurchasePrice = it.purchase_price?: "",
            assetResidualValue = it.residual_value?: "",
            assetBarCode = it.barcode?: "",
            assetNotes = it.notes?: "",
            assetFiles = it.files?: "",

        )

    }

}

