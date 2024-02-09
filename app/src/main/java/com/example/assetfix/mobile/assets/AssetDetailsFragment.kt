package com.example.assetfix.mobile.assets

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assetfix.R
import com.example.assetfix.mobile.carddetails.CardDetailsActivity
import com.example.assetfix.mobile.workOrder.data.Datasource

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AssetDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AssetDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_asset_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TextView Declarations

        val assetNameTextView: TextView = view.findViewById(R.id.asset_details_name)
        val assetCategoryTextView: TextView = view.findViewById(R.id.asset_details_category)
        val assetLocationTextView: TextView = view.findViewById(R.id.asset_details_location)
        val assetDepartmentTextView: TextView = view.findViewById(R.id.asset_details_department)

        val assetDescriptionTextView: TextView = view.findViewById(R.id.asset_details_department)
        val assetPreferredVendorTextView: TextView = view.findViewById(R.id.asset_details_preferred_vendor)
        val assetOtherVendorsTextView: TextView = view.findViewById(R.id.asset_details_other_vendors)
        val assetSubCategoryTextView: TextView = view.findViewById(R.id.asset_details_sub_category)
        val assetMakeTextView: TextView = view.findViewById(R.id.asset_details_make)
        val assetModelTextView: TextView = view.findViewById(R.id.asset_details_model)
        val assetBrandTextView: TextView = view.findViewById(R.id.asset_details_brand)
        val assetSerialNumberTextView: TextView = view.findViewById(R.id.asset_details_serial_number)
        val assetPurchaseDateTextView: TextView = view.findViewById(R.id.asset_details_purchase_date)
        val assetUsefulLifeTextView: TextView = view.findViewById(R.id.asset_details_useful_life)
        val assetPurchasePriceTextView: TextView = view.findViewById(R.id.asset_details_purchase_price)
        val assetResidualValueTextView: TextView = view.findViewById(R.id.asset_details_residual_value)
        val assetBarcodeTextView: TextView = view.findViewById(R.id.asset_details_barcode)
        val assetNotesTextView: TextView = view.findViewById(R.id.asset_details_notes)
        val assetFilesTextView: TextView = view.findViewById(R.id.asset_details_files)



        // Example work order number

        val assetName: String? = requireActivity().intent.getStringExtra("assetName")
        val assetCategory: String? = requireActivity().intent.getStringExtra("assetCategory")
        val assetLocation: String? = requireActivity().intent.getStringExtra("assetLocation")
        val assetDepartment: String? = requireActivity().intent.getStringExtra("assetDepartment")

        val assetDescription: String? = requireActivity().intent.getStringExtra("assetDescription")
        val assetPreferredVendor: String? = requireActivity().intent.getStringExtra("assetPreferredVendor")
        val assetOtherVendors: String? = requireActivity().intent.getStringExtra("assetOtherVendors")
        val assetSubCategory: String? = requireActivity().intent.getStringExtra("assetSubCategory")
        val assetMake: String? = requireActivity().intent.getStringExtra("assetMake")
        val assetModel: String? = requireActivity().intent.getStringExtra("assetModel")
        val assetBrand: String? = requireActivity().intent.getStringExtra("assetBrand")
        val assetSerialNumber: String? = requireActivity().intent.getStringExtra("assetSerialNumber")
        val assetPurchaseDate: String? = requireActivity().intent.getStringExtra("assetPurchaseDate")
        val assetUsefulLife: String? = requireActivity().intent.getStringExtra("assetUsefulLife")
        val assetPurchasePrice: String? = requireActivity().intent.getStringExtra("assetPurchasePrice")
        val assetResidualValue: String? = requireActivity().intent.getStringExtra("assetResidualValue")
        val assetBarcode: String? = requireActivity().intent.getStringExtra("assetBarcode")
        val assetNotes: String? = requireActivity().intent.getStringExtra("assetNotes")
        val assetFiles: String? = requireActivity().intent.getStringExtra("assetFiles")

        if (assetName != null) {
            changeActivityTitle(assetName)
        }

        assetNameTextView.text = assetName ?: "--"
        assetLocationTextView.text = assetLocation ?: "--"
        assetNameTextView.text = assetName ?: "--"
        assetCategoryTextView.text = assetCategory ?: "--"
        assetLocationTextView.text = assetLocation ?: "--"
        assetDepartmentTextView.text = assetDepartment ?: "--"

        assetDescriptionTextView.text = assetDescription ?: "--"
        assetPreferredVendorTextView.text = assetPreferredVendor ?: "--"
        assetOtherVendorsTextView.text = assetOtherVendors ?: "--"
        assetSubCategoryTextView.text = assetSubCategory ?: "--"
        assetMakeTextView.text = assetMake ?: "--"
        assetModelTextView.text = assetModel ?: "--"
        assetBrandTextView.text = assetBrand ?: "--"
        assetSerialNumberTextView.text = assetSerialNumber ?: "--"
        assetPurchaseDateTextView.text = assetPurchaseDate ?: "--"
        assetUsefulLifeTextView.text = assetUsefulLife ?: "--"
        assetPurchasePriceTextView.text = assetPurchasePrice ?: "--"
        assetResidualValueTextView.text = assetResidualValue ?: "--"
        assetBarcodeTextView.text = assetBarcode ?: "--"
        assetNotesTextView.text = assetNotes ?: "--"
        assetFilesTextView.text = assetFiles ?: "--"

    }

    private fun changeActivityTitle(newTitle: String) {

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.title = newTitle

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AssetDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AssetDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}