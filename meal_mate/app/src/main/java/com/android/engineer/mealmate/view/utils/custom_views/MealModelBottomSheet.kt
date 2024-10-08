package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.view.features.home.FoodPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealModelBottomSheet(
    onDismiss: () -> Unit,
    skipPartiallyExpanded: Boolean = false
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = OrangeOnPrimary,
    ) {
        FoodPreferences()
    }
}

@Preview
@Composable
fun MealModelBottomSheetPreview() {
    MealModelBottomSheet(onDismiss = {}, skipPartiallyExpanded = true)
}