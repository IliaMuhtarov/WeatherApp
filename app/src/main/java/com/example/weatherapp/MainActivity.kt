package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.example.weatherapp.screens.MainCard
import com.example.weatherapp.screens.SearchLocation
import com.example.weatherapp.screens.TabLayout
import org.json.JSONObject


const val API_KEY = "e2d795b244ec416bb83112522231910"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val daysList = remember {
                    mutableStateOf(listOf<WeatherModel>())
                }
                val dialogState = remember {
                    mutableStateOf(false)
                }

                val currentDay = remember {
                    mutableStateOf(WeatherModel(
                        "","","0.0","","","0.0","0.0",
                        ""
                    )
                    )
                }
                if(dialogState.value){
                    SearchLocation(dialogState, onSubmit = {
                        getData(it,this, daysList, currentDay )
                    })
                }
                getData("Voronezh",this, daysList, currentDay )
                Image(
                    painter = painterResource(id = R.drawable.weather_fon),
                    contentDescription = "Weather app fon",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.7f),
                    contentScale = ContentScale.FillBounds
                )
                Column {
                    MainCard(currentDay, onClickSync = {
                        getData("Voronezh",this@MainActivity, daysList, currentDay)
                    }, onClickSearch = {
                        dialogState.value = true
                    }
                    )
                    TabLayout(daysList, currentDay)
                }
            }
        }
    }
}


fun getData(city: String, context: Context, daysList: MutableState<List<WeatherModel>>,
            currentDay: MutableState<WeatherModel>){
    val url = "https://api.weatherapi.com/v1/forecast.json" +
            "?key=$API_KEY&" +
            "q=$city"+
            "&days=" +
            "5"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        {
                response ->
            var list = getWeatherByDays(response)
            currentDay.value = list[0]
            daysList.value = list
        },
        {
            Log.d("MyLog", "VolleyError $it")
        }
    )
    queue.add(stringRequest)
}



fun getWeatherByDays(response: String): List<WeatherModel>{
    if(response.isEmpty()) return listOf()
    val list = ArrayList<WeatherModel>()
    val mainObject = JSONObject(response)
    val city = mainObject.getJSONObject("location").getString("name")
    var days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")

    for(i in 0 until days.length()){
        val item = days[i] as JSONObject
        list.add(
            WeatherModel(
                city,
                item.getString("date"),
                "",
                item.getJSONObject("day").getJSONObject("condition").getString("text"),
                item.getJSONObject("day").getJSONObject("condition").getString("icon"),
                item.getJSONObject("day").getString("maxtemp_c"),
                item.getJSONObject("day").getString("mintemp_c"),
                item.getJSONArray("hour").toString()
            )
        )
    }
    list[0] = list[0].copy(
        time = mainObject.getJSONObject("current").getString("last_updated"),
        momentTemp = mainObject.getJSONObject("current").getString("temp_c")
    )
    return list
}

