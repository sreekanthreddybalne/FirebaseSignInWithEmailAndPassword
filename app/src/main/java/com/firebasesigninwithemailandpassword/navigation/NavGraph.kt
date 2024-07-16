package com.firebasesigninwithemailandpassword.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.firebasesigninwithemailandpassword.navigation.Screen.ForgotPasswordScreen
import com.firebasesigninwithemailandpassword.navigation.Screen.HomeScreen
import com.firebasesigninwithemailandpassword.navigation.Screen.SignInScreen
import com.firebasesigninwithemailandpassword.navigation.Screen.SignUpScreen
import com.firebasesigninwithemailandpassword.navigation.Screen.VerifyEmailScreen
import com.firebasesigninwithemailandpassword.presentation.forgot_password.ForgotPasswordScreen
import com.firebasesigninwithemailandpassword.presentation.home.HomeScreen
import com.firebasesigninwithemailandpassword.presentation.sign_in.SignInScreen
import com.firebasesigninwithemailandpassword.presentation.sign_up.SignUpScreen
import com.firebasesigninwithemailandpassword.presentation.verify_email.VerifyEmailScreen

@Composable
@ExperimentalComposeUiApi
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SignInScreen.route
    ) {
        composable(
            route = SignInScreen.route
        ) {
            SignInScreen(
                navigateToForgotPasswordScreen = {
                    navController.navigate(ForgotPasswordScreen.route)
                },
                navigateToSignUpScreen = {
                    navController.navigate(SignUpScreen.route)
                }
            )
        }
        composable(
            route = ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = SignUpScreen.route
        ) {
            SignUpScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = VerifyEmailScreen.route
        ) {
            VerifyEmailScreen(
                navigateToHomeScreen = {
                    navController.navigate(HomeScreen.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = HomeScreen.route
        ) {
            HomeScreen()
        }
    }
}