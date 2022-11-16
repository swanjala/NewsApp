package com.example.newsapp.utils

import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

class UnconfirmedTestDispatcherExtension : BeforeEachCallback, AfterEachCallback {
    private val testScheduler = TestCoroutineScheduler()
    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(UnconfinedTestDispatcher(testScheduler))
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}
