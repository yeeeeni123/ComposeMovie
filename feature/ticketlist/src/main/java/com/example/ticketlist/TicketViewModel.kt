package com.example.ticketlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketlist.dialog.CustomBottomSheetDialogState
import com.yeen.model.InvalidUserException
import com.yeen.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle?
) : ViewModel() {



    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val _selectArea = mutableStateOf("")
    val selectArea: State<String> = _selectArea



    fun showSnackBar(msg:String) {
        viewModelScope.launch {
            _eventFlow.emit(
                UiEvent.ShowSnackBar(
                    msg
                )
            )
        }
    }


    val customBottomSheetDialogState: MutableState<CustomBottomSheetDialogState> = mutableStateOf(
        CustomBottomSheetDialogState()
    )

    fun showBottomSheetDialog() {
        customBottomSheetDialogState.value = CustomBottomSheetDialogState(
            is_click = true,
            onClickConfirm = {
                resetBottomSheetDialogState()
            },
            onClickCancel = {
                resetBottomSheetDialogState()
            }
        )
    }



    fun resetBottomSheetDialogState() {
        customBottomSheetDialogState.value = CustomBottomSheetDialogState()
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object Ticket : UiEvent()
    }

}