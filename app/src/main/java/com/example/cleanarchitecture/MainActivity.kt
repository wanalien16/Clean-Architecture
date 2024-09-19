package com.example.cleanarchitecture

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecture.data.remote.ApiService
import com.example.cleanarchitecture.ui.screens.DevelopersScreen
import com.example.cleanarchitecture.ui.screens.OnBoardingScreen
import com.example.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.example.cleanarchitecture.ui.viewmodels.DeveloperViewModel
import com.example.cleanarchitecture.ui.viewmodels.OnBoardingViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            CleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Timber.tag("ActivityStatus").i("Timber logging is ready");
                    val navController = rememberNavController()
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    var isOnBoardingFinished by remember {
                        mutableStateOf(false)
                    }
                    val onBoardingStatus by viewModel.isOnBoardingFinished

                    Timber.i("intial status", "$isOnBoardingFinished")

                    NavHost(navController = navController, startDestination = if (onBoardingStatus) "main" else "onboarding")  {
                        composable("onboarding") {

                            OnBoardingScreen(viewModel = viewModel, navController = navController)
                        }
                        composable("main") {

                            DevelopersScreen()
                            Timber.i("After status", "$isOnBoardingFinished")
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CleanArchitectureTheme {
        Greeting("Android")
    }
}