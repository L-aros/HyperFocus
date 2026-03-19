package io.github.miuix.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

typealias MiuixColors = ColorScheme

fun darkMiuixColors(): MiuixColors = darkColorScheme()

fun lightMiuixColors(): MiuixColors = lightColorScheme()

@Composable
fun MiuixTheme(
    colors: MiuixColors = if (isSystemInDarkTheme()) darkMiuixColors() else lightMiuixColors(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
