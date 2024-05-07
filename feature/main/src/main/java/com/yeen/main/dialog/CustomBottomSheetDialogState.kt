package com.yeen.main.dialog

import androidx.compose.runtime.State
import com.yeen.data.response.MovieAreaModel

data class CustomBottomSheetDialogState(
    var is_click: Boolean = false,
    var key : Int = 0,
    val onItemClick: (MovieAreaModel.MovieArea) -> Unit = {},
    val onClickConfirm: () -> Unit = {},
    val onClickCancel: () -> Unit = {},
)

sealed interface MutableDialogState<T> : DialogState<T> {
    override val dialogData: State<T>
    override val isVisible: State<Boolean>

    fun showDialog(data: T) // Show the dialog with new data
    fun showDialog() // Show the dialog with the previous data set
    fun hideDialog()
}

sealed interface DialogState<T> {
    val dialogData: State<T>
    val isVisible: State<Boolean>
}

