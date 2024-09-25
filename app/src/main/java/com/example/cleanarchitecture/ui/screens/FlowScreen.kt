package com.example.cleanarchitecture.ui.screens

import android.widget.GridLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun FlowScreen(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.Center) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Cold Flow")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Hot Flow")
            }
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "State Flow")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Shared Flow")
            }
        }
    }
}