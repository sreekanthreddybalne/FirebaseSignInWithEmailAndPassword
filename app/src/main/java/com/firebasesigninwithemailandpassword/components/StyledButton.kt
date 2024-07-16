package com.firebasesigninwithemailandpassword.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebasesigninwithemailandpassword.core.Constants

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    label: String = Constants.SUBMIT_BUTTON,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit,
){
    Button(
        modifier= modifier
//            .fillMaxWidth()
            .height(40.dp),
//        colors= ButtonDefaults.buttonColors(
//            containerColor = MaterialTheme.colorScheme.primary,
//            contentColor = MaterialTheme.colorScheme.secondary
//        ),
        shape= RoundedCornerShape(size=0.dp),
        onClick = {onClick()},
        enabled = !isLoading && enabled
    ){
        if(isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                strokeWidth = 2.dp)
        }else{
            Text(
                text = label,
                fontSize = 15.sp
            )
        }

    }
}
