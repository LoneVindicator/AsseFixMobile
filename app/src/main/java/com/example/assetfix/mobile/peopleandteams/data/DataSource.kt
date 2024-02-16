package com.example.assetfix.mobile.peopleandteams.data

import com.example.assetfix.mobile.peopleandteams.model.PeopleCards

class Datasource {

    private val peopleList: List<PeopleCards> = listOf(
        PeopleCards("Brian David Gilbert", "brian@identigate.co.ke", "254721819101", "Tech Analyst", "1 year ago"),
        PeopleCards("Alice Johnson", "alice@example.com", "1234567890", "Data Scientist", "6 months ago") ,
        PeopleCards("Bob Smith", "bob@example.com", "9876543210", "Software Engineer", "1 year ago"),
        PeopleCards("Charlie Brown", "charlie@example.com", "5551234567", "Graphic Designer", "2 years ago"),
        PeopleCards("David Lee", "david@example.com", "9998887776", "Marketing Manager", "3 months ago"),
        PeopleCards("Emily Wang", "emily@example.com", "3334445555", "Financial Analyst", "5 months ago"),
        PeopleCards("Frank Rodriguez", "frank@example.com", "7776665554", "HR Specialist", "8 months ago"),
        PeopleCards("Grace Kim", "grace@example.com", "2223334444", "Project Manager", "9 months ago"),
        PeopleCards("Hannah Martinez", "hannah@example.com", "8887776665", "Sales Representative", "10 months ago"),
        PeopleCards("Ian Thompson", "ian@example.com", "4445556667", "UX/UI Designer", "2 years ago"),
        PeopleCards("Julia Garcia", "julia@example.com", "6667778889", "Product Manager", "11 months ago"),

    )

    fun loadPeopleCards(): List<PeopleCards> {
        return peopleList
    }
}
