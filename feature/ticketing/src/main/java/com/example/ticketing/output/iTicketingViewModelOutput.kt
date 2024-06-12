package com.example.ticketing.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface iTicketingViewModelOutput {
    val ticketingState : StateFlow<TicketingState>
    val ticketingUiEffect: SharedFlow<TicketingUiEffect>
}

sealed class TicketingUiEffect {
    data class OpenMovieDetail(val movieName : String) : TicketingUiEffect()
    object OpenInfoDialog: TicketingUiEffect()
}