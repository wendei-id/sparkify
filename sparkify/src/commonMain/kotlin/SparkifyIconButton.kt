package id.wendei.sparkify

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SparkifyIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
    style: SparkStyle = SparkifyDefault.sparkStyle(),
    onClick: () -> Unit
) {
    Sparkify(
        modifier = modifier,
        onClick = onClick,
        style = style,
        content = {
            Icon(
                modifier = modifier,
                imageVector = imageVector,
                tint = tint,
                contentDescription = contentDescription
            )
        },
    )
}

@Composable
fun SparkifyIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
    style: SparkStyle = SparkifyDefault.sparkStyle(),
    onClick: () -> Unit
) {
    Sparkify(
        modifier = modifier,
        onClick = onClick,
        style = style,
        content = {
            Icon(
                modifier = modifier,
                painter = painter,
                tint = tint,
                contentDescription = contentDescription
            )
        },
    )
}

@Composable
fun SparkifyIconButton(
    modifier: Modifier = Modifier,
    imageBitmap: ImageBitmap,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
    style: SparkStyle = SparkifyDefault.sparkStyle(),
    onClick: () -> Unit
) {
    Sparkify(
        modifier = modifier,
        onClick = onClick,
        style = style,
        content = {
            Icon(
                modifier = modifier,
                bitmap = imageBitmap,
                tint = tint,
                contentDescription = contentDescription
            )
        },
    )
}