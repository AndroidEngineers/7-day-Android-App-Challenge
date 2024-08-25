package com.android.engineer.mealmate.view.features.meal_plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.engineer.mealmate.view.features.meal_plan.model.CalendarUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MealCalendarViewModel : ViewModel() {

    private val dataSource = CalendarDataSource()

    private val _data = MutableStateFlow(dataSource.getData(lastSelectedDate = dataSource.today))
    val data = _data.asStateFlow()

    fun onPrevClick(startDate: LocalDate) {
        viewModelScope.launch {
            val finalStartDate = startDate.minusDays(1)
            _data.value = dataSource.getData(
                startDate = finalStartDate,
                lastSelectedDate = _data.value.selectedDate.date
            )
        }
    }

    fun onNextClick(endDate: LocalDate) {
        viewModelScope.launch {
            val finalStartDate = endDate.plusDays(2)
            _data.value = dataSource.getData(
                startDate = finalStartDate,
                lastSelectedDate = _data.value.selectedDate.date
            )
        }
    }

    fun onDateSelected(date: CalendarUiModel.Date) {
        viewModelScope.launch {
            _data.value = _data.value.copy(
                selectedDate = date,
                visibleDates = _data.value.visibleDates.map {
                    it.copy(isSelected = it.date.isEqual(date.date))
                }
            )
        }
    }

    fun getHeadingText(data: CalendarUiModel): String {
        return if (data.selectedDate.isToday) {
            "Today"
        } else {
            data.selectedDate.date.format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
            )
        }
    }
}