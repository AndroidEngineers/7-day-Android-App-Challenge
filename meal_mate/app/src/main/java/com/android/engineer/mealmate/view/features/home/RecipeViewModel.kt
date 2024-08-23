package com.android.engineer.mealmate.view.features.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.engineer.mealmate.model.data.response.MissedUnUsedIngredients
import com.android.engineer.mealmate.model.data.response.SearchByIngredients
import com.android.engineer.mealmate.model.data.response.SearchByNutrients
import com.android.engineer.mealmate.view.utils.constants.STATIC_INGREDIENTS_IMAGE1
import com.android.engineer.mealmate.view.utils.constants.STATIC_INGREDIENTS_IMAGE2
import com.android.engineer.mealmate.view.utils.constants.STATIC_INGREDIENTS_IMAGE3
import com.android.engineer.mealmate.view.utils.constants.STATIC_INGREDIENTS_IMAGE4
import com.android.engineer.mealmate.view.utils.constants.STATIC_INGREDIENTS_IMAGE5
import com.android.engineer.mealmate.view.utils.constants.STATIC_INGREDIENTS_IMAGE6
import com.android.engineer.mealmate.view.utils.constants.STATIC_INGREDIENTS_IMAGE7
import com.android.engineer.mealmate.view.utils.constants.STATIC_URL1
import com.android.engineer.mealmate.view.utils.constants.STATIC_URL_IMAGE1
import com.android.engineer.mealmate.view.utils.constants.STATIC_URL_IMAGE2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class RecipeViewModel() : ViewModel() {

    val isShowNextMealView = mutableStateOf(true)
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _historyItem = MutableStateFlow<MutableList<String>>(
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