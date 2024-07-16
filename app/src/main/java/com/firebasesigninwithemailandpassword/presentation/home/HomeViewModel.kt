package com.firebasesigninwithemailandpassword.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.firebasesigninwithemailandpassword.domain.model.Response.Loading
import com.firebasesigninwithemailandpassword.domain.model.Response.Success
import com.firebasesigninwithemailandpassword.domain.repository.AuthRepository
import com.firebasesigninwithemailandpassword.domain.repository.ReloadUserResponse
import com.firebasesigninwithemailandpassword.domain.repository.RevokeAccessResponse
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var chatStore = mutableStateListOf<String>()
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
        private set
    var reloadUserResponse by mutableStateOf<ReloadUserResponse>(Success(false))
        private set

    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Loading
        reloadUserResponse = repo.reloadFirebaseUser()
    }

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    fun signOut() = repo.signOut()

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Loading
        revokeAccessResponse = repo.revokeAccess()
    }

    fun addToChatStore(message: String) {
        var ourMessage = "$message $message"
        var lowercased = message.lowercase()
        if(lowercased.contains("hi")){
            ourMessage = "Hello there."
        }else if(lowercased.contains("how are you")){
            ourMessage = "I am doing good. How about you?"
        }
        chatStore.add(message)
        chatStore.add(ourMessage)
    }
}