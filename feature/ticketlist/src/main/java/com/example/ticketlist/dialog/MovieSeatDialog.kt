package com.example.ticketlist.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.BeyondBoundsLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import com.yeen.data.calendar.CalendarUiModel
import com.yeen.data.seat.SeatUiModel
import com.yeen.designsystem.theme.BrightRed
import com.yeen.designsystem.theme.BrightRed2

@Composable
fun MovieSeatDialog(
    title: String,
    description: String,
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit
) {
    Popup(
        onDismissRequest = onClickCancel,
        popupPositionProvider = object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize
            ): IntOffset {
                return IntOffset.Zero
            }
        }) {
        Column(
            Modifier
                .fillMaxSize()
                .background(
                    color = Color.White
                )
        ){
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {

                }
//                Content(
//                    seat = calendarUiModel,
//                    onSeatClickListener = { date ->
//                        calendarUiModel = calendarUiModel.copy(
//                            selectedDate = date,
//                            visibleDates = calendarUiModel.visibleDates.map {
//                                it.copy(
//                                    isSelected = it.date.isEqual(date.date)
//                                )
//                            }
//                        )
//                    }
//                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.LightGray)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min) // Row의 높이를 내부 컴포넌트에 맞춤
                ) {
                    Button(
                        onClick = { onClickCancel() },
                        shape = RectangleShape,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White, // 버튼 배경색상
                            contentColor = Color.Black, // 버튼 텍스트 색상
                            disabledContainerColor = Color.Gray, // 버튼 비활성화 배경 색상
                            disabledContentColor = Color.White, // 버튼 비활성화 텍스트 색상
                        ),

                        ) {
                        Text(
                            text = "취소",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(color = Color.LightGray)
                    )

                    Button(
                        onClick = { onClickConfirm() },
                        shape = RectangleShape,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White, // 버튼 배경색상
                            contentColor = Color.Red, // 버튼 텍스트 색상
                            disabledContainerColor = Color.Gray, // 버튼 비활성화 배경 색상
                            disabledContentColor = Color.White, // 버튼 비활성화 텍스트 색상
                        ),
                    ) {
                        Text(
                            text = "확인",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Content(seat: SeatUiModel, onSeatClickListener: (SeatUiModel.Seat) -> Unit) {
    LazyRow {
        // pass the visibleDates to the UI
        items(items = seat.visibleDates) { seat ->
            ContentItem(seat, onSeatClickListener)
        }
    }
}

@Composable
fun ContentItem(
    seat: SeatUiModel.Seat,
    onClickListener: (SeatUiModel.Seat) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 5.dp)
            .clickable {
                onClickListener(seat)
            },
        colors = CardDefaults.cardColors(
            containerColor = if (seat.isSelected) {
                BrightRed
            } else {
                BrightRed2
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
                text = seat.seatNm,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
    }
}
