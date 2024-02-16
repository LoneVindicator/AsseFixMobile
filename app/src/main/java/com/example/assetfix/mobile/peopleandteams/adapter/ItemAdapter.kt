package com.example.assetfix.mobile.peopleandteams.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assetfix.R
import com.example.assetfix.mobile.peopleandteams.PeopleAndTeamsFragment
import com.example.assetfix.mobile.peopleandteams.model.PeopleCards

class ItemAdapter(
    private val context: PeopleAndTeamsFragment,
    private val dataset: List<PeopleCards>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {


        val peopleAndTeamsName: TextView = itemView.findViewById(R.id.peopleandteams_name_content)
        val peopleAndTeamsEmail: TextView = itemView.findViewById(R.id.peopleandteams_email_content)
        val peopleAndTeamsPhoneNumber: TextView = itemView.findViewById(R.id.peopleandteams_phone_number_content)
        val peopleAndTeamsRole: TextView = itemView.findViewById(R.id.peopleandteams_role_content)

        val peopleAndTeamsEmailVerifiedAt: TextView = itemView.findViewById(R.id.peopleandteams_email_verified_at_content)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.people_and_teams_card, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.peopleAndTeamsName.text = item.peopleAndTeamsName
        holder.peopleAndTeamsEmail.text = item.peopleAndTeamsEmail
        holder.peopleAndTeamsPhoneNumber.text = item.peopleAndTeamsPhoneNumber
        holder.peopleAndTeamsRole.text = item.peopleAndTeamsRole

        holder.peopleAndTeamsEmailVerifiedAt.text = item.peopleAndTeamsEmailVerifiedAt



        holder.itemView.setOnClickListener {
            // Open a new activity when the item is clicked


            openNewActivity(holder)
        }

        }

    private fun openNewActivity(holder: ItemViewHolder) {
        // Create an Intent to start the new activity
        val context = holder.itemView.context
        val intent = Intent(context, com.example.assetfix.mobile.carddetails.CardDetailsActivity::class.java)


        val toolbar: androidx.appcompat.widget.Toolbar? = (holder.itemView.findViewById(R.id.toolbar) as? androidx.appcompat.widget.Toolbar)
        val toolbarTitle = "People & Teams"
        intent.putExtra("cardDetailsFragmentName", toolbarTitle)

        //Pass ancillary Work Order Details to CardDetailsActivity

        intent.putExtra("peopleAndTeamsName", holder.peopleAndTeamsName.text)
        intent.putExtra("peopleAndTeamsEmail", holder.peopleAndTeamsEmail.text)
        intent.putExtra("peopleAndTeamsPhoneNumber", holder.peopleAndTeamsPhoneNumber.text)
        intent.putExtra("peopleAndTeamsRole", holder.peopleAndTeamsRole.text)
        intent.putExtra("peopleAndTeamsEmailVerifiedAt", holder.peopleAndTeamsEmailVerifiedAt.text)

        context.startActivity(intent)

    }

    }

