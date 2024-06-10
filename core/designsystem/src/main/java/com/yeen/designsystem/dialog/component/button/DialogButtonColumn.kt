package com.yeen.designsystem.dialog.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yeen.designsystem.button.PrimaryButton
import com.yeen.designsystem.button.SecondaryBorderlessButton
import com.yeen.designsystem.button.SecondaryButton
import com.yeen.designsystem.button.UnderlinedTextButton
import com.yeen.designsystem.models.DialogButton
import com.yeen.designsystem.theme.Paddings

@Composable
fun DialogButtonColumn(buttons: List<DialogButton>?) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons?.forEachIndexed { index, dialogButton ->
            if (index > 0 ) {
                Spacer(modifier = Modifier.height(Paddings.large))
            }
            when(dialogButton) {
                is DialogButton.Primary -> {
                    PrimaryButton (
                        text = dialogButton.title,
                        leadingIconData = dialogButton.leadingIconData
                    ) {
                        dialogButton.action?.invoke()
                    }
                }

                is DialogButton.Secondary -> {
                    SecondaryButton(
                        text = dialogButton.title
                    ) { dialogButton.action?.invoke() }
                }

                is DialogButton.SecondaryBorderless -> {
                    SecondaryBorderlessButton(
                        text = dialogButton.title
                    ) { dialogButton.action?.invoke() }
                }

                is DialogButton.UnderlinedText -> {
                    UnderlinedTextButton(
                        text = dialogButton.title
                    ) { dialogButton.action?.invoke() }
                }

            }
        }
    }
}