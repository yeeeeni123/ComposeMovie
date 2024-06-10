package com.yeen.designsystem.dialog

import androidx.compose.runtime.Composable
import com.yeen.designsystem.models.DialogButton
import com.yeen.designsystem.models.DialogContent
import com.yeen.designsystem.models.DialogText
import com.yeen.designsystem.models.DialogTitle


@Composable
fun DialogPopup.Rating(
    movieName: String,
    rating: Float,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("평점"),
        dialogContent = DialogContent.Rating(
            DialogText.Rating(
                text = movieName,
                rating = 8.2f
            )
        ),
        buttons = buttons
    )

}