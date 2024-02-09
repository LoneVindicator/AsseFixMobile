package com.example.assetfix.mobile.dashboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R
import com.example.assetfix.mobile.dashboard.adapter.ItemAdapter
import com.example.assetfix.mobile.dashboard.data.Datasource

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter // replace MyAdapter with your actual adapter class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.card_recyclerView)

        // Initialize your data list (replace with your actual data)
                val itemList = Datasource().loadWorkOrderCards()

        // Create a sublist containing only the first 5 items
                val firstFiveItems = itemList.subList(0, minOf(itemList.size, 5))

        // Create an instance of your adapter with the sublist
                adapter = ItemAdapter(this, firstFiveItems)

        // Set the adapter to the RecyclerView
                recyclerView.adapter = adapter

        // Set the layout manager (e.g., LinearLayoutManager)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())

        populateDashboard()

    }

    private fun populateDashboard() {

        val workOrderCountTextView: TextView = view!!.findViewById(R.id.active_work_orders_content)
        val activeAssetsCountTextView: TextView = view!!.findViewById(R.id.active_assets_content)
        val activeUsersCountTextView: TextView = view!!.findViewById(R.id.active_users_content)


        val workOrderCount = getOneSpecificData("workOrderCount", defaultValue = "0")
        val activeAssetsCount = getOneSpecificData("assetsCount", defaultValue = "0")
        val activeUsersCount = getOneSpecificData("activeUsersCount", defaultValue = "0")

        workOrderCountTextView.text = workOrderCount
        activeAssetsCountTextView.text = activeAssetsCount
        activeUsersCountTextView.text = activeUsersCount

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    // Function to retrieve data from SharedPreferences
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
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}