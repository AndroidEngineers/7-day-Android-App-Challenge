package com.mani.quotify007.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val QUOTE_OF_THE_DAY_HEADER = "Quote of the Day"
const val HYPHEN_SPACE = "- "
const val QUOTE_AUTHOR_1 = "Robert Collier"

@Composable
fun HomeScreen(quotes: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(QUOTE_OF_THE_DAY_HEADER, fontStyle = FontStyle.Italic, fontSize = 30.sp)
        QuotesScreen(quote = quotes[0])
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { /* Refresh action */ }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Save")
            Spacer(modifier = Modifier.width(4.dp))
            Text("Refresh")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(listOf("Sample quote"))
}