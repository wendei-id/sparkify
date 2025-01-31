package id.wendei.sparkify

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.PI

private const val DOTS_COUNT = 8
private const val OUTER_DOTS_POSITION_ANGLE = 360 / DOTS_COUNT

private fun DrawScope.drawDots(
    centerX: Float,
    centerY: Float,
    animatableValue: Float,
    sparkColor: Color,
    outerDotRadius: Float,
    dotRadius: Float,
    offsetAngle: Float = 0f
) {
    repeat(DOTS_COUNT) { i ->
        val angle = (i * OUTER_DOTS_POSITION_ANGLE + offsetAngle) * PI / 180
        val cX = (centerX + animatableValue * cos(angle)).toFloat()
        val cY = (centerY + animatableValue * sin(angle)).toFloat()
        drawCircle(
            color = sparkColor,
            radius = outerDotRadius.times(dotRadius),
            center = Offset(cX, cY)
        )
    }
}

@Composable
fun Sparkify(
    modifier: Modifier = Modifier,
    style: SparkStyle = SparkifyDefault.sparkStyle(),
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val animationTime = 500
    val animationScope = rememberCoroutineScope()
    var isAnimating by remember { mutableStateOf(false) }
    val bigSparkAnimatable = remember { Animatable(1f) }
    val smallSparkAnimatable = remember { Animatable(1f) }
    val circleRadiusAnimatable = remember { Animatable(0f) }

    var circleRadiusRange = 0f

    LaunchedEffect(isAnimating) {
        if (isAnimating) {
            delay(animationTime.toLong())
            isAnimating = false
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.drawWithContent {
            val centerX = size.width / 2f
            val centerY = size.height / 2f

            val maxOuterDotsRadius = 1f * style.sparkSize
            val maxInnerDotsRadius = 0.7f * maxOuterDotsRadius

            circleRadiusRange = centerX.coerceAtMost(centerY).plus(size.width)

            listOf(
                Triple(bigSparkAnimatable.value, style.bigDotColor, maxOuterDotsRadius),
                Triple(smallSparkAnimatable.value, style.smallDotColor, maxInnerDotsRadius),
            ).forEachIndexed { index, it ->
                drawDots(
                    centerX = centerX,
                    centerY = centerY,
                    animatableValue = it.first,
                    sparkColor = it.second,
                    outerDotRadius = it.third,
                    dotRadius = circleRadiusAnimatable.value,
                    offsetAngle = if (index == 0) 0f else -20f
                )
            }

            drawContent()
        },
        content = {
            Box(
                modifier
                    .clickableWithBounce(enabled = isAnimating.not()) {
                        isAnimating = true
                        val circleRadiusAnimationJob = animationScope.async {
                            circleRadiusAnimatable.animateTo(
                                targetValue = 0f,
                                animationSpec = keyframes {
                                    durationMillis = animationTime
                                    1f at (animationTime * 0.4).toInt() using LinearOutSlowInEasing
                                    1f at (animationTime * 0.8).toInt() using LinearOutSlowInEasing
                                    0f at animationTime using LinearOutSlowInEasing
                                }
                            )
                        }
                        val bigCircleAnimationJob = animationScope.async {
                            bigSparkAnimatable.animateTo(
                                targetValue = circleRadiusRange,
                                animationSpec = tween(
                                    durationMillis = animationTime,
                                    easing = LinearOutSlowInEasing
                                )
                            )
                        }
                        val smallCircleAnimationJob = animationScope.async {
                            smallSparkAnimatable.animateTo(
                                targetValue = circleRadiusRange,
                                animationSpec = tween(
                                    durationMillis = animationTime,
                                    easing = LinearOutSlowInEasing
                                )
                            )
                        }
                        val jobs = animationScope.launch {
                            joinAll(
                                bigCircleAnimationJob,
                                smallCircleAnimationJob,
                                circleRadiusAnimationJob
                            )
                        }
                        jobs.invokeOnCompletion {
                            animationScope.launch {
                                bigSparkAnimatable.snapTo(targetValue = 0f)
                                smallSparkAnimatable.snapTo(targetValue = 0f)
                                circleRadiusAnimatable.snapTo(targetValue = 0f)
                                onClick()
                            }
                        }
                    },
                content = {
                    content.invoke()
                }
            )
        }
    )
}