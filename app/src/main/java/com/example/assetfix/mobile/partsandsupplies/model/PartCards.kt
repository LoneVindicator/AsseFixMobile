package com.example.assetfix.mobile.partsandsupplies.model

import android.util.Log
import androidx.annotation.StringRes
import com.example.assetfix.mobile.workOrder.model.*

data class PartCards(
    val partName: String,
    val partLocation: String,
    val partAisle: String,
    val partRow: String,
    val partBin: String,
    val partMake: String,
    val partModel: String,
    val partBrand: String,
    val partBarcode: String,
    val partAdditionalParts: String,
    val partCost: String,
    val partQuantity: String,

    )

data class PartList(
    val data: List<PartItem>
)

data class PartItem(
    val name: String?,
    val location: String?,
    val aisle: String?,
    val row: String?,
    val bin: String?,
    val make: String?,
    val model: String?,
    val brand: String?,
    val barcode: String?,
    val additional_parts: String?,
    val cost: String?,
    val quantity: String?,

)

fun mapPartListToPartCards(partList: PartList): List<PartCards> {

    return partList.data.map {
        PartCards(
            partName = it.name?: "",
            partLocation = it.location?: "",
            partAisle = it.aisle?: "",
            partRow = it.row?: "",
            partBin = it.bin?: "",
            partMake = it.make?: "",
            partModel = it.model?: "",
            partBrand = it.brand?: "",
            partBarcode = it.barcode?: "",
            partAdditionalParts = it.additional_parts?: "",
            partCost = it.cost?: "",
            partQuantity = it.quantity?: "",

        )

    }

}

