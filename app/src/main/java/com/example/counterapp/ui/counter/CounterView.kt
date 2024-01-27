package com.example.counterapp.ui.counter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                fontSize = 120.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()



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
                shape = RoundedCornerShape(5.dp)

            ) {
                Text(text = "Add")

            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { counter.value -=1},
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors()
            ) {
                Text(text = "DeAdd")

            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { counter.value =0},
            shape = RoundedCornerShape(5.dp)) {

            Text(text = "Reset")

        }



    }
}





@Preview
@Composable
fun PreviewCounter() {
}