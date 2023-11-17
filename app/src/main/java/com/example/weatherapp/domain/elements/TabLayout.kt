package com.example.weatherapp.domain.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.presentation.ui.uitheme.primaryPink
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun TabLayout(){
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { pos ->
                   TabRowDefaults.Indicator(
                       Modifier.pagerTabIndicatorOffset(pagerState, pos)
                   )
            },
            backgroundColor = primaryPink,
            contentColor = Color.White
        ) {
            tabList.forEachIndexed{index, text ->
                Tab(
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                        text = {
                            Text(
                                text = text,
                                color = Color.White
                            )
                        }
                    )
            }
        }
        HorizontalPager(
            count = tabList.size,
            state = pagerState,
            modifier = Modifier.weight(1.0f)
        ) {
            index ->
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
               itemsIndexed(
                   listOf(
                       WeatherModel()
                   )
               ) { _, item -> ListItem(item)

                }
            }
        }
    }
}