package com.example.battlefieldv1

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BoardBase(){
    // Board
    val lineColor = MaterialTheme.colors.primary
    Canvas(
        modifier = Modifier
            .size(300.dp)

    ){

        drawLine(
            color = lineColor,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f,0f),
            end = Offset(0f, size.height)
        )
        drawLine(
            color = lineColor,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f,0f),
            end = Offset(size.width, 0f)
        )
        for(i in 1  until 10 ) {
            // Vertical Lines
            drawLine(
                color = lineColor,
                strokeWidth = 10f,
                cap = StrokeCap.Round,
                start = Offset(x = size.width*i/6, y = 0f),
                end = Offset(x = size.width*i/6, y = size.height)
            )
            // Horizontal Lines
            drawLine(
                color = lineColor,
                strokeWidth = 10f,
                cap = StrokeCap.Round,
                start = Offset(x = 0f, y = size.height*i/6),
                end = Offset(x = size.width, y = size.height*i/6)
            )
        }
    }
}

@Composable
fun Miss(){
    val paint = Paint()
    val missColor = MaterialTheme.colors.onPrimary
    Canvas(
        modifier = Modifier
            .size(25.dp)
    ){

        drawLine(
            color = missColor,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, 0f),
            end = Offset(x = size.width, y = size.height)
        )
        drawLine(
            color = missColor,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height),
            end = Offset(x = size.width, y = 0f)
        )
    }
}

@Composable
fun Hit() {

    val hitColor = MaterialTheme.colors.primary
    Canvas (
        modifier = Modifier
            .size(25.dp)
            ){
        drawLine(
            color = hitColor,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height/2),
            end = Offset(size.width, size.height/2)
        )
    }
}

val multiplesOfSixMap = mapOf(
    7 to 'A',
    13 to 'B',
    19 to 'C',
    25 to 'D',
    31 to 'E',
)
@Composable
fun Number(number: Int) {
    val numberColor = MaterialTheme.colors.primary
    Canvas (
        modifier = Modifier
            .size(25.dp),

    ){
        if(number in 2..6) {
            drawContext.canvas.nativeCanvas.drawText(
                "${number - 1}",
                center.x - 30f,
                center.y + 30f,
                android.graphics.Paint().apply {
                    color = numberColor.toArgb()
                    textSize = 90f
                }
            )
        }
        else if (
            number == 7 ||
            number == 13 ||
            number == 19 ||
            number == 25 ||
            number == 31
                ){


            drawContext.canvas.nativeCanvas.drawText(
                "${multiplesOfSixMap[number]}",
                center.x - 30f,
                center.y + 30f,
                android.graphics.Paint().apply {
                    color = numberColor.toArgb()
                    textSize = 90f
                },

            )
        }
        }



    }

@Preview
@Composable
fun Previews() {
    BoardBase()
}

@Preview
@Composable
fun HitMiss(){
    Column(){
        Hit()
        Miss()
    }
}