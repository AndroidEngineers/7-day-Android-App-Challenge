package com.mani.quotify007.ui.screens.utils

import android.content.Context
import android.content.Intent
import com.mani.quotify007.domain.model.Quote

object ShareUtil {
    fun shareQuote(context: Context, quote: Quote) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${quote.text} - ${quote.author}")
            type = "text/plain"
        }
        context.startActivity(Intent.createChooser(shareIntent, null))
    }
}