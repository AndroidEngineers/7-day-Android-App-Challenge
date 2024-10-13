package com.mani.quotify007.ui.navigation.viewmodel

import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.model.QuoteResult
import com.mani.quotify007.domain.usecase.GetQuoteUseCase
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.navigation.model.ResponseResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private lateinit var useCase: GetQuoteUseCase

    private val viewModel: MainViewModel by lazy {
        MainViewModel(useCase)
    }

    @Before
    fun setUp() {
        useCase = mockk()
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadQuotesData_should_update_state_with_quotes_on_success() = runTest {
        val quotes = listOf(Quote(id = "1", content = "Test Quote", author = "Author"))
        val quoteResult = QuoteResult(results = quotes)
        coEvery { useCase.dbQuotes() } returns flowOf(quotes)
        coEvery { useCase.result() } returns ResponseResult.Success(quoteResult)

        viewModel.loadQuotesData()
        advanceUntilIdle()

        assertEquals(quotes, viewModel.state.value.quotes)
        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals("Test Quote", viewModel.state.value.quotes[0].content)
    }

    @Test
    fun loadQuotesData_should_emit_error_event_on_failure() = runTest{
        val exception = Exception("Test Exception")
        coEvery { useCase.dbQuotes() } returns flowOf(emptyList())
        coEvery { useCase.result() } returns ResponseResult.Error(exception)

        viewModel.loadQuotesData()
        advanceUntilIdle()

        assertEquals(emptyList(), viewModel.state.value.quotes)
        assertEquals(false, viewModel.state.value.isLoading)

    }

    @Test
    fun `onEvent CopyText should emit UiActionEvent CopyText`() = runTest(UnconfinedTestDispatcher()) {
        val quote = Quote(id = "1", content = "Test Quote", author = "Author")

        val quotes = listOf(Quote(id = "1", content = "Test Quote", author = "Author"))
        val quoteResult = QuoteResult(results = quotes)
        coEvery { useCase.dbQuotes() } returns flowOf(quotes)
        coEvery { useCase.result() } returns ResponseResult.Success(quoteResult)

        viewModel.onEvent(MainEvent.CopyText(quote))
        viewModel.onEvent(MainEvent.ShareClick(quote))
        viewModel.onEvent(MainEvent.ShowToast("quote"))
    }

}