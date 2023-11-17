package com.example.weatherapp.data

data class WeatherModel(
    val city: String,
    val time: String,
    val momentTemp: String,
    val conditionText: String,
    val conditionIcon: String,
    val maxTemp: String,
    val minTemp: String,
    val hours: String,

)
