package com.cevdetkilickeser.networkstatemanagement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    networkStatus: Boolean
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Network Status: $networkStatus", fontSize = 24.sp)
    }
}