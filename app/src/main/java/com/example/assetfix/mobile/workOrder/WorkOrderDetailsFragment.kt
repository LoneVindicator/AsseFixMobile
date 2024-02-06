package com.example.assetfix.mobile.workOrder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assetfix.R
import com.example.assetfix.mobile.workOrder.data.Datasource
import com.example.assetfix.mobile.workOrder.model.MaintenanceData
import com.example.assetfix.mobile.workOrder.model.MaintenanceItem
import com.example.assetfix.mobile.workOrder.model.mapMaintenanceDataToWorkOrderCards
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

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
    private val baseUrl = "https://test.assetfix.co/api/"

    private lateinit var datasource: Datasource
    private lateinit var issueSummaryView: TextView


    private val apiService: ApiService = Retrofit.Builder()
        .baseUrl(baseUrl) // Replace with your actual base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

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
        val workOrderNumber  = requireActivity().intent.getStringExtra("workOrderNumber")
        val workOrderIssueSummary  = requireActivity().intent.getStringExtra("workOrderIssueSummary")
        val workOrderAsset  = requireActivity().intent.getStringExtra("workOrderAsset")
        val workOrderProject  = requireActivity().intent.getStringExtra("workOrderProject")
        val workOrderStatus  = requireActivity().intent.getStringExtra("workOrderStatus")
        val workOrderEstimatedTime  = requireActivity().intent.getStringExtra("workOrderEstimatedTime")
        val workOrderType  = requireActivity().intent.getStringExtra("workOrderType")
        val workOrderPriority  = requireActivity().intent.getStringExtra("workOrderPriority")
        val workOrderDueDate  = requireActivity().intent.getStringExtra("workOrderDueDate")
        val workOrderAssignedTo  = requireActivity().intent.getStringExtra("workOrderAssignedTo")

        Log.d("WorkOrderDetailsFragment", workOrderIssueSummary?:"null")


        changeActivityTitle("Work Order #$workOrderNumber")

        issueSummaryTextView.text = workOrderIssueSummary ?: "--"
        assetLocationTextView.text = workOrderAsset ?: "--"
        projectTextView.text = workOrderProject ?: "--"
        workOrderStatusTextView.text = workOrderStatus ?: "--"
        maintenanceTypeTextView.text = workOrderType ?: "--"
        priorityTextView.text = workOrderPriority ?: "--"
        dueDateTextView.text = workOrderDueDate ?: "--"
        estimatedTimeTextView.text = workOrderEstimatedTime ?: "--"
        assignedToTextView.text = workOrderAssignedTo ?: "--"
//        tasksTextView.text = specificWorkOrder.workOrderTasks ?: ""
//        filesTextView.text = specificWorkOrder.workOrderFiles ?: ""



//        fetchData(workOrderNumberToRetrieve) { workOrderItem ->
//            // Use workOrderItem here
//            // This block will be executed when the data is available
//
//            if (workOrderItem != null) {
//                // Do something with the MaintenanceItem
//                // For example, map it to WorkOrderCards and use it
////                val workOrderCards = mapMaintenanceDataToWorkOrderCards(workOrderItem)
//                // Now you can use workOrderCards
//            } else {
//                // Handle the case where workOrderItem is null (error or no data)
//            }
//        }



    }

    private fun fetchData(workOrderNumber: String, callback: (MaintenanceItem?) -> Unit) {
        val accessToken = "30|028dowtjgcLF9WFHbZy84OtpsANgw8HF8UNptMli"

        val call = apiService.getData(workOrderNumber, "Bearer $accessToken")
        call.enqueue(object : Callback<MaintenanceItem> { // Ensure this line is using MaintenanceItem
            override fun onResponse(call: Call<MaintenanceItem>, response: Response<MaintenanceItem>) {


                if (response.isSuccessful) {
                    val maintenanceItem: MaintenanceItem? = response.body()
                    logData(maintenanceItem)
                    callback(maintenanceItem)
                } else {
                    handleErrorResponse(response)
                }
            }

            override fun onFailure(call: Call<MaintenanceItem>, t: Throwable) {
                Log.e("ApiCall", "API call failed", t)
            }
        })
    }

    interface ApiService {
        // Define the endpoint for fetching a single work order by work order number
        @GET("work-orders")
        fun getData(
            @Query("id") workOrderNumber: String,
            @Header("Authorization") token: String
        ): Call<MaintenanceItem> // Ensure this line is using MaintenanceItem
    }

    private fun logData(data: MaintenanceItem?) {
        if (data != null) {
            // Log the data here
            Log.d("ApiCall", data.toString())

//            val workOrderCardsList = mapMaintenanceDataToWorkOrderCards(data)

//            Log.d("ApiCall", workOrderCardsList.toString())
        } else {
            Log.w("ApiCall", "Data is null")
        }
    }

    private fun handleErrorResponse(response: Response<MaintenanceItem>) {
        // Log the error details
        Log.e("ApiCall", "Error: ${response.code()}, ${response.message()}")
        // You can also log the error body if needed: Log.e("ApiCall", "Error Body: ${response.errorBody()?.string()}")
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