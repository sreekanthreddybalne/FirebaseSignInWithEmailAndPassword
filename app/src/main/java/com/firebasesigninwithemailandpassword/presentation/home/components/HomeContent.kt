package com.firebasesigninwithemailandpassword.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.firebasesigninwithemailandpassword.components.SmallSpacer
import com.firebasesigninwithemailandpassword.components.StyledButton
import com.firebasesigninwithemailandpassword.core.Constants
import com.firebasesigninwithemailandpassword.core.Constants.WELCOME_MESSAGE
import com.firebasesigninwithemailandpassword.presentation.home.HomeViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeContent(
    padding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var message by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = WELCOME_MESSAGE,
            fontSize = 24.sp
        )
        SmallSpacer()

        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            verticalArrangement = Arrangement.Top
        ) {
            viewModel.chatStore?.let { chatList ->
                itemsIndexed(chatList) { index, message ->
                    var owner = "You"
                    if(index%2==1)owner = "Bot"
                    Text(text = "$owner : $message")
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ){
            Row(
                modifier=Modifier.padding(15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    modifier = Modifier.padding(15.dp),
                    value = message,
                    onValueChange = { message = it },
                    label = { Text("Type message here...") }
                )
                SmallSpacer()
                StyledButton(
                    label= "Send",
                    onClick = {
                        keyboard?.hide()
                        viewModel.addToChatStore(message.text)
                        message = TextFieldValue(text = Constants.EMPTY_STRING)
                    },
                    enabled = message.text.isNotEmpty(),
                )
            }
        }
    }
}