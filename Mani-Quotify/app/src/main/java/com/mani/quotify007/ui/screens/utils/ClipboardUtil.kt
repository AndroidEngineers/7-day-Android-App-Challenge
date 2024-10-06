package com.mani.quotify007.ui.screens.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.mani.quotify007.domain.model.Quote

fun onCopyText(context: Context, quote: Quote) {
    val clipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboardManager.setPrimaryClip(
        ClipData.newPlainText(
            "quote",
            "${quote.text} - ${quote.author}"
        )
    )
    Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
}