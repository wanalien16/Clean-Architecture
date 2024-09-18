package com.example.cleanarchitecture.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.cleanarchitecture.ui.viewmodels.OnBoardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun OnBoardingScreen(viewModel: OnBoardingViewModel, navController: NavController) {

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome to the App")
        Spacer(modifier = Modifier.padding(20.dp))
        Button(onClick = { viewModel.completeOnBoarding()
        navController.navigate("main"){
            popUpTo("onboarding"){inclusive = true}
        }}) {
            
            Text(text = "Finish OnBoarding")

        }

    }
}