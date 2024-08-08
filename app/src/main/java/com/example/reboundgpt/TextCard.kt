package com.example.reboundgpt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextCardRight(text: String, options:Boolean, viewModel: viewModel){
    val context = LocalContext.current
    Row {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp, 10.dp, 0.dp, 10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Box(
                        modifier = Modifier.background(
                            color = Color.White,
                            shape = RoundedCornerShape(50)
                        )
                    ) {
                        Text(
                            text = "RP",
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                if(options) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth().padding(0.dp,0.dp,16.dp,0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { viewModel.copyButton(text, context) }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "copy",
                                tint = Color.White
                            )
                        }
                        Text(text = "Copy", fontSize = 15.sp)
                        IconButton(onClick = { viewModel.retryButton() }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "retry",
                                tint = Color.White
                            )
                        }
                        Text(text = "Retry", fontSize = 15.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun TextCardLeft(text: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 25.dp, 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Text(text = text, color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp))
    }
}