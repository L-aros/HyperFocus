package io.github.miuix.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun MiuixScaffold(
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = topBar,
        content = content
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MiuixTopBar(
    title: @Composable () -> Unit,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    TopAppBar(
        title = title,
        navigationIcon = {
            if (navigationIcon != null) {
                navigationIcon()
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }
        }
    )
}

@Composable
fun MiuixText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified
) {
    val resolvedStyle = style.copy(
        textAlign = textAlign ?: style.textAlign,
        lineHeight = if (lineHeight != TextUnit.Unspecified) lineHeight else style.lineHeight
    )

    Text(
        text = text,
        modifier = modifier,
        style = resolvedStyle,
        color = color,
        textDecoration = textDecoration
    )
}
