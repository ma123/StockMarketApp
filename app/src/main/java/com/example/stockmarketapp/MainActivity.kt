package com.example.stockmarketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stockmarketapp.presentation.company_info.CompanyInfoScreen
import com.example.stockmarketapp.presentation.company_listings.CompanyListingsScreen
import com.example.stockmarketapp.ui.theme.StockMarketAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "company_list_screen") {
                        composable("company_list_screen") {
                            CompanyListingsScreen(navController = navController)
                        }

                        composable("company_info_screen/{symbol}",
                            arguments = listOf(
                                navArgument("symbol") {
                                    type = NavType.StringType
                                }
                            )) {

                            val symbol = remember {
                                it.arguments?.getString("symbol")
                            }

                            CompanyInfoScreen(
                                navController = navController,
                                symbol = symbol!!
                            )
                        }
                    }
                }
            }
        }
    }
}