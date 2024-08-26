package com.example.reciperoulette.presentation.viewmodel

import app.cash.turbine.test
import com.example.reciperoulette.domain.model.Recipe
import com.example.reciperoulette.domain.model.RecipesContent
import com.example.reciperoulette.domain.usecase.RecipeListFromApiUseCase
import com.example.reciperoulette.presentation.ui.base.UiState
import com.example.reciperoulette.presentation.util.AppConstant
import com.example.reciperoulette.presentation.util.DispatcherProvider
import com.example.reciperoulette.presentation.util.NetworkHelper
import com.example.reciperoulette.presentation.util.TestDispatcherProvider
import com.example.reciperoulette.presentation.viewModel.recipelist.RecipeListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RecipeListViewModelTest {

    @Mock
    private lateinit var recipeListFromApiUseCase: RecipeListFromApiUseCase

    @Mock
    private lateinit var networkHelper: NetworkHelper

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp(){
        dispatcherProvider = TestDispatcherProvider()
        given(networkHelper.isNetworkAvailable()).willReturn(true)
    }
    /*
    * TODO: Needs to add test cases*/
}