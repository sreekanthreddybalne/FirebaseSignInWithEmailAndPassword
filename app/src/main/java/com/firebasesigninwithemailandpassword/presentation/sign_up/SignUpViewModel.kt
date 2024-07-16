package com.firebasesigninwithemailandpassword.presentation.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.firebasesigninwithemailandpassword.domain.model.Response.Loading
import com.firebasesigninwithemailandpassword.domain.model.Response.Success
import com.firebasesigninwithemailandpassword.domain.repository.AuthRepository
import com.firebasesigninwithemailandpassword.domain.repository.SendEmailVerificationResponse
import com.firebasesigninwithemailandpassword.domain.repository.SignUpResponse
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var signUpResponse by mutableStateOf<SignUpResponse?>(null)
        private set
    var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponse>(Success(false))
        private set

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signUpResponse = Loading
        signUpResponse = repo.firebaseSignUpWithEmailAndPassword(email, password)
    }

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResponse = Loading
        sendEmailVerificationResponse = repo.sendEmailVerification()
    }

    fun resetSignUpResponse(){
        signUpResponse = null
    }
}