package com.example.weatherapp.domain.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.presentation.ui.uitheme.primaryPink


@Composable
@Preview
fun CardElement(){
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = primaryPink),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                        text = "20 jun 2023 13:00",
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White
                    )
                    AsyncImage(
                        model = "https://cdn.weatherapi.com/weather/64x64/day/113.png",
                        contentDescription = "Weather image API link",
                        modifier = Modifier
                            .padding(
                                top = 3.dp,
                                end = 8.dp
                            )
                            .size(35.dp))
                }
                Text(
                    text = "Moscow",
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.White
                )
                Text(
                    text = "23 С",
                    style = TextStyle(fontSize = 65.sp),
                    color = Color.White
                )
                Text(
                    text = "Sunny",
                    style = TextStyle(fontSize = 18.sp),
                    color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                       Icon(
                           painter = painterResource(id = R.drawable.ic_search),
                           contentDescription = "",
                           tint = Color.White)
                    }
                    Text(
                        text = "23C/12C",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sync),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        }

    }
}