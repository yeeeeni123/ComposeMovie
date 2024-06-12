package com.example.ticketing.output

import com.yeen.data.di.MovieItemEntity
import com.yeen.data.entity.CategoryEntity

sealed class TicketingState {
    object Loading: TicketingState()
    class Main(
        val categories : List<CategoryEntity>
    ) : TicketingState()

    class Failed(
        val reason : String
    ) : TicketingState()
}