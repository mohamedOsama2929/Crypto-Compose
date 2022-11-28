package com.vivus.crypto.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun LifeEventListener(OnEvent: (event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(newValue = OnEvent)
    val lifeCycleOwner = rememberUpdatedState(newValue = LocalLifecycleOwner.current)
    DisposableEffect(lifeCycleOwner.value) {

        val lifecycle = lifeCycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            eventHandler.value(event)
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}