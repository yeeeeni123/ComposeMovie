package com.yeen.designsystem.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jun.designsystem.R
import com.yeen.designsystem.models.DialogButton
import com.yeen.designsystem.models.DialogContent
import com.yeen.designsystem.models.DialogText
import com.yeen.designsystem.models.DialogTitle

object DialogPopup
@Composable
fun DialogPopup.Alert(
    title: String,
    content : String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header(title),
        dialogContent = DialogContent.Large(
            DialogText.Default(content)
        ),
        buttons = buttons
    )
}