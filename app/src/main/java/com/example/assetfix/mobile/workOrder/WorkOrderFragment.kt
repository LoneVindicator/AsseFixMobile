package com.example.assetfix.mobile.workOrder

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.assetfix.R
import com.example.assetfix.mobile.carddetails.CardDetailsActivity

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

        // Find the button by ID
        val openNewActivityButton: Button? = view?.findViewById(R.id.empty_work_order_new_work_order_button)
        val openNewActivityIcon: ImageView? = view?.findViewById(R.id.empty_work_order_new_work_order_icon)

        openNewActivityButton?.setOnClickListener {

            openNewActivity()
        }

        openNewActivityIcon?.setOnClickListener {

            changeActivityTitle("Create Work Order")
            openNewActivity()

        }



        return view


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