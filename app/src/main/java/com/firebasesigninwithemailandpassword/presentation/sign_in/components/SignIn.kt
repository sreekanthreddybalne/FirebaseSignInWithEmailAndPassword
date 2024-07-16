package com.firebasesigninwithemailandpassword.presentation.sign_in.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.firebasesigninwithemailandpassword.components.ProgressBar
import com.firebasesigninwithemailandpassword.core.Utils.Companion.print
import com.firebasesigninwithemailandpassword.domain.model.Response.*
import com.firebasesigninwithemailandpassword.presentation.sign_in.SignInViewModel

@Composable
fun SignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val signInResponse = viewModel.signInResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showErrorMessage(e.message)
            }
            viewModel.resetSignInResponse()
        }
        else -> {}
    }
}