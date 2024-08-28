package com.android.engineer.mealmate.view.features.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.data.local.datastore.MealDataStore
import com.android.engineer.mealmate.data.remote.model.response.IngredientsResponseItem
import com.android.engineer.mealmate.data.remote.model.response.NutrientsResponseItem
import com.android.engineer.mealmate.data.utils.API_KEY
import com.android.engineer.mealmate.data.utils.API_KEY_VALUE
import com.android.engineer.mealmate.data.utils.INGREDIENTS_KEY
import com.android.engineer.mealmate.data.utils.MAX_CARBS_KEY
import com.android.engineer.mealmate.data.utils.MIN_CARBS_KEY
import com.android.engineer.mealmate.data.utils.NUMBER_KEY
import com.android.engineer.mealmate.data.utils.NUMBER_KEY_VALUE
import com.android.engineer.mealmate.data.utils.USERNAME
import com.android.engineer.mealmate.repository.remote.RecipeSearchRepository
import com.android.engineer.mealmate.view.features.home.model.MealChipList
import com.android.engineer.mealmate.view.utils.constants.MinMaxRangeEnum
import com.android.engineer.mealmate.view.utils.constants.NutrientsEnum
import com.android.engineer.mealmate.view.utils.constants.SearchByEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeSearchRepository,
    private val dataStore: MealDataStore
): ViewModel() {
    val loggedInUserName = mutableStateOf("")

    init {
        viewModelScope.launch {
            loggedInUserName.value = dataStore.getString(USERNAME) ?: ""
        }
    }
    val isShowNextMealView = mutableStateOf(false)
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

    private val _searchByNutrients = MutableStateFlow(listOf(NutrientsResponseItem(0, "", "", 0, "", "", "", "")))
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
    private val _searchByIngredients = MutableStateFlow(listOf(IngredientsResponseItem(0, "", "", 0, 0, listOf(), "", listOf(),0, listOf())))
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

    val isScreenLoading = mutableStateOf(false)

    fun onQueryChange(text: String) {
        _searchText.value = text
    }

    fun onSearch(text: String) {
        if (!_historyItem.value.contains(text)) {
            _historyItem.value.add(text)
        }
        _isActive.value = false
        _searchText.value = ""
        if(!_isSearchByNutrients.value) {
            searchByApi(searchedText = text)
        }
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
                _isSearchByNutrients.value = item.name == SearchByEnum.NUTRIENTS.name
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
        viewModelScope.launch {
            if(_isSearchByNutrients.value) {
                isScreenLoading.value = true
                val filter = hashMapOf<String, String>()
                filter[API_KEY] = API_KEY_VALUE
                filter[MIN_CARBS_KEY] = _minMaxCarbs.value.start.toInt().toString()
                filter[MAX_CARBS_KEY] = _minMaxCarbs.value.endInclusive.toInt().toString()
                filter[NUMBER_KEY] = NUMBER_KEY_VALUE

                try{
                    val nutrientsResponseList = repository.searchByNutrients(filter)
                    nutrientsResponseList.collectLatest {
                        _searchByNutrients.value = it
                        isScreenLoading.value = false
                    }
                } catch (e: Exception) {
                    Log.d("onResponse", "There is an error")
                    isScreenLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun onNutrientsRangeChanged(getNutrientsEnum: NutrientsEnum, minMaxRange: ClosedFloatingPointRange<Float>) {
        when (getNutrientsEnum) {
            NutrientsEnum.CARBOHYDRATES -> _minMaxCarbs.value = minMaxRange
            NutrientsEnum.PROTEIN -> _minMaxProtein.value = minMaxRange
            NutrientsEnum.CALORIES -> _minMaxKCal.value = minMaxRange
            NutrientsEnum.FAT -> _minMaxFat.value = minMaxRange
        }
    }

    private fun searchByApi(searchedText: String) {
        viewModelScope.launch {
            isScreenLoading.value = true
            val values = searchedText.split("\\s+".toRegex())
            var ingredientValues = ""
            for(item in values) {
                ingredientValues = ingredientValues.plus(item).plus(",+")
            }
            ingredientValues = ingredientValues.substring(0, ingredientValues.length-2)

            val filter = hashMapOf<String, String>()
            filter[API_KEY] = API_KEY_VALUE
            filter[INGREDIENTS_KEY] = ingredientValues
            filter[NUMBER_KEY] = NUMBER_KEY_VALUE

            try {
                val ingredientsResponseList = repository.searchByIngredients(filter)
                ingredientsResponseList.collectLatest {
                    _searchByIngredients.value = it
                    isScreenLoading.value = false
                }
            } catch (e: Exception) {
                Log.d("onResponse", "There is an error")
                isScreenLoading.value = false
                e.printStackTrace()
            }
        }
    }
}
