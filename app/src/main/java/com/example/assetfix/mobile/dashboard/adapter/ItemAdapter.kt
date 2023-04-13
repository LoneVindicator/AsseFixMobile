package com.example.assetfix.mobile.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R
import com.example.assetfix.mobile.dashboard.DashboardFragment
import com.example.assetfix.mobile.dashboard.model.DashboardCards

class ItemAdapter(
    private val context: DashboardFragment,
    private val dataset: List<DashboardCards>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val cardTitle: TextView = view.findViewById(R.id.card_title)
        val cardNumber: TextView = view.findViewById(R.id.card_number)
        val cardBackground: ImageView = view.findViewById(R.id.card_background)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.dashboard_cards, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.cardTitle.text = context.resources.getString(item.cardTitle)
        holder.cardNumber.text = context.resources.getString(item.cardNum)
        holder.cardBackground.setImageResource(item.imageResourceId)

        }

}