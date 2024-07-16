package com.firebasesigninwithemailandpassword.presentation.home.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.firebasesigninwithemailandpassword.components.ProgressBar
import com.firebasesigninwithemailandpassword.core.Constants.ACCESS_REVOKED_MESSAGE
import com.firebasesigninwithemailandpassword.core.Constants.REVOKE_ACCESS_MESSAGE
import com.firebasesigninwithemailandpassword.core.Constants.SENSITIVE_OPERATION_MESSAGE
import com.firebasesigninwithemailandpassword.core.Constants.SIGN_OUT_ITEM
import com.firebasesigninwithemailandpassword.core.Utils.Companion.print
import com.firebasesigninwithemailandpassword.core.Utils.Companion.showMessage
import com.firebasesigninwithemailandpassword.domain.model.Response.*
import com.firebasesigninwithemailandpassword.presentation.home.HomeViewModel

@Composable
fun RevokeAccess(
    viewModel: HomeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    signOut: () -> Unit,
) {
    val context = LocalContext.current

    fun showRevokeAccessMessage() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT_ITEM
        )
        if (result == SnackbarResult.ActionPerformed) {
            signOut()
        }
    }

    when(val revokeAccessResponse = viewModel.revokeAccessResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isAccessRevoked = revokeAccessResponse.data
            LaunchedEffect(isAccessRevoked) {
                if (isAccessRevoked) {
                    showMessage(context, ACCESS_REVOKED_MESSAGE)
                }
            }
        }
        is Failure -> revokeAccessResponse.apply {
            LaunchedEffect(e) {
                print(e)
                if (e.message == SENSITIVE_OPERATION_MESSAGE) {
                    showRevokeAccessMessage()
                }
            }
        }
    }
}