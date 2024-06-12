package com.example.ticketing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ticketing.output.TicketingState
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ticketing.dialog.DetailDialog
import com.example.ticketing.input.iTicketingViewModelInput
import com.yeen.designsystem.theme.Paddings
import com.yeen.ticketiong.R

private val CARD_WIDTH = 150.dp
val COMMON_HORIZONTAL_PADDING = Paddings.medium

@Composable
fun TicketListScreen(
    stateHolder: State<TicketingState>,
    input: iTicketingViewModelInput) {

    val viewModel : TicketingViewModel = hiltViewModel()
    val movieSeatDialogState = viewModel.movieDialogState.value

    Column{
        Content(
            state = stateHolder.value,
            input = input
        )
    }


    if (movieSeatDialogState.title.isNotBlank()) {
        DetailDialog(
            title = movieSeatDialogState.title,
            description = movieSeatDialogState.description,
            onClickCancel = { movieSeatDialogState.onClickCancel() },
            onClickConfirm = { movieSeatDialogState.onClickConfirm() }
        )
    }

}


@Composable
fun Content(state: TicketingState, input: iTicketingViewModelInput) {

    when(state) {
        is TicketingState.Loading -> {
            Box (
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is TicketingState.Main -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(state.categories) { _, category ->
                    CategoryRow(
                        categoryEntity = category,
                        input = input
                    )

                }
            }
        }

        is TicketingState.Failed -> {
            RetryMessage(
                message = state.reason,
                input = input
            )
        }
    }

}

val IMAGE_SIZE = 48.dp
@Composable
fun RetryMessage(
    modifier: Modifier = Modifier,
    message: String,
    input: iTicketingViewModelInput
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier.requiredSize(IMAGE_SIZE),
            imageVector = ImageVector.vectorResource(R.drawable.ic_warning),
            contentDescription = null
        )

        Text(text = message,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                top = Paddings.xlarge,
                bottom = Paddings.extra
            )
        )

        Button(onClick = { input.refreshTicketing() }) {
            Text("다시 시도")
        }

    }
}


@Composable
@Preview
fun RetryMessagePreview() {
//    RetryMessage(
//        modifier = Modifier,
//        message = "다시 시도해주세요", onRetryClicked = { /*TODO*/ }
//    )
}