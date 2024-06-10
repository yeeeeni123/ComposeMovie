package com.yeen.designsystem.dialog

import androidx.compose.runtime.Composable
import com.yeen.designsystem.models.DialogButton
import com.yeen.designsystem.models.DialogContent
import com.yeen.designsystem.models.DialogText
import com.yeen.designsystem.models.DialogTitle

@Composable
fun DialogPopup.Default(
    title: String,
    content: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Default(title),
        dialogContent = DialogContent.Default(
            DialogText.Default(content)
        ),
        buttons = buttons
    )
}