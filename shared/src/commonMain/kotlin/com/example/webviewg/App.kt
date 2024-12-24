package com.example.webviewg

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun App() {

    var showLoading by remember { mutableStateOf(false) }

    MyWebView("https://www.seeme-app.com/", isLoading = {
            isLoading ->
        showLoading = isLoading
    }, onUrlClicked = {

    })
    if(showLoading){
        Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}