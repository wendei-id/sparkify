package id.wendei.sparkify

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class SparkStyle(
    val sparkSize: Float,
    val bigDotColor: Color,
    val smallDotColor: Color,
)

object SparkifyDefault {

    @Composable
    fun sparkStyle(
        sparkSize: Float = 5f,
        bigDotColor: Color = LocalContentColor.current,
        smallDotColor: Color = LocalContentColor.current,
    ): SparkStyle = SparkStyle(
        sparkSize = sparkSize,
        bigDotColor = bigDotColor,
        smallDotColor = smallDotColor
    )

}