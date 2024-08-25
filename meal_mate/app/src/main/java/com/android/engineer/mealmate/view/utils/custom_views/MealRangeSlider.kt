package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangePrimary

@Composable
fun MealRangeSlider(
    minType: String,
    maxType: String,
    rangeVal : ClosedFloatingPointRange<Float>,
    onRangeValueChanged : (ClosedFloatingPointRange<Float>) -> Unit
) {
    var sliderPosition by remember { mutableStateOf(rangeVal) }
    RangeSlider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        value = sliderPosition,
        steps = 9,
        onValueChange = { range -> sliderPosition = range },
        valueRange = 0f..100f,
        onValueChangeFinished = {
            onRangeValueChanged(sliderPosition)
        },
        colors = SliderColors(
            thumbColor = OrangePrimary,
            activeTrackColor = OrangePrimary,
            activeTickColor = OrangePrimary,
            inactiveTickColor = OrangePrimary,
            inactiveTrackColor = Color.White,
            disabledInactiveTickColor = Color.Gray,
            disabledThumbColor = Color.Gray,
            disabledActiveTrackColor = Color.Gray,
            disabledInactiveTrackColor = Color.Gray,
            disabledActiveTickColor = Color.Gray
        )
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val suffixVal = if(minType == stringResource(id = R.string.min_calories)) "" else " g"
        MealText(
            text = minType.plus(" ${sliderPosition.start.toInt()}").plus(suffixVal)
        )
        MealText(
            text = maxType.plus(" ${sliderPosition.endInclusive.toInt()}").plus(suffixVal)
        )
    }
}