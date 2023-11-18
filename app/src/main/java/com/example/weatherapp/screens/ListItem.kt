package com.example.weatherapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.presentation.ui.theme.primaryPink
@Composable
fun HoursList(list: List<WeatherModel>, currentDay: MutableState<WeatherModel>){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        itemsIndexed(
            list
        ) { _, item ->
            ListItem(item, currentDay)

        }
    }
}

@Composable
fun ListItem(item: WeatherModel, currentDay: MutableState<WeatherModel>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
            .clickable {
                if (item.hours.isEmpty()) return@clickable
                currentDay.value = item
            },
        backgroundColor = primaryPink,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(modifier = Modifier
                .padding(
                    start = 8.dp,
                    top = 5.dp,
                    bottom = 5.dp)
            ) {
                Text(text = item.time)
                Text(text = item.conditionText)
            }
            Text(
                text = item.momentTemp.ifEmpty { "${item.maxTemp}/${item.minTemp}" },
                color = Color.White,
                style = TextStyle(fontSize = 25.sp)
            )
            AsyncImage(
                model = "https:${item.conditionIcon}",
                contentDescription = "Weather image API link",
                modifier = Modifier
                    .padding(
                        end = 8.dp
                    )
                    .size(35.dp)
            )
        }
    }
}

@Composable
fun SearchLocation(dialogState: MutableState<Boolean>, onSubmit: (String) -> Unit){
    var dialogText = remember {
        mutableStateOf("")
    }
    AlertDialog(onDismissRequest = {
        onSubmit(dialogText.value)
        dialogState.value = false
  }, confirmButton = {
      TextButton(onClick = {}) {
          Text(text = "OK")
      }
  },
      dismissButton = {
          TextButton(onClick = {}) {
              Text(text = "Cancel")
          }
      },
      title = {
          Column(modifier = Modifier.fillMaxWidth()) {
              Text(text = "Введите название города")
              TextField(value = dialogText.value, onValueChange = {
                    dialogText.value = it
              })
          }

      }
  )
}
