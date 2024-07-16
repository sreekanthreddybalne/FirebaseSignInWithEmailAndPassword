package com.firebasesigninwithemailandpassword.presentation.sign_up.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.firebasesigninwithemailandpassword.components.EmailField
import com.firebasesigninwithemailandpassword.components.PasswordField
import com.firebasesigninwithemailandpassword.components.SmallSpacer
import com.firebasesigninwithemailandpassword.components.StyledButton
import com.firebasesigninwithemailandpassword.core.Constants.ALREADY_USER
import com.firebasesigninwithemailandpassword.core.Constants.EMPTY_STRING
import com.firebasesigninwithemailandpassword.core.Constants.RETYPE_PASSWORD_LABEL
import com.firebasesigninwithemailandpassword.core.Constants.SIGN_UP_BUTTON
import com.firebasesigninwithemailandpassword.presentation.sign_up.SignUpViewModel

@Composable
@ExperimentalComposeUiApi
fun SignUpContent(
    padding: PaddingValues,
    viewModel: SignUpViewModel = hiltViewModel(),
    signUp: (email: String, password: String) -> Unit,
    navigateBack: () -> Unit
) {
    var email by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    var repassword by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            }
        )
        SmallSpacer()
        PasswordField(
            password = password,
            onPasswordValueChange = { newValue ->
                password = newValue
            }
        )
        SmallSpacer()
        PasswordField(
            password = repassword,
            onPasswordValueChange = { newValue ->
                repassword = newValue
            },
            label = RETYPE_PASSWORD_LABEL
        )
        SmallSpacer()
        StyledButton(
            label= SIGN_UP_BUTTON,
            onClick = {
                keyboard?.hide()
                signUp(email.text, password.text)
            },
            enabled = password.text.isNotEmpty() && password.text == repassword.text && email.text.matches(Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+\$")),
            isLoading = viewModel.signUpResponse!=null
        )
        Text(
            modifier = Modifier.clickable {
                navigateBack()
            },
            text = ALREADY_USER,
            fontSize = 15.sp
        )
    }
}