package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.ui.theme.White

@Composable
fun MealIconButton(
    onClick: () -> Unit,
    text: String,
    icon: Int,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 16.dp
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
        modifier = modifier.padding(horizontal = horizontalPadding)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealIconButtonPreview() {
    MealIconButton(onClick = {  }, text = stringResource(id = R.string.meal_card), icon = R.drawable.ic_meal_card)
}