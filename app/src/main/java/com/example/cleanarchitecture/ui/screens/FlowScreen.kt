package com.example.cleanarchitecture.ui.screens

import android.widget.GridLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cleanarchitecture.data.local.DeveloperTable
import com.example.cleanarchitecture.ui.viewmodels.ColdFlowViewModel

@Composable
fun FlowScreen(onNavigateToColdUi: (developers : List<DeveloperTable>) -> Unit){
    val viewModel : ColdFlowViewModel = hiltViewModel()
    val developers by viewModel.devs.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.Center) {
            Button(onClick = {onNavigateToColdUi(developers)}) {
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



