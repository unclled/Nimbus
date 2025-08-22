package com.project.ui.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.nimbus.core.ui.R
import com.project.ui.components.input.common.TextFieldContainer
import com.project.ui.components.input.common.TextFieldInput
import com.project.ui.theme.NimbusTheme


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    placeholderText: String,
    inputText: String,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextFieldContainer {
            TextFieldInput(
                inputText = inputText,
                placeholderText = placeholderText,
                onValueChange = onValueChange
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CustomTextFieldPreview() {
    NimbusTheme {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_16))) {
            CustomTextField(
                placeholderText = "Россия, Кировская область, Киров",
                onValueChange = {},
                inputText = "",
            )
        }
    }
}
