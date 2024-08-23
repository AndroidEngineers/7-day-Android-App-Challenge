package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.ui.theme.White

@Composable
fun MealFilledButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp  = 16.dp
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
        modifier = modifier.padding(horizontal = horizontalPadding)
    ) {
        Text(
            text = text,
            color = White,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MealFilledButtonPreview() {
    MealFilledButton(modifier = Modifier, onClick = { }, text = stringResource(R.string.logout))
}