package com.example.assetfix.mobile.peopleandteams.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // References to TextView elements in item_layout.xml

    val peopleAndTeamsName: TextView = itemView.findViewById(R.id.peopleandteams_name_content)
    val peopleAndTeamsEmail: TextView = itemView.findViewById(R.id.peopleandteams_email_content)
    val peopleAndTeamsPhoneNumber: TextView = itemView.findViewById(R.id.peopleandteams_phone_number_content)
    val peopleAndTeamsRole: TextView = itemView.findViewById(R.id.peopleandteams_role_content)



}