package com.android.engineer.mealmate.view.features.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.data.model.response.MissedUnUsedIngredients
import com.android.engineer.mealmate.data.model.response.SearchByIngredients
import com.android.engineer.mealmate.data.model.response.SearchByNutrients
import com.android.engineer.mealmate.data.utils.STATIC_INGREDIENTS_IMAGE1
import com.android.engineer.mealmate.data.utils.STATIC_INGREDIENTS_IMAGE2
import com.android.engineer.mealmate.data.utils.STATIC_INGREDIENTS_IMAGE3
import com.android.engineer.mealmate.data.utils.STATIC_INGREDIENTS_IMAGE4
import com.android.engineer.mealmate.data.utils.STATIC_INGREDIENTS_IMAGE5
import com.android.engineer.mealmate.data.utils.STATIC_INGREDIENTS_IMAGE6
import com.android.engineer.mealmate.data.utils.STATIC_INGREDIENTS_IMAGE7
import com.android.engineer.mealmate.data.utils.STATIC_URL1
import com.android.engineer.mealmate.data.utils.STATIC_URL_IMAGE1
import com.android.engineer.mealmate.data.utils.STATIC_URL_IMAGE2
import com.android.engineer.mealmate.view.features.home.model.MealChipList
import com.android.engineer.mealmate.view.utils.constants.MinMaxRangeEnum
import com.android.engineer.mealmate.view.utils.constants.NutrientsEnum
import com.android.engineer.mealmate.view.utils.constants.SearchByEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class RecipeViewModel: ViewModel() {

    val isShowNextMealView = mutableStateOf(true)
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _historyItem = MutableStateFlow(
        mutableListOf(
            "apples, flour, sugar",
            "apple"
        )
    )
    val historyItem = _historyItem.asStateFlow()

    private var _isActive = MutableStateFlow(false)
    val isActive = _isActive.asStateFlow()

    private var _isSearchByNutrients = MutableStateFlow(true)
    val isSearchByNutrients = _isSearchByNutrients.asStateFlow()

    private val _searchByNutrients = MutableStateFlow(getByNutrientsItems)
    val searchByNutrients = searchText
        .combine(_searchByNutrients) { text, searchByNutrients ->
            if (text.isBlank()) {
                searchByNutrients
            } else {
                searchByNutrients
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _searchByNutrients.value

        )
    private val _searchByIngredients = MutableStateFlow(getByIngredientsItems)
    val searchByIngredients = searchText
        .combine(_searchByIngredients) { text, searchByIngredients ->
            if (text.isBlank()) {
                searchByIngredients
            } else {
                searchByIngredients
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _searchByIngredients.value

        )

    private val _isBottomSheetShowing = MutableStateFlow(false)
    val isBottomSheetShowing = _isBottomSheetShowing.asStateFlow()

    private val _selectedSearchBy = MutableStateFlow(SearchByEnum.NUTRIENTS.name)
    val selectedSearchBy = _selectedSearchBy.asStateFlow()

    private val _dailyKcal = MutableStateFlow(DAILY_KCAL)
    val dailyKcal = _dailyKcal.asStateFlow()

    private val getSearchItems = listOf(
        MealChipList(name = SearchByEnum.NUTRIENTS.name, isSelected = true, unSelectedIcon = R.drawable.ic_nutrients_icon),
        MealChipList(name = SearchByEnum.INGREDIENTS.name, isSelected = false, unSelectedIcon = R.drawable.ic_ingredient_icon)
    )

    private val _searchByItems = MutableStateFlow(getSearchItems)
    val searchByItems = _searchByItems.asStateFlow()

    private val _minMaxCarbs = MutableStateFlow(MinMaxRangeEnum.MIN_MAX_CARBS.minMaxRange)
    val minMaxCarbs = _minMaxCarbs.asStateFlow()

    private val _minMaxProtein = MutableStateFlow(MinMaxRangeEnum.MIN_MAX_PROTEIN.minMaxRange)
    val minMaxProtein = _minMaxProtein.asStateFlow()

    private val _minMaxKCal = MutableStateFlow(MinMaxRangeEnum.MIN_MAX_KCAL.minMaxRange)
    val minMaxKCal = _minMaxKCal.asStateFlow()

    private val _minMaxFat = MutableStateFlow(MinMaxRangeEnum.MIN_MAX_FAT.minMaxRange)
    val minMaxFat = _minMaxFat.asStateFlow()

    fun onQueryChange(text: String) {
        _searchText.value = text
    }

    fun onSearch(text: String) {
        if (!_historyItem.value.contains(text)) {
            _historyItem.value.add(text)
        }
        _isActive.value = false
        _searchText.value = ""
    }

    fun onActiveChange(isActive: Boolean) {
        _isActive.value = isActive
    }

    fun onCloseIconClicked() {
        if (_searchText.value.isNotEmpty()) _searchText.value = "" else _isActive.value = false
    }
    fun onFilterIconClicked() {
        if(!_isBottomSheetShowing.value) {
            showHideBottomSheet(true)
        }
    }

    fun showHideBottomSheet(isShow: Boolean) {
        _isBottomSheetShowing.value = isShow
    }

    fun onDailyKCalValueChange(newText: String) {
        _dailyKcal.value = newText
    }

    fun onSelectedChipView(selectedChip: String) {
        _searchByItems.value.forEach { item ->
            item.isSelected = selectedChip == item.name
            if(item.isSelected) {
                _selectedSearchBy.value = item.name
            }
        }
    }

    fun onResetAllClicked() {
        _dailyKcal.value = DAILY_KCAL
        _selectedSearchBy.value = SearchByEnum.NUTRIENTS.name
        _searchByItems.value.forEach { item ->
            item.isSelected = _selectedSearchBy.value == item.name
        }
        _minMaxCarbs.value = MinMaxRangeEnum.MIN_MAX_CARBS.minMaxRange
        _minMaxProtein.value = MinMaxRangeEnum.MIN_MAX_PROTEIN.minMaxRange
        _minMaxKCal.value = MinMaxRangeEnum.MIN_MAX_KCAL.minMaxRange
        _minMaxFat.value = MinMaxRangeEnum.MIN_MAX_FAT.minMaxRange
    }

    fun onApplyClicked() {
        _isBottomSheetShowing.value = false
    }

    fun onNutrientsRangeChanged(getNutrientsEnum: NutrientsEnum, minMaxRange: ClosedFloatingPointRange<Float>) {
        when (getNutrientsEnum) {
            NutrientsEnum.CARBOHYDRATES -> _minMaxCarbs.value = minMaxRange
            NutrientsEnum.PROTEIN -> _minMaxProtein.value = minMaxRange
            NutrientsEnum.CALORIES -> _minMaxKCal.value = minMaxRange
            NutrientsEnum.FAT -> _minMaxFat.value = minMaxRange
        }
    }
}

// Pre-defined list items. These values will be removed once API integration is complete.
val getByNutrientsItems = listOf(
    SearchByNutrients(
        id = 1,
        title = "Baked Apples in White Wine",
        image = STATIC_URL_IMAGE1,
        calories = 210,
        carbs = "43g",
        fat = "3g",
        protein = "1g",
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByNutrients(
        id = 2,
        title = "Chocolate Silk Pie with Marshmallow Meringue",
        image = STATIC_URL_IMAGE2,
        calories = 210,
        carbs = "33g",
        fat = "10g",
        protein = "2g",
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByNutrients(
        id = 3,
        title = "Chocolate Silk Pie with Marshmallow Meringue",
        image = STATIC_URL_IMAGE1,
        calories = 210,
        carbs = "33g",
        fat = "10g",
        protein = "2g",
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByNutrients(
        id = 4,
        title = "Baked Apples in White Wine",
        image = STATIC_URL_IMAGE2,
        calories = 210,
        carbs = "43g",
        fat = "3g",
        protein = "1g",
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByNutrients(
        id = 5,
        title = "Chocolate Silk Pie with Marshmallow Meringue",
        image = STATIC_URL_IMAGE1,
        calories = 210,
        carbs = "33g",
        fat = "10g",
        protein = "2g",
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByNutrients(
        id = 6,
        title = "Baked Apples in White Wine",
        image = STATIC_URL_IMAGE2,
        calories = 210,
        carbs = "43g",
        fat = "3g",
        protein = "1g",
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByNutrients(
        id = 7,
        title = "Chocolate Silk Pie with Marshmallow Meringue",
        image = STATIC_URL_IMAGE1,
        calories = 210,
        carbs = "33g",
        fat = "10g",
        protein = "2g",
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByNutrients(
        id = 8,
        title = "Chocolate Silk Pie with Marshmallow Meringue",
        image = STATIC_URL_IMAGE2,
        calories = 210,
        carbs = "33g",
        fat = "10g",
        protein = "2g",
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByNutrients(
        id = 9,
        title = "Baked Apples in White Wine",
        image = STATIC_URL_IMAGE1,
        calories = 210,
        carbs = "43g",
        fat = "3g",
        protein = "1g",
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByNutrients(
        id = 10,
        title = "Chocolate Silk Pie with Marshmallow Meringue",
        image = STATIC_URL_IMAGE2,
        calories = 210,
        carbs = "33g",
        fat = "10g",
        protein = "2g",
        spoonacularSourceUrl = STATIC_URL1
    )

)

val getByIngredientsItems = listOf(
    SearchByIngredients(
        id = 1,
        image = STATIC_URL_IMAGE1,
        title = "Apple Or Peach Strudel",
        likes = 0,
        missedIngredientCount = 3,
        missedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 18371,
                image = STATIC_INGREDIENTS_IMAGE1,
                name = "baking powder",
                original = "1 tsp baking powder",
                amount = 1.0,
            ),
            MissedUnUsedIngredients(
                id = 2010,
                image = STATIC_INGREDIENTS_IMAGE2,
                name = "cinnamon",
                original = "1 tsp cinnamon",
                amount = 1.0,
            ),
            MissedUnUsedIngredients(
                id = 1123,
                image = STATIC_INGREDIENTS_IMAGE3,
                name = "egg",
                original = "1 egg",
                amount = 1.0,
            )
        ),
        usedIngredientCount = 1,
        usedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 9003,
                image = STATIC_INGREDIENTS_IMAGE4,
                name = "apples",
                original = "6 large baking apples",
                amount = 6.0,
            )
        ),
        unusedIngredients = listOf(),
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByIngredients(
        id = 2,
        image = STATIC_URL_IMAGE2,
        title = "Apricot Glazed Apple Tart",
        likes = 3,
        missedIngredientCount = 4,
        missedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 1001,
                image = STATIC_INGREDIENTS_IMAGE5,
                name = "butter",
                original = "1 1/2 sticks cold unsalted butter cold unsalted butter<",
                amount = 1.5,
            ),
            MissedUnUsedIngredients(
                id = 1079003,
                image = STATIC_INGREDIENTS_IMAGE6,
                name = "red apples",
                original = "4 larges red apples, such as Golden Delicious, peeled, cored and cut into 1/4-inch-thick slices",
                amount = 4.0,
            ),
            MissedUnUsedIngredients(
                id = 2010,
                image = STATIC_INGREDIENTS_IMAGE2,
                name = "cinnamon",
                original = "2 teaspoons cinnamon",
                amount = 2.0,
            ),
            MissedUnUsedIngredients(
                id = 19719,
                image = STATIC_INGREDIENTS_IMAGE7,
                name = "apricot preserves",
                original = "2 tablespoons apricot preserves, melted and strained",
                amount = 2.0,
            )
        ),
        usedIngredientCount = 0,
        usedIngredients = listOf(),
        unusedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 9003,
                image = STATIC_INGREDIENTS_IMAGE4,
                name = "apples",
                original = "apples",
                amount = 1.0,
            )
        ),
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByIngredients(
        id = 3,
        image = STATIC_URL_IMAGE1,
        title = "Apple Or Peach Strudel",
        likes = 0,
        missedIngredientCount = 3,
        missedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 18371,
                image = STATIC_INGREDIENTS_IMAGE1,
                name = "baking powder",
                original = "1 tsp baking powder",
                amount = 1.0,
            ),
            MissedUnUsedIngredients(
                id = 2010,
                image = STATIC_INGREDIENTS_IMAGE2,
                name = "cinnamon",
                original = "1 tsp cinnamon",
                amount = 1.0,
            ),
            MissedUnUsedIngredients(
                id = 1123,
                image = STATIC_INGREDIENTS_IMAGE3,
                name = "egg",
                original = "1 egg",
                amount = 1.0,
            )
        ),
        usedIngredientCount = 1,
        usedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 9003,
                image = STATIC_INGREDIENTS_IMAGE4,
                name = "apples",
                original = "6 large baking apples",
                amount = 6.0,
            )
        ),
        unusedIngredients = listOf(),
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByIngredients(
        id = 4,
        image = STATIC_URL_IMAGE2,
        title = "Apricot Glazed Apple Tart",
        likes = 3,
        missedIngredientCount = 4,
        missedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 1001,
                image = STATIC_INGREDIENTS_IMAGE5,
                name = "butter",
                original = "1 1/2 sticks cold unsalted butter cold unsalted butter<",
                amount = 1.5,
            ),
            MissedUnUsedIngredients(
                id = 1079003,
                image = STATIC_INGREDIENTS_IMAGE6,
                name = "red apples",
                original = "4 larges red apples, such as Golden Delicious, peeled, cored and cut into 1/4-inch-thick slices",
                amount = 4.0,
            ),
            MissedUnUsedIngredients(
                id = 2010,
                image = STATIC_INGREDIENTS_IMAGE2,
                name = "cinnamon",
                original = "2 teaspoons cinnamon",
                amount = 2.0,
            ),
            MissedUnUsedIngredients(
                id = 19719,
                image = STATIC_INGREDIENTS_IMAGE7,
                name = "apricot preserves",
                original = "2 tablespoons apricot preserves, melted and strained",
                amount = 2.0,
            )
        ),
        usedIngredientCount = 2,
        usedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 9003,
                image = STATIC_INGREDIENTS_IMAGE4,
                name = "apples",
                original = "6 large baking apples",
                amount = 6.0,
            ),
            MissedUnUsedIngredients(
                id = 9003,
                image = STATIC_INGREDIENTS_IMAGE4,
                name = "apples",
                original = "6 large baking apples",
                amount = 6.0,
            )
        ),
        unusedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 9003,
                image = STATIC_INGREDIENTS_IMAGE4,
                name = "apples",
                original = "apples",
                amount = 1.0,
            )
        ),
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByIngredients(
        id = 5,
        image = STATIC_URL_IMAGE1,
        title = "Apple Or Peach Strudel",
        likes = 0,
        missedIngredientCount = 3,
        missedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 18371,
                image = STATIC_INGREDIENTS_IMAGE1,
                name = "baking powder",
                original = "1 tsp baking powder",
                amount = 1.0,
            ),
            MissedUnUsedIngredients(
                id = 2010,
                image = STATIC_INGREDIENTS_IMAGE2,
                name = "cinnamon",
                original = "1 tsp cinnamon",
                amount = 1.0,
            ),
            MissedUnUsedIngredients(
                id = 1123,
                image = STATIC_INGREDIENTS_IMAGE3,
                name = "egg",
                original = "1 egg",
                amount = 1.0,
            )
        ),
        usedIngredientCount = 1,
        usedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 9003,
                image = STATIC_INGREDIENTS_IMAGE4,
                name = "apples",
                original = "6 large baking apples",
                amount = 6.0,
            )
        ),
        unusedIngredients = listOf(),
        spoonacularSourceUrl = STATIC_URL1
    ),
    SearchByIngredients(
        id = 6,
        image = STATIC_URL_IMAGE2,
        title = "Apricot Glazed Apple Tart",
        likes = 3,
        missedIngredientCount = 4,
        missedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 1001,
                image = STATIC_INGREDIENTS_IMAGE5,
                name = "butter",
                original = "1 1/2 sticks cold unsalted butter cold unsalted butter<",
                amount = 1.5,
            ),
            MissedUnUsedIngredients(
                id = 1079003,
                image = STATIC_INGREDIENTS_IMAGE6,
                name = "red apples",
                original = "4 larges red apples, such as Golden Delicious, peeled, cored and cut into 1/4-inch-thick slices",
                amount = 4.0,
            ),
            MissedUnUsedIngredients(
                id = 2010,
                image = STATIC_INGREDIENTS_IMAGE2,
                name = "cinnamon",
                original = "2 teaspoons cinnamon",
                amount = 2.0,
            ),
            MissedUnUsedIngredients(
                id = 19719,
                image = STATIC_INGREDIENTS_IMAGE7,
                name = "apricot preserves",
                original = "2 tablespoons apricot preserves, melted and strained",
                amount = 2.0,
            )
        ),
        usedIngredientCount = 0,
        usedIngredients = listOf(),
        unusedIngredients = listOf(
            MissedUnUsedIngredients(
                id = 9003,
                image = STATIC_INGREDIENTS_IMAGE4,
                name = "apples",
                original = "apples",
                amount = 1.0,
            )
        ),
        spoonacularSourceUrl = STATIC_URL1
    )
)