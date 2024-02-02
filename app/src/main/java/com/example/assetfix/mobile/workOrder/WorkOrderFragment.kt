package com.example.assetfix.mobile.workOrder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R
import com.example.assetfix.mobile.workOrder.adapter.ItemAdapter
import com.example.assetfix.mobile.workOrder.data.Datasource
import com.example.assetfix.mobile.workOrder.model.MaintenanceData
import com.example.assetfix.mobile.workOrder.model.WorkOrderCards
import com.example.assetfix.mobile.workOrder.model.mapMaintenanceDataToWorkOrderCards
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header


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

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter // replace MyAdapter with your actual adapter class

    private val baseUrl = "https://test.assetfix.co/api/"
    private lateinit var apiService: ApiService


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

        // Find the button by ID
        val openNewActivityButton: Button? = view?.findViewById(R.id.empty_work_order_new_work_order_button)
        val createWorOrderBtn: ImageView? = view?.findViewById(R.id.empty_work_order_new_work_order_icon)

        openNewActivityButton?.setOnClickListener {

            openNewActivity()
        }

        createWorOrderBtn?.setOnClickListener {

            changeActivityTitle("Create Work Order")
            openNewActivity()

        }


        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(com.example.assetfix.R.id.card_recyclerView)

        // Initialize your data list (replace with your actual data)
        val itemList = Datasource().loadWorkOrderCards()

        // Create an instance of your adapter
        adapter = ItemAdapter(this, itemList)

        // Set the adapter to the RecyclerView
        recyclerView.adapter = adapter

        // Set the layout manager (e.g., LinearLayoutManager)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val emptyLayout: LinearLayout = view.findViewById(com.example.assetfix.R.id.empty_work_order_layout)

        // Check if the RecyclerView data is empty or null

        // Check if the RecyclerView data is empty or null
                if (recyclerView.adapter == null || recyclerView.adapter!!.itemCount == 0) {
                    // If empty, make the LinearLayout visible
                    emptyLayout.visibility = View.VISIBLE
                } else {
                    // If not empty, make the LinearLayout gone
                    emptyLayout.visibility = View.GONE
                }

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        fetchData()
    }

    private fun changeActivityTitle(newTitle: String) {

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.title = newTitle

    }

    // Method to open the new activity
    private fun openNewActivity() {
        // Create an Intent to start the new activity
        val intent = Intent(activity, com.example.assetfix.mobile.carddetails.CardDetailsActivity::class.java)


        val toolbar: androidx.appcompat.widget.Toolbar? = activity?.findViewById(R.id.toolbar)
        val toolbarTitle = toolbar?.title?.toString()
        intent.putExtra("cardDetailsFragmentName", toolbarTitle)
        startActivity(intent)

    }
    interface ApiService {
        @GET("work-orders")
        fun getData(@Header("Authorization") token: String): Call<MaintenanceData>
    }

    private fun fetchData() {
        val accessToken = "30|028dowtjgcLF9WFHbZy84OtpsANgw8HF8UNptMli"

        val call = apiService.getData("Bearer $accessToken")
        call.enqueue(object : Callback<MaintenanceData> {
            override fun onResponse(call: Call<MaintenanceData>, response: Response<MaintenanceData>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    // Process the data as needed
                    logData(data)
                } else {
                    // Handle error
                    handleErrorResponse(response)
                }
            }

            override fun onFailure(call: Call<MaintenanceData>, t: Throwable) {
                // Handle failure
                Log.e("ApiCall", "API call failed", t)
            }
        })
    }

    private fun logData(data: MaintenanceData?) {
        if (data != null) {
            // Log the data here
            Log.d("ApiCall", data.toString())

            val workOrderCardsList = mapMaintenanceDataToWorkOrderCards(data)

            Log.d("ApiCall", workOrderCardsList.toString())
        } else {
            Log.w("ApiCall", "Data is null")
        }
    }

    private fun handleErrorResponse(response: Response<MaintenanceData>) {
        // Log the error details
        Log.e("ApiCall", "Error: ${response.code()}, ${response.message()}")
        // You can also log the error body if needed: Log.e("ApiCall", "Error Body: ${response.errorBody()?.string()}")
    }

//    data class MaintenanceData(
//        val data: List<MaintenanceItem>
//    )
//
//    data class MaintenanceItem(
//        val id: Int,
//        val issue_summary: String,
//        val assetable: Assetable,
//        val project_id: String,
//        val status_id: String,
//        val maintenance_type_id: Int,
//        val priority: String,
//        val due_date: String,
//        val estimated_labour_hours: Double,
//        val requestor: Requestor,
//        val workOrderAssignedTo: String,
//
//    )
//
//    data class Assetable(
//        val id: Int,
//        val name: String,
//        val address: String,
//        val city: String,
//        val country: String,
//        val latitude: String,
//        val longitude: String,
//        val parent_location_id: String,
//        val uuid: Int,
//        val organization_id: Int,
//        val is_active: Boolean,
//
//    )
//
//    data class Requestor(
//        val id: Int,
//        val name: String,
//        val email: String,
//        val phone_number: String,
//        val organizationId: Int
//    )


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