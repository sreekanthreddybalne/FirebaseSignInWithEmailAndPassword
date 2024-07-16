package com.firebasesigninwithemailandpassword.presentation.home

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.firebasesigninwithemailandpassword.components.TopBar
import com.firebasesigninwithemailandpassword.core.Constants.HOME_SCREEN
import com.firebasesigninwithemailandpassword.presentation.home.components.HomeContent
import com.firebasesigninwithemailandpassword.presentation.home.components.RevokeAccess

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBar(
                title = HOME_SCREEN,
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            HomeContent(
                padding = padding
            )
        },
        scaffoldState = scaffoldState
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}