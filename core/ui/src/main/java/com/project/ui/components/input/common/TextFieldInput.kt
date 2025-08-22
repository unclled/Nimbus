package com.project.ui.components.input.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import com.project.nimbus.core.ui.R
import com.project.ui.theme.NimbusTheme

@Composable
fun TextFieldInput(
    inputText: String,
    placeholderText: String,
    onValueChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_20))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            value = inputText,
            onValueChange = onValueChange,
            textStyle = NimbusTheme.typography.bodyMedium.copy(
                color = NimbusTheme.colors.componentBackgroundColor
            ),
            singleLine = true,
            cursorBrush = SolidColor(NimbusTheme.colors.componentBackgroundColor),
            decorationBox = { innerTextField ->
                if (inputText.isEmpty()) {
                    Text(
                        text = placeholderText,
                        style = NimbusTheme.typography.bodyMedium,
                        color = NimbusTheme.colors.primaryContentColor,
                        maxLines = 1
                    )
                }
                innerTextField()
            },
            maxLines = 1,
            modifier = Modifier.weight(1f)
        )
    }
}