package com.example.assetfix.mobile.peopleandteams.model

import android.util.Log
import androidx.annotation.StringRes
import com.example.assetfix.mobile.workOrder.model.*

data class PeopleCards(
    val peopleAndTeamsName: String?,
    val peopleAndTeamsEmail: String?,
    val peopleAndTeamsPhoneNumber: String?,
    val peopleAndTeamsRole: String?,
    val peopleAndTeamsEmailVerifiedAt: String?,

    )

data class PeopleList(
    val data: List<PeopleItem>
)

data class PeopleItem(
    val name: String?,
    val email: String?,
    val phone_number: String?,
    val role: String?,
    val email_verified_at: String?
)

fun mapPeopleListToPeopleCards(peopleList: PeopleList): List<PeopleCards> {

    return peopleList.data.map {
        PeopleCards(
            peopleAndTeamsName = it.name?: "",
            peopleAndTeamsEmail = it.email?: "",
            peopleAndTeamsPhoneNumber = it.phone_number?: "",
            peopleAndTeamsRole = it.role?: "",
            peopleAndTeamsEmailVerifiedAt = it.email_verified_at?: "",
            )

    }

}

