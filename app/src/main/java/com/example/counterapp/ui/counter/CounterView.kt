package com.example.counterapp.ui.counter

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counterapp.ui.theme.Redberry
import com.example.counterapp.ui.theme.lavender
import com.example.counterapp.ui.theme.nude
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
@Composable
fun CustomCircularProgressIndicator(
    modifier: Modifier = Modifier,
    initialValue:Int,
    primaryColor: Color,
    secondaryColor:Color,
    minValue:Int = 0,
    maxValue:Int = 100,
    circleRadius:Float,
    onPositionChange:(Int)->Unit
) {

    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var positionValue by remember {
        mutableStateOf(initialValue)
    }



    Box(
        modifier = modifier
    ){
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ){
            val width = size.width
            val height = size.height
            val circleThickness = width / 25f
            circleCenter = Offset(x = width/2f, y = height/2f)


            drawCircle(
                brush = Brush.radialGradient(
                    listOf(
                        primaryColor.copy(0.45f),
                        secondaryColor.copy(0.15f)
                    )
                ),
                radius = circleRadius,
                center = circleCenter
            )


            drawCircle(
                style = Stroke(
                    width = circleThickness
                ),
                color = Color.White,
                radius = circleRadius,
                center = circleCenter
            )

            drawArc(
                color = Redberry,
                startAngle = 90f,
                sweepAngle = (360f/maxValue) * positionValue.toFloat(),
                style = Stroke(
                    width = circleThickness,
                    cap = StrokeCap.Round
                ),
                useCenter = false,
                size = Size(
                    width = circleRadius * 2f,
                    height = circleRadius * 2f
                ),
                topLeft = Offset(
                    (width - circleRadius * 2f)/2f,
                    (height - circleRadius * 2f)/2f
                )

            )

            val outerRadius = circleRadius + circleThickness/2f
            val gap = 15f
            for (i in 0 .. (maxValue-minValue)){
                val color = if(i < positionValue-minValue) primaryColor else primaryColor.copy(alpha = 0.3f)
                val angleInDegrees = i*360f/(maxValue-minValue).toFloat()
                val angleInRad = angleInDegrees * PI / 180f + PI /2f

                val yGapAdjustment = cos(angleInDegrees * PI / 180f) *gap
                val xGapAdjustment = -sin(angleInDegrees * PI / 180f) *gap

                val start = Offset(
                    x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
                    y = (outerRadius * sin(angleInRad) + circleCenter.y + yGapAdjustment).toFloat()
                )

                val end = Offset(
                    x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
                    y = (outerRadius * sin(angleInRad) + circleThickness + circleCenter.y + yGapAdjustment).toFloat()
                )

                rotate(
                    angleInDegrees,
                    pivot = start
                ){
                    drawLine(
                        color = color,
                        start = start,
                        end = end,
                        strokeWidth = 1.dp.toPx()
                    )
                }

            }

            drawContext.canvas.nativeCanvas.apply {
                drawIntoCanvas {
                    drawText(
                        "",
                        circleCenter.x,
                        circleCenter.y + 45.dp.toPx()/3f,
                        Paint().apply {
                            textSize = 38.sp.toPx()
                            textAlign = Paint.Align.CENTER
                            isFakeBoldText = true
                        }
                    )
                }
            }

        }
    }
}
@Composable
    fun CounterView(){


        val counter = remember {
            mutableStateOf(0)
        }

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){


        Spacer(modifier = Modifier.height(16.dp))

        Box (modifier = Modifier
            .weight(1f),
            contentAlignment = Alignment.Center

        ){

            Text(
                text = counter.value.toString(),
                fontSize = 100.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 90.dp)




            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        )

        {
            Button(onClick = { counter.value +=1 },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),

            ) {
                Text(text = "Plant A Tree",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp,


                    )

            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { counter.value -=1},
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),
            ) {
                Text(text = "Cut A Tree",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp,

                    )

            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { counter.value =0},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            shape = RoundedCornerShape(5.dp)) {

            Text(text = "Reset",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp,

                )

        }



    }
}





@Preview
@Composable
fun PreviewCounter() {
}