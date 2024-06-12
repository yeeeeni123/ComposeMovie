package com.example.ticketing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ticketing.input.iTicketingViewModelInput
import com.yeen.data.entity.CategoryEntity


@Composable
fun CategoryRow(
    categoryEntity: CategoryEntity,
    input: iTicketingViewModelInput
) {
    Column {
//        CategoryTitle(categoryEntity.genre)
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = 10.dp
            )
        ) {
            itemsIndexed(categoryEntity.movieEntities) {_, item ->
                MovieItem(
                    movie = item,
                    input = input
                )
            }
        }
    }
}

@Composable
fun CategoryTitle(title: String) {
    Text(
        text =  title,
        modifier = Modifier.padding(10.dp)
    )

}
