package com.yeen.main.dialog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class MutableDialogStateImpl<T>(initialData: T) : MutableDialogState<T> {

    private var _dialogData = mutableStateOf(initialData)

    override val dialogData: State<T>
        get() = _dialogData

    private var _isVisible = mutableStateOf(false)

    override val isVisible: State<Boolean>
        get() = _isVisible

    override fun showDialog() {
        _isVisible.value = true
    }

    override fun showDialog(data: T) {
        _dialogData.value = data
        _isVisible.value = true
    }

    override fun hideDialog() {
        _isVisible.value = false
    }

}