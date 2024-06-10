package com.yeen.data.seat

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class SeatUiModel(
    val selectedSeat: Seat,
    val visibleDates: List<Seat>
) {
    data class Seat(
        val seatNm: String,
        val isSellOut: Boolean,
        val isSelected: Boolean
    ) {

    }
}