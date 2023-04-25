package com.example.assetfix.mobile.workOrder

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.assetfix.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkOrderFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_work_order, container, false)
        val selectAsset: AutoCompleteTextView = view.findViewById(R.id.select_asset)
        val selectProject: AutoCompleteTextView = view.findViewById(R.id.select_project)
        val selectWorkOrderStatus: AutoCompleteTextView = view.findViewById(R.id.work_order_status)
        val selectMaintenanceTypeStatus: AutoCompleteTextView = view.findViewById(R.id.maintenance_type)
        val selectPriority: AutoCompleteTextView = view.findViewById(R.id.priority)

        //Calendar Picker//
        val dueDateEditText: TextInputEditText = view.findViewById(R.id.due_date_edit_text)

        dueDateEditText.setOnClickListener {
            // Request focus for the edit text to ensure the dialog appears on the first click
            dueDateEditText.requestFocus()
            // Get the current date for the date picker dialog
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a date picker dialog and show it
            val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, day ->
                // Update the edit text with the selected date
                val selectedDate = "$year-${month + 1}-$day"
                dueDateEditText.setText(selectedDate)
            }, year, month, day)
            datePickerDialog.show()
        }
        //End of Calendar Picker//


        // First, defined the options for the dropdown menu m
        val assetOptions = listOf("May Tran", "Laptops", "Mobiles")
        val projectOptions = listOf("AssetFix", "Vigilant", "Workgate")
        val workOrderStatusOptions = listOf("Complete", "Draft", "In Progress","Open","Pending Approval","Rejected")
        val maintenanceTypeOptions = listOf("Inspection","Preventive", "Corrective", "Damage","Upgrade","Safety","Other")
        val selectPriorityOption = listOf("Low","Medium", "High")

        // Created an ArrayAdapter to populate the dropdown with the options
        val assetAdapter = ArrayAdapter(requireContext(), R.layout.asset_list, assetOptions)
        val projectAdapter = ArrayAdapter(requireContext(), R.layout.asset_list, projectOptions)
        val workOrderAdapter = ArrayAdapter(requireContext(), R.layout.asset_list, workOrderStatusOptions)
        val maintenanceTypeAdapter = ArrayAdapter(requireContext(), R.layout.asset_list, maintenanceTypeOptions)
        val selectPriorityAdapter = ArrayAdapter(requireContext(), R.layout.asset_list, selectPriorityOption)

        // Set the layout for the dropdown items
        assetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        projectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        workOrderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        maintenanceTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        selectPriorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Finally, attach the adapter to the dropdown view
        selectAsset.setAdapter(assetAdapter)
        selectProject.setAdapter(projectAdapter)
        selectWorkOrderStatus.setAdapter(workOrderAdapter)
        selectPriority.setAdapter(selectPriorityAdapter)
        selectMaintenanceTypeStatus.setAdapter(maintenanceTypeAdapter)

        // Set a listener to get the selected value
        selectAsset.setOnItemClickListener { parent, view, position, id ->
            val selectedValue = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "Selected option: $selectedValue", Toast.LENGTH_SHORT).show()
        }

        // Set a listener to get the selected value for Project
        selectProject.setOnItemClickListener { parent, view, position, id ->
            val selectedValue = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "Selected option: $selectedValue", Toast.LENGTH_SHORT).show()
        }

        // Set a listener to get the selected value for Work Order Status
        selectWorkOrderStatus.setOnItemClickListener { parent, view, position, id ->
            val selectedValue = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "Selected option: $selectedValue", Toast.LENGTH_SHORT).show()
        }

        // Set a listener to get the selected value for Work Order Status
        selectMaintenanceTypeStatus.setOnItemClickListener { parent, view, position, id ->
            val selectedValue = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "Selected option: $selectedValue", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WorkOrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WorkOrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
