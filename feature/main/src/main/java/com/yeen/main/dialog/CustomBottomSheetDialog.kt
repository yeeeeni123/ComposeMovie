package com.yeen.main.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yeen.data.response.MovieAreaModel
import com.yeen.data.response.MovieAreaSubModel
import com.yeen.login.LoginEvent
import com.yeen.login.R
import com.yeen.main.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheetDialog(
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    var key by remember { mutableStateOf(0) }

    val areaList = listOf(
        MovieAreaModel.MovieArea(0, "서울", true),
        MovieAreaModel.MovieArea(1, "경기", false),
        MovieAreaModel.MovieArea(2, "강원", false)
    )

    val area_seoul = listOf(
        MovieAreaSubModel.MovieAreaSub("왕십리", false),
        MovieAreaSubModel.MovieAreaSub("용산", false),
        MovieAreaSubModel.MovieAreaSub("압구정", false),
        MovieAreaSubModel.MovieAreaSub("강남", false)
    )

    val area_gyeonggi = listOf(
        MovieAreaSubModel.MovieAreaSub("역곡", false),
        MovieAreaSubModel.MovieAreaSub("광주", false),
        MovieAreaSubModel.MovieAreaSub("의정부", false),
        MovieAreaSubModel.MovieAreaSub("부천", false),
        MovieAreaSubModel.MovieAreaSub("파주", false),
        MovieAreaSubModel.MovieAreaSub("산본", false),
        MovieAreaSubModel.MovieAreaSub("포천", false)
    )

    val area_gangwon = listOf(
        MovieAreaSubModel.MovieAreaSub("강릉", false),
        MovieAreaSubModel.MovieAreaSub("원주", false),
        MovieAreaSubModel.MovieAreaSub("춘천", false),
        MovieAreaSubModel.MovieAreaSub("인제", false),
        MovieAreaSubModel.MovieAreaSub("원통", false),
        MovieAreaSubModel.MovieAreaSub("기린", false)
    )

    var hashMap = HashMap<Int,List<MovieAreaSubModel.MovieAreaSub>>()
    hashMap.put(0, area_seoul)
    hashMap.put(1, area_gyeonggi)
    hashMap.put(2, area_gangwon)

    val movieArea = MovieAreaModel(areaList.get(key), areaList)
    var movieSubArea = MovieAreaSubModel(hashMap.get(key)!!.get(key), hashMap.get(key)!!)
    var areaUiModel by remember { mutableStateOf(movieArea) }
    var areaSubUiModel by remember { mutableStateOf(movieSubArea) }

    ModalBottomSheet(
        onDismissRequest = { onClickCancel() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier
                .padding(top = 3.dp, start = 10.dp, end = 10.dp, bottom = bottomPadding)
                .fillMaxSize()
                .fillMaxHeight()
        ) {
            Row(
                Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)
            ) {
                AreaList(areaUiModel, onItemClick = { area ->
                    key = area.key
                    areaSubUiModel = MovieAreaSubModel(hashMap.get(area.key)!!.get(area.key), hashMap.get(area.key)!!)
                    areaUiModel = areaUiModel.copy(
                        selectedArea = area,
                        visibleAreas = areaUiModel.visibleAreas.map {
                            it.copy(
                                clickable = it.key.equals(area.key)
                            )
                        }
                    )
                })

                AreaListSub(areaSubUiModel, onItemClick = { area ->
                    areaSubUiModel = areaSubUiModel.copy(
                        selectedAreaSub = area,
                        visibleAreaSub = areaSubUiModel.visibleAreaSub.map {
                            it.copy(
                                isSelected = it.area.equals(area.area)
                            )
                        }
                    )
                })
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    .height(50.dp)
                    .background(
                        color = colorResource(id = R.color.brightRed),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        scope
                            .launch {
                                modalBottomSheetState.hide()
                            }
                            .invokeOnCompletion {
                                onClickConfirm()
                            }
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("극장 선택", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}


@Composable
fun AreaList(areaList: MovieAreaModel, onItemClick: (MovieAreaModel.MovieArea) -> Unit) {
    LazyColumn(
        userScrollEnabled = true
    ) {
        items(areaList.visibleAreas) { area ->
            AreaItem(area = area, onItemClick = { selectedArea ->
                onItemClick(selectedArea)
            })
        }

    }
}



@Composable
fun AreaListSub(areaList: MovieAreaSubModel, onItemClick: (MovieAreaSubModel.MovieAreaSub) -> Unit) {
    LazyColumn(
        userScrollEnabled = true,
    ) {
        items(areaList.visibleAreaSub) { area ->
            AreaSubItem(item = area,
                onItemClick = { selectedArea ->
                    onItemClick(selectedArea)
                })
        }
    }
}


@Composable
fun AreaItem(area: MovieAreaModel.MovieArea, onItemClick: (MovieAreaModel.MovieArea) -> Unit) {
    // Your item UI code here
    // Detect click and invoke the onItemClick lambda
    Box(
        modifier = Modifier
            .clickable { onItemClick(area) }
            .padding(16.dp)
    ) {
        Text(text = area.name,
            color = if (area.clickable) {
                colorResource(id = R.color.brightRed)
            } else {
                colorResource(id = R.color.black)
            },
            fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun AreaSubItem(item: MovieAreaSubModel.MovieAreaSub, onItemClick: (MovieAreaSubModel.MovieAreaSub) -> Unit) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 0.dp)
            .width(120.dp)
            .height(50.dp)
            .background(
                color = if (item.isSelected) {
                    colorResource(id = R.color.brightRed)
                } else {
                    Color.Transparent
                },

                shape = if (item.isSelected) {
                    RoundedCornerShape(20.dp)
                } else {
                    RoundedCornerShape(0.dp)
                }
            )
            .clickable {
                onItemClick(item)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(item.area, color = if (item.isSelected) {
            colorResource(id = R.color.white)
        } else {
            colorResource(id = R.color.black)
        }, fontSize = 16.sp)
    }

//    Box(
//        modifier = Modifier
//            .height(50.dp)
//            .width(120.dp)
//            .clickable { onItemClick(item) }
//            .background(
//                color = if (item.isSelected) {
//                    colorResource(id = R.color.brightRed)
//                } else {
//                    Color.Transparent
//                },
//
//                shape = if (item.isSelected) {
//                    RoundedCornerShape(20.dp)
//                } else {
//                    RoundedCornerShape(0.dp)
//                }
//            )
//            .padding(16.dp)
//    ) {
//        Text(item.area, color =if (item.isSelected) {
//            colorResource(id = R.color.white)
//        } else {
//            colorResource(id = R.color.black)
//        }, fontSize = 16.sp)
//    }
}






