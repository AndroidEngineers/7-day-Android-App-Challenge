package com.mani.quotify007.ui.screens.quote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.screens.utils.ADDED_TO_FAVORITES
import com.mani.quotify007.ui.screens.utils.ALREADY_ADDED_TO_FAVORITES
import com.mani.quotify007.ui.screens.utils.HYPHEN_SPACE
import com.mani.quotify007.ui.screens.utils.REMOVED_FROM_FAVORITES
import com.mani.quotify007.ui.screens.utils.TEXT_COPIED_TO_CLIPBOARD

@Composable
fun QuotesScreen(
    quote: Quote,
    onEvent: (MainEvent) -> Unit,
    isAddOnly: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = quote.text,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = HYPHEN_SPACE + quote.author,
                fontFamily = FontFamily.Monospace,
                color = Color.Blue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextButton(onClick = {
                if (isAddOnly) {
                    if (!quote.isFavorite) {
                        quote.isFavorite = true
                        onEvent(MainEvent.AddFavorite(quote))
                        onEvent(MainEvent.ShowToast(ADDED_TO_FAVORITES))
                    } else {
                        onEvent(MainEvent.ShowToast(ALREADY_ADDED_TO_FAVORITES))
                    }
                } else {
                    if (quote.isFavorite) {
                        quote.isFavorite = false
                        onEvent(MainEvent.RemoveFavorite(quote))
                        onEvent(MainEvent.ShowToast(REMOVED_FROM_FAVORITES))
                    }
                }
            }) {
                Icon(
                    imageVector = if (quote.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Save"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Save")
            }
            TextButton(onClick = {
                onEvent(MainEvent.CopyText(quote))
                onEvent(MainEvent.ShowToast(TEXT_COPIED_TO_CLIPBOARD))
            }) {
                Spacer(modifier = Modifier.width(4.dp))
                Text("Copy Text")
            }
            TextButton(onClick = {
                onEvent(MainEvent.ShareClick(quote))
            }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Share")
            }
        }
    }
}

@Preview
@Composable
fun QuotesScreenPreview() {
    QuotesScreen(
        Quote(0, "Sample quote", "Sample author"),
        onEvent = {},
        true
    )
}