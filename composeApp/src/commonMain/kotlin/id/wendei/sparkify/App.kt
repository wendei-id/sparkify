package id.wendei.sparkify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sparkifylib.composeapp.generated.resources.Res
import sparkifylib.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        SparkifyExample()
    }
}

@Composable
private fun SparkifyExample() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Sparkify(
                style = SparkifyDefault.sparkStyle(
                    sparkSize = 10f,
                    smallDotColor = Color.Red.copy(alpha = 0.8f),
                    bigDotColor = Color.Red
                ),
                onClick = {

                },
                content = {
                    Text(
                        text = "Sparkify!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(48.dp)
            ) {
                SparkifyIconButton(
                    imageVector = Icons.Rounded.Star,
                    tint = Color(0xFFFF9529),
                    style = SparkifyDefault.sparkStyle(
                        smallDotColor = Color(0xFFFF9529),
                        bigDotColor = Color(0xFFFF9529)
                    ),
                    onClick = {

                    }
                )
                SparkifyIconButton(
                    imageVector = Icons.Rounded.ThumbUp,
                    tint = Color.Blue,
                    style = SparkifyDefault.sparkStyle(
                        smallDotColor = Color.Blue,
                        bigDotColor = Color.Blue
                    ),
                    onClick = {

                    }
                )
                SparkifyIconButton(
                    imageVector = Icons.Rounded.Favorite,
                    tint = Color.Red,
                    style = SparkifyDefault.sparkStyle(
                        smallDotColor = Color.Red,
                        bigDotColor = Color.Red
                    ),
                    onClick = {

                    }
                )
            }
            SparkifyIconButton(
                modifier = Modifier
                    .size(48.dp),
                style = SparkifyDefault.sparkStyle(
                    sparkSize = 10f,
                    smallDotColor = Color.Magenta.copy(alpha = 0.5f),
                    bigDotColor = Color.Magenta.copy(alpha = 0.8f)
                ),
                painter = painterResource(Res.drawable.compose_multiplatform),
                onClick = {

                }
            )
        }
    }
}