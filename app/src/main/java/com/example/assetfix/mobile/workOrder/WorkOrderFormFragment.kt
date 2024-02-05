package com.example.assetfix.mobile.workOrder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.assetfix.R
import com.google.android.material.button.MaterialButton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkOrderFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkOrderFormFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_work_order_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val submitBtn: MaterialButton? = view?.findViewById(R.id.create_work_order_submit_btn)

        val issueSummaryTextView = view.findViewById<TextView>(R.id.issue_summary_input)

        val assignedToSpinner: Spinner = view.findViewById(R.id.assigned_to_spinner)
        val assetLocationSpinner: Spinner = view.findViewById(R.id.asset_location_spinner)

        var selectedAssignedToOption = ""
        var selectedAssetLocationOption = ""

        // Set an item selected listener for the spinner
        assignedToSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Retrieve the selected item directly from the Spinner
                selectedAssignedToOption = assignedToSpinner.selectedItem.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle the case where no item is selected
                selectedAssignedToOption = ""
            }

        }

        assetLocationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Retrieve the selected item directly from the Spinner
                selectedAssetLocationOption = assetLocationSpinner.selectedItem.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle the case where no item is selected
                selectedAssetLocationOption = ""
            }

        }

        submitBtn?.setOnClickListener {

            var isFormValid: Boolean = validateForm(issueSummaryTextView.text.toString(), selectedAssetLocationOption, selectedAssignedToOption)

            if(isFormValid){

//                postWorkOrder()
                Toast.makeText(context, "Form validation successful", Toast.LENGTH_SHORT).show()


            }else{

                Toast.makeText(context, "Formation validation failed!", Toast.LENGTH_SHORT).show()


            }


        }


    }

    private fun postWorkOrder() {
        TODO("Not yet implemented")

    }

    private fun validateForm(issueSummary: String, assetLocation: String, assignedTo: String): Boolean {

        Log.d("workOrderForm", issueSummary)
        Log.d("workOrderForm", assetLocation)
        Log.d("workOrderForm", assignedTo)

        // Check if issueSummary is not empty
        if (issueSummary.isEmpty()) {
            // Display an error message or handle the empty issueSummary case
            Log.d("workOrderForm", "Issue summary is required.")
            Toast.makeText(context, "Issue Summary CANNOT be empty", Toast.LENGTH_SHORT).show()
            return false
        }

        // Check if assetLocation is not empty
        if (assetLocation.isEmpty() || assetLocation == "-- Select an asset or location --") {
            // Display an error message or handle the empty or default assetLocation case
            Log.d("workOrderForm", "Please select a valid asset location.")
            Toast.makeText(context, "Asset/Location MUST be selected", Toast.LENGTH_SHORT).show()
            return false
        }

        // Check if assignedTo is not empty
        if (assignedTo.isEmpty() || assignedTo == "-- Choose Assignees --") {
            // Display an error message or handle the empty or default assignedTo case
            Log.d("workOrderForm", "Please select a valid assignee.")
            Toast.makeText(context, "Assignee MUST be selected", Toast.LENGTH_SHORT).show()
            return false
        }

        // All validations passed, return true
        return true

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WorkOrderFormFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WorkOrderFormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}