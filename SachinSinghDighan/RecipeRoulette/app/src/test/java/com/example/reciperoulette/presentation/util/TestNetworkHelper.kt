package com.example.reciperoulette.presentation.util

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class TestNetworkHelper {
    private lateinit var networkHelper: NetworkHelper

    @Before
    fun setUp() {
        networkHelper = Mockito.mock(NetworkHelper::class.java)
    }

    @Test
    fun isConnectedToNetwork() {
        Mockito.`when`(networkHelper.isNetworkAvailable()).thenReturn(false)
        Assert.assertThat(networkHelper.isNetworkAvailable(), CoreMatchers.`is`(false))
    }

    @Test
    fun isConnectedToNetwork_true_trueReturned() {
        Mockito.`when`(networkHelper.isNetworkAvailable()).thenReturn(true)
        Assert.assertThat(networkHelper.isNetworkAvailable(), CoreMatchers.`is`(true))
    }
}