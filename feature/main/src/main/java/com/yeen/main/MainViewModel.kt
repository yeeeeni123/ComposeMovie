package com.yeen.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeen.domain.usecase.SignInUsecase
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

    val _selectArea = mutableStateOf("")
    val selectArea: State<String> = _selectArea


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

    fun showSnackBar(msg:String) {
        viewModelScope.launch {
            _eventFlow.emit(
                UiEvent.ShowSnackBar(
                    msg
                )
            )
        }
    }



    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SignIn : UiEvent()
    }
}
