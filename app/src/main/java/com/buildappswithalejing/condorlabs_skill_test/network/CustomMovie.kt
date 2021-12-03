package com.buildappswithalejing.condorlabs_skill_test.network

data class CustomMovie (
    var id: Int,
    var title: String,
    var overview: String,
    var releaseDate: String,
    var budget: Int,
    var videoPath: String,
    var imagePath: String,
    var favorite: Boolean
    )