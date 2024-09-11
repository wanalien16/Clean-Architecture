package com.example.cleanarchitecture.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.cleanarchitecture.domain.model.Developers
import com.example.cleanarchitecture.ui.viewmodels.DeveloperViewModel

@Composable
fun DevelopersScreen(){
val viewModel: DeveloperViewModel = hiltViewModel()
val developers by viewModel.data.collectAsState()

Box(modifier = Modifier.fillMaxSize()){
    LazyColumn {
        items(developers){developer -> CardView(developer)
        }
    }
}
}

@Composable
fun CardView(developer: Developers){
Card(modifier = Modifier.fillMaxWidth()) {
Row {
    Image(painter = rememberAsyncImagePainter(developer.avatar ), contentDescription = "Developer Image",
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .padding(5.dp)
            .background(MaterialTheme.colorScheme.surface))

    Spacer(modifier = Modifier.padding(20.dp))

    Column(modifier = Modifier.padding(5.dp)) {

//        Text(text = developer.name)
        
        Text(text = developer.username)
        
        Text(text = developer.url)


    }

}
}
}