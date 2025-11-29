package com.example.newlearnings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newlearnings.ui.theme.NewLearningsTheme
import com.example.presentation.newsScreens.DetailsScreen
import com.example.presentation.newsScreens.Home
import com.example.presentation.newsScreens.details.DetailsScreenViewModel
import com.example.presentation.newsScreens.home.HomeScreenViewModel
import com.example.search_presentation.Screens.screens.SearchScreen
import com.example.search_presentation.Screens.screens.search.SearchScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val homeScreenViewModel by viewModel<HomeScreenViewModel>()
        enableEdgeToEdge()
        setContent {
            NewLearningsTheme {
               val navController = rememberNavController()
                NavHost(navController = navController , startDestination = Home){
                    composable<Home> {
                        val viewModel = koinViewModel<HomeScreenViewModel>()
                        com.example.presentation.newsScreens.home.HomeScreen(viewModel , {
                            navController.navigate(SearchScreen)
                        }){ id ->
                            navController.navigate(DetailsScreen(id))
                        }
                    }
                    composable<DetailsScreen> {
                        val viewModel = koinViewModel<DetailsScreenViewModel>()
                        val args = it.toRoute<DetailsScreen>()
                        com.example.presentation.newsScreens.details.DetailsScreen(id = args.id , viewModel =viewModel ) {
                            navController.popBackStack()
                        }
                    }
                   composable<SearchScreen> {
                       val viewModel = koinViewModel<SearchScreenViewModel>()
                       com.example.search_presentation.Screens.screens.search.SearchScreen(viewmodel = viewModel , {
                           navController.popBackStack()
                       }) { id ->
                           navController.navigate(DetailsScreen(id))
                       }
                   }
                }
            }
        }
    }
}
