package com.yeen.main.bottomnav

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yeen.data.calendar.CalendarDataSource
import com.yeen.data.calendar.CalendarUiModel
import com.yeen.data.response.MovieItem
import com.yeen.main.MainViewModel
import com.yeen.main.R
import com.yeen.main.dialog.CustomBottomSheetDialog
import com.yeen.main.home.ProfileImage
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreens() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home Screen",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun Ticketing() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Ticketing",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun TicketList() {
    val dataSource = CalendarDataSource()
    val viewModel: MainViewModel = hiltViewModel()
    var calendarUiModel by remember { mutableStateOf(dataSource.getData(lastSelectedDate = dataSource.today)) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.white))
    ) {
        Header(
            data = calendarUiModel,
            onPrevClickListener = { startDate ->
                val finalStartDate = startDate.minusDays(1)
                calendarUiModel = dataSource.getData(
                    startDate = finalStartDate,
                    lastSelectedDate = calendarUiModel.selectedDate.date
                )
            },
            onNextClickListener = { endDate ->
                val finalStartDate = endDate.plusDays(2)
                calendarUiModel = dataSource.getData(
                    startDate = finalStartDate,
                    lastSelectedDate = calendarUiModel.selectedDate.date
                )
            }
        )
        Content(data = calendarUiModel, onDateClickListener = { date ->
            calendarUiModel = calendarUiModel.copy(
                selectedDate = date,
                visibleDates = calendarUiModel.visibleDates.map {
                    it.copy(
                        isSelected = it.date.isEqual(date.date)
                    )
                }
            )
        })
        SelectTheater(viewModel)
    }
}


@Composable
fun Header(
    data: CalendarUiModel,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit
) {
    Row(
        Modifier
            .padding(5.dp, 0.dp, 0.dp, 0.dp)
    ) {
        Text(
            text = if (data.selectedDate.isToday) {
                data.selectedDate.date.format(
                    DateTimeFormatter.ofPattern("yyyy.MM.dd(E)")
                        .withLocale(Locale.forLanguageTag("ko"))
                ) + " 오늘"
            } else {
                data.selectedDate.date.format(
                    DateTimeFormatter.ofPattern("yyyy.MM.dd(E)")
                        .withLocale(Locale.forLanguageTag("ko"))
                )
            },
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )

        IconButton(onClick = {
            onPrevClickListener(data.startDate.date)
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Previous"
            )
        }
        IconButton(onClick = {
            onNextClickListener(data.endDate.date)
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Next"
            )
        }

    }
}

@Composable
fun Content(data: CalendarUiModel, onDateClickListener: (CalendarUiModel.Date) -> Unit) {
    LazyRow {
        // pass the visibleDates to the UI
        items(items = data.visibleDates) { date ->
            ContentItem(date, onDateClickListener)
        }
    }
}

@Composable
fun ContentItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 5.dp)
            .clickable {
                onClickListener(date)
            },
        colors = CardDefaults.cardColors(
            containerColor = if (date.isSelected) {
                colorResource(id = R.color.brightRed)
            } else {
                colorResource(id = R.color.brightRed2)
            }
        ),
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.day, // day "Mon", "Tue"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(id = R.color.white)
            )
            Text(
                text = date.date.dayOfMonth.toString(), // date "15", "16"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.white)
            )
        }
    }
}


@Composable
fun SelectTheater(
    viewModel: MainViewModel
) {
    val customBottomSheetDialogState = viewModel.customBottomSheetDialogState.value
    Column(
        modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(7.dp))
        Column(
            Modifier
                .padding(5.dp, 5.dp, 0.dp, 0.dp)
                .height(30.dp)
                .width(90.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.brightRed),
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable {
                    viewModel.showBottomSheetDialog()
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "극장 선택",
                color = colorResource(id = R.color.brightRed),
                fontSize = 14.sp
            )
        }
    }

    if (customBottomSheetDialogState.is_click) {
        CustomBottomSheetDialog(
            onClickCancel = { customBottomSheetDialogState.onClickCancel() },
            onClickConfirm = { customBottomSheetDialogState.onClickConfirm() }
        )
    }

}


@Composable
fun MyPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "MyPage",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


