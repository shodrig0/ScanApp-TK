package com.rodrigov.myapplication.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rodrigov.myapplication.data.model.ProductViewModel
import com.rodrigov.myapplication.ui.screen.configip.ConfigIpScreen
import com.rodrigov.myapplication.ui.screen.home.HomeScreen
import com.rodrigov.myapplication.ui.screen.result.ResultScreen
import com.rodrigov.myapplication.ui.screen.search.ScanProductScreen
import com.rodrigov.myapplication.ui.screen.search.SearchProductScreen

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val productViewModel: ProductViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        modifier = modifier
    ) {
        composable(Routes.Home.route) {
            HomeScreen(
                handleConfigIpClick = {
                    navController.navigate(Routes.ConfigIp.route)
                },
                handleSearchProductClick = {
                    navController.navigate(Routes.SearchProduct.route)
                }
            )
        }

        composable(Routes.ConfigIp.route) {
            ConfigIpScreen(
                onContinue = {
                    navController.navigate(Routes.Home.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ScanBarcode.route) {
            ScanProductScreen(
                onCodeScanned = { codigo ->

                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("scanned_code", codigo)

                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.SearchProduct.route) { backStackEntry ->
            val scannedCode =
                backStackEntry.savedStateHandle.get<String>("scanned_code")

            LaunchedEffect(scannedCode) {
                scannedCode?.let { codigo ->
                    backStackEntry.savedStateHandle.remove<String>("scanned_code")

                    navController.navigate(
                        Routes.Result.createRoute(codigo)
                    )
                }
            }

            SearchProductScreen(
                onSearch = { codigo ->
                    navController.navigate(Routes.Result.createRoute(codigo))
                },
                onScan = {
                    navController.navigate(Routes.ScanBarcode.route)
                },
//                onBack = {
//                    navController.popBackStack()
//                }
            )
        }

        composable(
            route = Routes.Result.route,
            arguments = listOf(navArgument("codigo") { type = NavType.StringType })
        ) { backStackEntry ->
            val codigo = backStackEntry.arguments?.getString("codigo")!!

            ResultScreen(
                codigo = codigo,
                onBack = { navController.popBackStack() }
            )
        }
    }
}