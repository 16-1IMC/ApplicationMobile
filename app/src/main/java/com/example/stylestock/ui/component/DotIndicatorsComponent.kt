package com.example.stylestock.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.stylestock.ui.theme.NeonGreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicatorsComponent(
    pageCount: Int,
    pagerState: PagerState,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color(0x4265F543) else NeonGreen
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}