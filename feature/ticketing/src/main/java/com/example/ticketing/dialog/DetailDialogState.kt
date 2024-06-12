package com.example.ticketing.dialog

data class DetailDialogState(
    val title: String = "",
    val description: String = "",
    val onClickConfirm: () -> Unit = {},
    val onClickCancel: () -> Unit = {},
)