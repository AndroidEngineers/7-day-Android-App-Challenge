package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.view.features.home.model.MealChipList
import com.android.engineer.mealmate.view.utils.constants.SearchByEnum

@Composable
fun MealFilterChipGroup(
    chipList: List<MealChipList>,
    defaultSelectedItemIndex: Int  = 0,
    selectedItemIcon: ImageVector = Icons.Filled.Done,
    onSelectedChanged: (String) -> Unit = {}
) {
    var selectedItemIndex by remember { mutableIntStateOf(defaultSelectedItemIndex) }
    if(selectedItemIndex != defaultSelectedItemIndex) {
        selectedItemIndex = defaultSelectedItemIndex
    }
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            userScrollEnabled = true
        ) {
            items(count = chipList.size) { index ->
                FilterChip(
                    modifier = Modifier.padding(end = 6.dp),
                    selected = chipList[selectedItemIndex] == chipList[index],
                    onClick = {
                        selectedItemIndex = index
                        onSelectedChanged(chipList[selectedItemIndex].name)
                    },
                    label = { Text(chipList[index].name) },
                    leadingIcon = if (chipList[selectedItemIndex] == chipList[index]) {
                        {
                            Icon(
                                imageVector = selectedItemIcon,
                                contentDescription = "",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        {
                            Icon(
                                painter = painterResource(id = chipList[index].unSelectedIcon),
                                contentDescription = "",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    },
                    colors = SelectableChipColors(
                        containerColor = Color.White,
                        labelColor = OrangePrimary,
                        leadingIconColor = OrangePrimary,
                        trailingIconColor = Color.White,
                        disabledContainerColor = Color.Transparent,
                        disabledLabelColor = Color.Transparent,
                        disabledLeadingIconColor = Color.Transparent,
                        disabledTrailingIconColor = Color.Transparent,
                        disabledSelectedContainerColor = Color.Transparent,
                        selectedContainerColor = OrangePrimary,
                        selectedLabelColor = Color.White,
                        selectedLeadingIconColor = Color.White,
                        selectedTrailingIconColor = Color.Transparent
                    ),
                    border = BorderStroke(width = 1.dp, color = OrangePrimary)
                )
            }
        }
    }
}


@Preview
@Composable
fun MealChipGroupPreview() {
    val getSearchItems = listOf(
        MealChipList(name = SearchByEnum.NUTRIENTS.name, isSelected = true, unSelectedIcon = R.drawable.ic_nutrients_icon),
        MealChipList(name = SearchByEnum.INGREDIENTS.name, isSelected = false, unSelectedIcon = R.drawable.ic_ingredient_icon)
    )
    MealFilterChipGroup(chipList = getSearchItems)
}
