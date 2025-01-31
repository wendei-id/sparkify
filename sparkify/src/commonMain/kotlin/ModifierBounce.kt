package id.wendei.sparkify

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal fun Modifier.clickableWithBounce(
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) = composed {
    var state by rememberSaveable { mutableStateOf(State.Idle) }
    val scale by animateFloatAsState(
        targetValue = if (state == State.Pressed) 0.30f else 1f,
        label = "BounceEffect"
    )

    val scope = rememberCoroutineScope()

    this
        .scale(scale)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            enabled = enabled,
            onClick = {
                if (state == State.Idle) {
                    scope.launch {
                        state = State.Pressed
                        delay(150L)
                        state = State.Idle
                        onClick?.invoke()
                    }
                }
            }
        )
}
