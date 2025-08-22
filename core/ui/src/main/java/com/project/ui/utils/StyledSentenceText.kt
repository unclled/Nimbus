package com.project.ui.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import com.project.ui.theme.NimbusTheme

@Composable
fun StyledSentenceText(
    fullText: String,
    wordsToStyle: List<String>,
    modifier: Modifier = Modifier
) {
    val styledText = buildAnnotatedString {
        append(fullText)

        wordsToStyle.forEach { word ->
            var startIndex = fullText.indexOf(word)
            while (startIndex != -1) {
                addStyle(
                    style = SpanStyle(color = NimbusTheme.colors.accentColor),
                    start = startIndex,
                    end = startIndex + word.length
                )
                startIndex = fullText.indexOf(word, startIndex + 1)
            }
        }
    }

    Text(
        text = styledText,
        style = NimbusTheme.typography.displayMedium,
        color = NimbusTheme.colors.primaryContentColor,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}