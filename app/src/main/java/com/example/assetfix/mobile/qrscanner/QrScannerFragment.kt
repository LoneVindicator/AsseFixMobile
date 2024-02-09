package com.example.assetfix.mobile.qrscanner

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.assetfix.R
import com.example.assetfix.mobile.assets.model.AssetCards
import com.example.assetfix.mobile.assets.model.AssetList
import com.example.assetfix.mobile.assets.model.mapAssetListToAssetCards
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
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

private val baseUrl = "https://test.assetfix.co/api/"
private lateinit var apiService: QrScannerFragment.ApiService


/**
 * A simple [Fragment] subclass.
 * Use the [QrScannerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QrScannerFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_qr_scanner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestCameraPermission()
        } else {
            startQRScan()
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startQRScan()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Camera permission is required to scan QR codes.",
                    Toast.LENGTH_SHORT
                ).show()
                // Handle permission denied case
            }
        }
    }

    private fun startQRScan() {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        IntentIntegrator.forSupportFragment(this)
            .setOrientationLocked(true)
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            .setPrompt("Scan a QR code")
            .setBeepEnabled(false)
            .initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IntentIntegrator.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                val contents = result.contents
                // Handle the scanned QR code contents here
                Toast.makeText(requireContext(), "Scanned: $contents", Toast.LENGTH_LONG).show()
                Log.d("Scanned QR: ", contents)

                fetchData { assetList ->
                    // Use workOrderCardList here
                    // This block will be executed when the data is available

                    val filteredAsset = assetList.find { it.assetBarCode == contents }
                    openNewActivity(filteredAsset)

                    Log.d("QrScannerFragment", filteredAsset.toString())



                }
            }
        }

    }

    interface ApiService {
        @GET("assets")
        fun getData(@Header("Authorization") token: String): Call<AssetList>
    }

    private fun fetchData(callback: (List<AssetCards>) -> Unit) {
        val accessToken = "30|028dowtjgcLF9WFHbZy84OtpsANgw8HF8UNptMli"

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

    private fun openNewActivity(assetList: AssetCards?) {
        // Create an Intent to start the new activity
        val intent = Intent(context, com.example.assetfix.mobile.carddetails.CardDetailsActivity::class.java)


        val toolbar: androidx.appcompat.widget.Toolbar? = activity?.findViewById(R.id.toolbar)
        val toolbarTitle = "Assets"
        intent.putExtra("cardDetailsFragmentName", toolbarTitle)

        //Pass ancillary Work Order Details to CardDetailsActivity

        intent.putExtra("assetName", assetList?.assetName)
        intent.putExtra("assetDescription", assetList?.assetDescription)
        intent.putExtra("assetPreferredVendor", assetList?.assetPreferredVendor)
        intent.putExtra("assetOtherVendors", assetList?.assetOtherVendors)
        intent.putExtra("assetLocation", assetList?.assetLocation)
        intent.putExtra("assetDepartment", assetList?.assetDepartment)
        intent.putExtra("assetCategory", assetList?.assetCategory)
        intent.putExtra("assetSubCategory", assetList?.assetSubCategory)
        intent.putExtra("assetMake", assetList?.assetMake)
        intent.putExtra("assetModel", assetList?.assetModel)
        intent.putExtra("assetBrand", assetList?.assetBrand)
        intent.putExtra("assetSerialNumber", assetList?.assetSerialNumber)
        intent.putExtra("assetPurchaseDate", assetList?.assetPurchaseDate)
        intent.putExtra("assetUsefulLife", assetList?.assetUsefulLife)
        intent.putExtra("assetPurchasePrice", assetList?.assetPurchasePrice)
        intent.putExtra("assetResidualValue", assetList?.assetResidualValue)
        intent.putExtra("assetBarcode", assetList?.assetBarCode)
        intent.putExtra("assetNotes", assetList?.assetNotes)
        intent.putExtra("assetFiles", assetList?.assetFiles)

        context?.startActivity(intent)

    }

    companion object {

        private const val CAMERA_PERMISSION_REQUEST_CODE = 100

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QrScannerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QrScannerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}