package com.example.cleanarchitecture.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cleanarchitecture.data.local.DeveloperTable

@Composable
fun ColdFlowUi(developers: List<DeveloperTable>){
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyColumn {
                items(developers) {
                    DevView(it)
                }
            }
        }
    }
}

@Composable
fun DevView(dev: DeveloperTable){

}
