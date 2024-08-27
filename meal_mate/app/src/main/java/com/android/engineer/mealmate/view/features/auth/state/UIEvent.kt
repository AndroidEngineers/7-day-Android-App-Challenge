package com.android.engineer.mealmate.view.features.auth.state

sealed interface VisitingUIEvent {
    data class ShowErrorMessageStatic(
        val staticError: String?,
        val dynamicError: String?,
        val errorCode: String?
    ) : VisitingUIEvent
}