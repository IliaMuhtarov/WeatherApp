package com.example.weatherapp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.domain.elements.CardElement
import com.example.weatherapp.domain.elements.TabLayout


@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Image(
        painter = painterResource(id = R.drawable.weather_fon),
        contentDescription = "Weather app fon",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.7f),
        contentScale = ContentScale.FillBounds)
    Column {
        CardElement()
        TabLayout()
    }

}

