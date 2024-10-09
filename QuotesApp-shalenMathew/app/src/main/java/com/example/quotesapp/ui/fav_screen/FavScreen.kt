package com.example.quotesapp.ui.fav_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.quotesapp.ui.viewmodel.QuoteViewModel


@Composable
fun FavScreen(paddingValues: PaddingValues, quoteViewModel: QuoteViewModel) {

    Box(modifier=Modifier.padding(paddingValues)
        .fillMaxSize().background(color = Color.Black)){

        val filter = quoteViewModel.quoteState.value.dataList.filter {
            it.liked
        }

        LazyColumn(modifier=Modifier.fillMaxSize()) {
            items(filter){ quote ->
                FavQuoteItem(quote)
            }

        }


    }

}