package com.example.weatherapp.data

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

const val API_KEY = "e2d795b244ec416bb83112522231910"
@Composable
private fun getResult(city: String, state: MutableState<String>, context: Context, ){
    val url = "https://api.weatherapi.com/v1/current.json" +
            "?key=$API_KEY&" +
            "q=$city"+
            "&days" +
            "1" +
            "&aqi = no"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        {
                response ->
                Log.d("MyLog", "Response $response")
        },
        {
                Log.d("MyLog", "VolleyError $it")
        }
    )
    queue.add(stringRequest)
}
