package com.firebasesigninwithemailandpassword.presentation.verify_email

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.firebasesigninwithemailandpassword.components.TopBar
import com.firebasesigninwithemailandpassword.core.Constants.EMAIL_NOT_VERIFIED_MESSAGE
import com.firebasesigninwithemailandpassword.core.Constants.VERIFY_EMAIL_SCREEN
import com.firebasesigninwithemailandpassword.core.Utils.Companion.showMessage
import com.firebasesigninwithemailandpassword.presentation.home.HomeViewModel
import com.firebasesigninwithemailandpassword.presentation.home.components.RevokeAccess
import com.firebasesigninwithemailandpassword.presentation.verify_email.components.ReloadUser
import com.firebasesigninwithemailandpassword.presentation.verify_email.components.VerifyEmailContent

@Composable
fun VerifyEmailScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                title = VERIFY_EMAIL_SCREEN,
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            VerifyEmailContent(
                padding = padding,
                reloadUser = {
                    viewModel.reloadUser()
                }
            )
        },
        scaffoldState = scaffoldState
    )

    ReloadUser(
        navigateToHomeScreen = {
            if (viewModel.isEmailVerified) {
                navigateToHomeScreen()
            } else {
                showMessage(context, EMAIL_NOT_VERIFIED_MESSAGE)
            }
        }
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}