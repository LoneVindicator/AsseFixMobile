package com.example.assetfix.mobile.assets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R
import com.example.assetfix.mobile.assets.adapter.ItemAdapter
import com.example.assetfix.mobile.assets.data.Datasource
import com.example.assetfix.mobile.assets.model.AssetCards
import com.example.assetfix.mobile.assets.model.AssetList
import com.example.assetfix.mobile.assets.model.mapAssetListToAssetCards
import com.example.assetfix.mobile.workOrder.WorkOrderFragment
import com.example.assetfix.mobile.workOrder.model.MaintenanceData
import com.example.assetfix.mobile.workOrder.model.WorkOrderCards
import com.example.assetfix.mobile.workOrder.model.mapMaintenanceDataToWorkOrderCards
import com.google.gson.Gson
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
class AssetsFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_assets, container, false)

        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the button by ID
        val openNewActivityButton: ImageView? = view?.findViewById(R.id.empty_assets_icon)

        // Use safe call operator ?. to avoid null pointer exception
        openNewActivityButton?.setOnClickListener {
            // Handle button click, open new activity

            openNewActivity()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        showLoadingIndicator()

        fetchData { assetList ->
            // Use workOrderCardList here
            // This block will be executed when the data is available
            initializeRecyclerView(assetList)


        }


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

    private fun initializeRecyclerView(assetList: List<AssetCards>) {

        // Check if view is null
        if (view == null) {
            // Handle the case where view is null, maybe log an error or return early
            return
        }

        // Find the RecyclerView by its ID
        recyclerView = view!!.findViewById(com.example.assetfix.R.id.card_recyclerView)

        // Check if recyclerView is null
        if (recyclerView == null) {
            // Handle the case where recyclerView is null, maybe log an error or return early
            return
        }


        // Initialize your data list (replace with your actual data)
        val itemList = assetList

        // Create an instance of your adapter
        adapter = ItemAdapter(this, itemList)

        // Set the adapter to the RecyclerView
        recyclerView.adapter = adapter

        // Set the layout manager (e.g., LinearLayoutManager)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val emptyLayout: LinearLayout = view!!.findViewById(R.id.empty_assets_layout)

        // Check if the RecyclerView data is empty or null
        if (recyclerView.adapter == null || recyclerView.adapter!!.itemCount == 0) {
            // If empty, make the LinearLayout visible
            emptyLayout.visibility = View.VISIBLE
        } else {
            // If not empty, make the LinearLayout gone
            emptyLayout.visibility = View.GONE
            hideLoadingIndicator()
        }
    }

    interface ApiService {
        @GET("assets")
        fun getData(@Header("Authorization") token: String): Call<AssetList>
    }

    private fun fetchData(callback: (List<AssetCards>) -> Unit) {
        val accessToken: String = getOneSpecificData("accessToken", defaultValue = "0")

        val call = apiService.getData("Bearer $accessToken")
        call.enqueue(object : Callback<AssetList> {
            override fun onResponse(call: Call<AssetList>, response: Response<AssetList>) {
                if (response.isSuccessful) {
                    val data = response.body()

                    val rawJsonResponse = response.body()?.let { Gson().toJson(it) }
                    Log.d("RawResponse", rawJsonResponse ?: "Response body is null")


                    logData(data)
                    val assetCardsList = data?.let { mapAssetListToAssetCards(it) }
                    if (assetCardsList != null) {

                        val assetsCount = assetCardsList.size
                        saveData("assetsCount", assetsCount.toString())
                        callback(assetCardsList)
                    }
                } else {
                    handleErrorResponse(response)
                }
            }

            override fun onFailure(call: Call<AssetList>, t: Throwable) {
                Log.e("ApiCall", "API call failed", t)
            }
        })
    }


    private fun logData(data: AssetList?) {
        if (data != null) {
            // Log the data here
            Log.d("ApiCall", data.toString())

            val assetCardsList = mapAssetListToAssetCards(data)

            Log.d("ApiCall", assetCardsList.toString())
        } else {
            Log.w("ApiCall", "Data is null")
        }
    }

    private fun handleErrorResponse(response: Response<AssetList>) {
        // Log the error details
        Log.e("ApiCall", "Error: ${response.code()}, ${response.message()}")
        // You can also log the error body if needed: Log.e("ApiCall", "Error Body: ${response.errorBody()?.string()}")
    }

    private fun showLoadingIndicator() {
        // Find the ProgressBar by its ID
        val progressBar: LinearLayout = view!!.findViewById(R.id.loading_layout)
        // Show the ProgressBar
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoadingIndicator() {
        // Find the ProgressBar by its ID
        val progressBar: LinearLayout = view!!.findViewById(R.id.loading_layout)
        // Hide the ProgressBar
        progressBar.visibility = View.GONE
    }

    private fun saveData(key: String, value: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("dashboardContent", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun retrieveData(key: String, defaultValue: String): String {
        val sharedPreferences = requireActivity().getSharedPreferences("dashboardContent", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    // Example function to retrieve one specific data
    private fun getOneSpecificData(key: String, defaultValue: String): String {
        val specificData = retrieveData(key, defaultValue)

        return specificData
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
            AssetsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}