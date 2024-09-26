package com.example.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecture.data.remote.ApiService
import com.example.cleanarchitecture.ui.screens.ColdFlowUi
import com.example.cleanarchitecture.ui.screens.DevelopersScreen
import com.example.cleanarchitecture.ui.screens.FlowScreen
import com.example.cleanarchitecture.ui.screens.OnBoardingScreen
import com.example.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.example.cleanarchitecture.ui.viewmodels.DeveloperViewModel
import com.example.cleanarchitecture.ui.viewmodels.OnBoardingViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
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

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "onboarding") {
                        composable("onboarding") {
                            val viewModel: OnBoardingViewModel = hiltViewModel()
                            OnBoardingScreen(viewModel = viewModel, navController = navController)
                        }
                        composable("main") {
                            DevelopersScreen(onNavigateToFlowScreen = {navController.navigate(route = "flow")})
                        }
                        composable("flow"){
                            FlowScreen(onNavigateToColdUi = {navController.navigate(route = "cold")})
                        }
                        composable("cold"){
//                            ColdFlowUi()
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