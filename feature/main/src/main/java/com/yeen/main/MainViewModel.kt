package com.yeen.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeen.domain.usecase.SignInUsecase
import com.yeen.main.dialog.CustomBottomSheetDialogState
import com.yeen.model.InvalidUserException
import com.yeen.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val signInUseCase: SignInUsecase,
    savedStateHandle: SavedStateHandle?
) : ViewModel() {

    private val _userEmail = mutableStateOf("")
    val userEmail: State<String> = _userEmail

    private val _userPassword = mutableStateOf("")
    val userPassword: State<String> = _userPassword

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.EnteredEmail -> {
                _userEmail.value = event.value

            }
            is MainEvent.EnteredPassword -> {
                _userPassword.value = event.value
            }
            is MainEvent.SignIn -> {
                viewModelScope.launch {
                    try {
                        signInUseCase(
                            User(
                                email = userEmail.value,
                                password = userPassword.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SignIn)
                    } catch (e: InvalidUserException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "회원가입할 수 없습니다."
                            )
                        )
                    }
                }
            }
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
        object SignIn : UiEvent()
    }
}
