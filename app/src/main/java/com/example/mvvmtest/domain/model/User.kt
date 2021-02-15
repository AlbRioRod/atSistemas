package com.example.mvvmtest.domain.model


data class User(
    var id: String,
    var name: String,
    var title: String = "",
    var email: String = "",
    var address: Address = Address(),
    var phone: String = "",
    var website: String = "",
    var company: Company = Company()
)

data class Address(
    var street: String = "",
    var suite: String = "",
    var city: String = "",
    var zipcode: String = "",
    var geo: Geo = Geo()
)

data class Geo(
    var lat: String = "",
    var lng: String = ""
)

data class Company(
    var name: String = "",
    var catchPhrase: String = "",
    var bs: String = ""
)