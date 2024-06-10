package com.yeen.designsystem.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jun.designsystem.R
import com.yeen.designsystem.dialog.component.button.DialogButtonColumn
import com.yeen.designsystem.dialog.component.content.DialogContentWrapper
import com.yeen.designsystem.dialog.component.title.DialogTitleWrapper
import com.yeen.designsystem.models.DialogButton
import com.yeen.designsystem.models.DialogContent
import com.yeen.designsystem.models.DialogText
import com.yeen.designsystem.models.DialogTitle
import com.yeen.designsystem.theme.Paddings

@Composable
fun BaseDialogPopup(
    dialogTitle: DialogTitle? = null,
    dialogContent : DialogContent? = null,
    buttons : List<DialogButton>? = null
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = Paddings.none,
        backgroundColor = Color.White,
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            dialogTitle?.let {
                DialogTitleWrapper(it)
            }
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    )
            ) {
                dialogContent?.let { DialogContentWrapper(it) }
                buttons.let { DialogButtonColumn(it) }
            }
        }
    }
}

@Preview
@Composable
fun BaseDialogPopupPreview() {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header("TITLE"),
        dialogContent = DialogContent.Large(
            DialogText.Default("abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde")
        ),
        buttons = listOf(
            DialogButton.Primary("확인") {

            }
        )
    )
}

@Preview
@Composable
fun BaseDialogPopupPreview2() {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("TITLE"),
        dialogContent = DialogContent.Default(
            DialogText.Default("abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde")
        ),
        buttons = listOf(
            DialogButton.Secondary("확인") {},
            DialogButton.UnderlinedText("취소") {}
        )
    )
}


//@Preview
//@Composable
//fun BaseDialogPopupPreview3() {
//    BaseDialogPopup(
//        dialogTitle = DialogTitle.Large("TITLE"),
//        dialogContent = DialogContent.Rating(
//            DialogText.Rating(
//                text = ""
//            )
//        ),
//        buttons = listOf(
//            DialogButton.Secondary("확인") {},
//            DialogButton.UnderlinedText("취소") {}
//        )
//    )
//}