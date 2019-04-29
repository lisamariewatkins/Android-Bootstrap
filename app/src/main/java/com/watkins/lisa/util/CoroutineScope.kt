package com.watkins.lisa.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

/**
 * @author Lisa Watkins
 *
 * Provides [Dispatchers.Default] Coroutine scope, which is backed by a shared pool of background threads limited by number
 * of CPU cores. This scope is recommended for use with CPU intensive tasks or networking.
 */
interface WithDefaultCoroutineScope: CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    fun cancelAllChildren() {
        coroutineContext.cancelChildren()
    }
}