package com.example.ticketing

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketing.dialog.DetailDialogState
import com.example.ticketing.input.iTicketingViewModelInput
import com.example.ticketing.output.TicketingState
import com.example.ticketing.output.TicketingUiEffect
import com.example.ticketing.output.iTicketingViewModelOutput
import com.yeen.data.entity.EntityWrapper
import com.yeen.data.repository.IMovieDataSource
import com.yeen.domain.usecase.IGetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TicketingViewModel @Inject constructor(
    private val movieRepository: IMovieDataSource,
    private val getCategoryUseCase: IGetCategoryUseCase
) : ViewModel(), iTicketingViewModelOutput, iTicketingViewModelInput {

    val output: iTicketingViewModelOutput = this
    val input : iTicketingViewModelInput = this

    //화면에 보여주기 위한 Flow
    private val _ticketingState: MutableStateFlow<TicketingState> =
        MutableStateFlow(TicketingState.Loading)
    override val ticketingState: StateFlow<TicketingState>
        get() = _ticketingState

    //사용자로부터 입력을 받아서 화면 단에서 액션을 수행하기 위한 Flow
    private val _ticketingUiEffect = MutableSharedFlow<TicketingUiEffect>(replay = 0)
    override val ticketingUiEffect: SharedFlow<TicketingUiEffect>
        get() = _ticketingUiEffect

    init {
        fetchMovie()
    }


    private fun fetchMovie() {
        viewModelScope.launch {
            _ticketingState.value = TicketingState.Loading

            val categories = getCategoryUseCase()
            _ticketingState.value = when (categories) {

                is EntityWrapper.Success -> {
                    TicketingState.Main(
                        categories = categories.entity
                    )
                }

                is EntityWrapper.Fail -> {
                    TicketingState.Failed(
                        reason = categories.error.message ?: "Unknown Error"
                    )
                }
            }
        }
    }

    override fun openDetail(movieName: String) {
        showMovieSeatDialog()
    }

    override fun openInfoDialog() {

    }

    override fun refreshTicketing() {

    }


    val movieDialogState: MutableState<DetailDialogState> = mutableStateOf(
        DetailDialogState()
    )
    fun showMovieSeatDialog() {
        movieDialogState.value = DetailDialogState(
            title = "상세 화면",
            description = "",
            onClickConfirm = {
                resetDialogState()
            },
            onClickCancel = {
                resetDialogState()
            }
        )
    }
    // 다이얼로그 상태 초기화
    fun resetDialogState() {
        movieDialogState.value = DetailDialogState()
    }

}