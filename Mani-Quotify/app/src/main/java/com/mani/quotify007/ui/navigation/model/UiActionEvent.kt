package com.mani.quotify007.ui.navigation.model

import com.mani.quotify007.domain.model.Quote

sealed class UiActionEvent {
    data class CopyText(val quote: Quote) : UiActionEvent()
    data class ShareClick(val quote: Quote) : UiActionEvent()
    data class ShowToast(val message: String) : UiActionEvent()
}