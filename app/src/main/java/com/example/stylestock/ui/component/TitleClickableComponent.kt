package com.example.stylestock.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.Jura
import com.example.stylestock.ui.theme.NeonBlue


@Composable
fun TitleClickableComponent(text: String, withBar: Boolean, onClick: () -> Unit){
    ClickableText(
        onClick = {onClick()},
        text =  AnnotatedString(text = text),
        style= TextStyle(
            color = Jet,
            fontSize = 30.sp,
            fontFamily = Jura,
            fontWeight = FontWeight.Bold,
        ),
        modifier = Modifier
            .padding(start = 33.dp)
            .height(46.dp)
            .drawBehind {
                if (withBar) {
                    drawLine(
                        color = NeonBlue,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 6f,
                        cap = StrokeCap.Round
                    )
                }
            }
    )

}