package com.example.myapplication.model

data class Result(
        val created: String?,
        val episode: List<String?>?,
        val gender: String?,
        val id: Int?,
        var image: String?,
        val location: Location?,
        var name: String?,
        val origin: Origin?,
        val species: String?,
        val status: String?,
        val type: String?,
        val url: String?
)