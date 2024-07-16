package com.firebasesigninwithemailandpassword.presentation.sign_in

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
import com.firebasesigninwithemailandpassword.domain.repository.SignInResponse
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var signInResponse by mutableStateOf<SignInResponse?>(null)
        private set

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signInResponse = Loading
        signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)
    }

    fun resetSignInResponse(){
        signInResponse = null
    }
}