package com.example.webviewg

import androidx.compose.runtime.Composable


@Composable
expect fun MyWebView(url: String, isLoading: (isLoading: Boolean) -> Unit, onUrlClicked: (url: String) -> Unit)
