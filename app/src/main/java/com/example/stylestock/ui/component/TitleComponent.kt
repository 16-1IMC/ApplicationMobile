package com.example.stylestock.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.Jura
import com.example.stylestock.ui.theme.NeonBlue

@Composable
fun TitleComponent(text: String){
    Box(modifier = Modifier
        .padding(start = 33.dp),

    ){
        Text(
            text = text,
        color = Jet,
        fontSize = 30.sp,
            fontFamily = Jura,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .height(46.dp)
                .drawBehind {
                    drawLine(
                        color = NeonBlue,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 15f,
                        cap = StrokeCap.Square
                    )
                }
        )
    }

}