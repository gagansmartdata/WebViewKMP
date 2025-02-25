package com.example.webviewg

import android.graphics.Bitmap
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun MyWebView(
    url: String,
    isLoading: (isLoading: Boolean) -> Unit,
    onUrlClicked: (url: String) -> Unit
) {
    var isLoadingFinished by remember { mutableStateOf(false) }

    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = {
                WebView(context).apply {
                    scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
                    setBackgroundColor(Color.TRANSPARENT)
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    // Enable horizontal scrolling
                    settings.apply {
                        loadWithOverviewMode = true
                        useWideViewPort = true
                        layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
                    }

                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(
                            view: WebView?,
                            url: String?,
                            favicon: Bitmap?
                        ) {
                            isLoading(true)
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            view?.scrollTo(view.contentHeight, 0)
                            isLoadingFinished = true
                            isLoading(false)
                        }
                    }
                }
            },
            update = { webView ->
                webView.loadUrl(url)
            }
        )
    }
}