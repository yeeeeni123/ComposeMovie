package com.yeen.designsystem.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jun.designsystem.R
import com.yeen.designsystem.models.DialogButton
import com.yeen.designsystem.models.LeadingIconData


@Preview
@Composable
fun AlertPreview() {
    DialogPopup.Alert(
        title = "알림", content = "내용", buttons = listOf(
            DialogButton.UnderlinedText("확인") {},
        )
    )
}


@Preview
@Composable
fun DefaultPreview() {
    DialogPopup.Default(title = "알림", content = "내용", buttons = listOf(
        DialogButton.Primary("확인") {},
        DialogButton.SecondaryBorderless("취소") {}
    ))
}


@Preview
@Composable
fun RatingPreview() {

    DialogPopup.Rating(
        movieName = "The Lord of the Rings: The Two Towers",
        rating = 7.5f,
        buttons = listOf(
            DialogButton.Primary(
                title = "OPEN",
                leadingIconData = LeadingIconData(
                    iconDrawable = R.drawable.ic_send,
                    iconContentDescription = null
                )
            ) {},
            DialogButton.SecondaryBorderless("CANCEL") {}
        )
    )
}