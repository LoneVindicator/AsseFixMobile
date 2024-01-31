package com.example.assetfix.mobile.workOrder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.assetfix.R
import com.example.assetfix.mobile.workOrder.data.Datasource

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkOrderDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkOrderDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var datasource: Datasource
    private lateinit var issueSummaryView: TextView

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
        return inflater.inflate(R.layout.fragment_work_order_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        datasource = Datasource()


        //TextView Declarations

        val issueSummaryTextView = view.findViewById<TextView>(R.id.work_order_details_issue_summary)
        val assetLocationTextView = view.findViewById<TextView>(R.id.work_order_details_asset_location_summary)
        val projectTextView = view.findViewById<TextView>(R.id.work_order_details_project)
        val workOrderStatusTextView = view.findViewById<TextView>(R.id.work_order_details_work_order_status)
        val maintenanceTypeTextView = view.findViewById<TextView>(R.id.work_order_details_maintenance_type)
        val priorityTextView = view.findViewById<TextView>(R.id.work_order_details_priority)
        val dueDateTextView = view.findViewById<TextView>(R.id.work_order_details_due_date)
        val estimatedTimeTextView = view.findViewById<TextView>(R.id.work_order_details_estimated_time)
        val assignedToTextView = view.findViewById<TextView>(R.id.work_order_details_assigned_to)
        val tasksTextView = view.findViewById<TextView>(R.id.work_order_details_tasks)
        val filesTextView = view.findViewById<TextView>(R.id.work_order_details_files)

        // Example work order number
        val workOrderNumberToRetrieve =  requireActivity().intent.getStringExtra("workOrderNumber")

        // Retrieve the specific WorkOrderCards
        val specificWorkOrder = datasource.getWorkOrderCardByNumber(workOrderNumberToRetrieve!!)

        // Update the TextView with the retrieved details
        if (specificWorkOrder != null) {

            issueSummaryTextView.text = specificWorkOrder.workOrderIssueSummary ?: ""
            assetLocationTextView.text = specificWorkOrder.workOrderAsset ?: ""
            projectTextView.text = specificWorkOrder.workOrderProject ?: ""
            workOrderStatusTextView.text = specificWorkOrder.workOrderStatus ?: ""
            maintenanceTypeTextView.text = specificWorkOrder.workOrderType ?: ""
            priorityTextView.text = specificWorkOrder.workOrderPriority ?: ""
            dueDateTextView.text = specificWorkOrder.workOrderDueDate ?: ""
            estimatedTimeTextView.text = specificWorkOrder.workOrderEstimatedType ?: ""
            assignedToTextView.text = specificWorkOrder.workOrderAssignedTo ?: ""
            tasksTextView.text = specificWorkOrder.workOrderTasks ?: ""
            filesTextView.text = specificWorkOrder.workOrderFiles ?: ""

        }
        }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WorkOrderDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WorkOrderDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}