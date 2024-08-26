package com.android.engineer.mealmate.view.features.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.view.features.home.model.MealChipList
import com.android.engineer.mealmate.view.utils.constants.NutrientsEnum
import com.android.engineer.mealmate.view.utils.constants.SearchByEnum
import com.android.engineer.mealmate.view.utils.custom_views.MealFilledButton
import com.android.engineer.mealmate.view.utils.custom_views.MealFilterChipGroup
import com.android.engineer.mealmate.view.utils.custom_views.MealRangeSlider
import com.android.engineer.mealmate.view.utils.custom_views.MealText
import com.android.engineer.mealmate.view.utils.custom_views.MealTextButton

@Composable
fun FoodPreferences() {
    val viewModel = viewModel<RecipeViewModel>()

    val selectedSearchBy = viewModel.selectedSearchBy.collectAsState()
    val setDailyKcal = viewModel.dailyKcal.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(OrangeOnPrimary)
            .verticalScroll(state = ScrollState(0), enabled = true)) {
        ShowHeaderView()
        ShowDailyKCalView(setDailyKcal.value, viewModel)
        ShowSearchByView(selectedSearchBy.value, viewModel)
        ShowBottomView(viewModel = viewModel)
    }
}

@Composable
fun ShowHeaderView() {
    Spacer(modifier = Modifier.height(15.dp))
    MealText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        text = stringResource(id = R.string.food_preferences),
        fontSize = 28.sp,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
fun ShowDailyKCalView(setDailyKcal: String, viewModel: RecipeViewModel) {
    MealText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        text = stringResource(id = R.string.daily_kcal)
    )
    Spacer(modifier = Modifier.height(10.dp))
    TextField(
        value = setDailyKcal,
        onValueChange = { newText ->
            viewModel.onDailyKCalValueChange(newText)
        },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .fillMaxWidth()
            .height(56.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp), color = OrangePrimary.copy(alpha = 0.8f))
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun ShowSearchByView(selectedSearchBy: String, viewModel: RecipeViewModel) {
    val getSearchByItem = viewModel.searchByItems.collectAsState()
    MealText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        text = stringResource(id = R.string.search_by)
    )
    MealFilterChipGroup(
        chipList = getSearchByItem.value,
        defaultSelectedItemIndex = getSelectedIndex(getSearchByItem.value),
        onSelectedChanged = { selectedItem ->
            viewModel.onSelectedChipView(selectedChip = selectedItem)
        }
    )
    if(selectedSearchBy == SearchByEnum.NUTRIENTS.name) {
        ShowHideNutrientsView(isShowView = true, viewModel = viewModel)
    } else {
        ShowHideNutrientsView(isShowView = false, viewModel = viewModel)
    }
}

fun getSelectedIndex(getSearchByItem: List<MealChipList>): Int {
    var selectedIndex = 0
    getSearchByItem.forEach { item ->
        if(item.isSelected) {
           selectedIndex = getSearchByItem.indexOf(item)
        }
    }
    return selectedIndex
}

@Composable
fun ShowHideNutrientsView(isShowView: Boolean = true, viewModel: RecipeViewModel) {
    val minMaxCarbs = viewModel.minMaxCarbs.collectAsState()
    val minMaxProtein = viewModel.minMaxProtein.collectAsState()
    val minMaxKCal = viewModel.minMaxKCal.collectAsState()
    val minMaxFat = viewModel.minMaxFat.collectAsState()
    if(isShowView) {
        HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp), color = OrangePrimary.copy(alpha = 0.8f))
        Spacer(modifier = Modifier.height(10.dp))
        MealText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            text = stringResource(id = R.string.set_min_max_value)
        )
        MealRangeSlider(
            minType = stringResource(id = R.string.min_carbs),
            maxType = stringResource(id = R.string.max_carbs),
            rangeVal = minMaxCarbs.value,
            onRangeValueChanged = {
                viewModel.onNutrientsRangeChanged(NutrientsEnum.CARBOHYDRATES, it)
            }
        )
        MealRangeSlider(
            minType = stringResource(id = R.string.min_protein),
            maxType = stringResource(id = R.string.max_protein),
            rangeVal = minMaxProtein.value,
            onRangeValueChanged = {
                viewModel.onNutrientsRangeChanged(NutrientsEnum.PROTEIN, it)
            }
        )
        MealRangeSlider(
            minType = stringResource(id = R.string.min_calories),
            maxType = stringResource(id = R.string.max_calories),
            rangeVal = minMaxKCal.value,
            onRangeValueChanged = {
                viewModel.onNutrientsRangeChanged(NutrientsEnum.CALORIES, it)
            }
        )
        MealRangeSlider(
            minType = stringResource(id = R.string.min_fat),
            maxType = stringResource(id = R.string.max_fat),
            rangeVal = minMaxFat.value,
            onRangeValueChanged = {
                viewModel.onNutrientsRangeChanged(NutrientsEnum.FAT, it)
            }
        )
    }
}

@Composable
fun ShowBottomView(viewModel: RecipeViewModel) {
    Spacer(modifier = Modifier.height(50.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MealTextButton(onClick = { viewModel.onResetAllClicked() }, text = stringResource(id = R.string.reset_all))
        MealFilledButton(onClick = { viewModel.onApplyClicked() }, text = stringResource(id = R.string.apply))
    }
}
