package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.engineer.mealmate.ui.theme.MealMateTheme
import com.android.engineer.mealmate.ui.theme.OrangeDark
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.ui.theme.White
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream

@Composable
fun MealCalender(
	modifier: Modifier = Modifier,
) {
	val dataSource = CalendarDataSource()
	var data by remember { mutableStateOf(dataSource.getData(lastSelectedDate = dataSource.today)) }
	Column(modifier = modifier.fillMaxWidth()) {
		Spacer(modifier = Modifier.height(10.dp))
		CalenderHeader(
			data = data,
			onPrevClickListener = { startDate ->
				val finalStartDate = startDate.minusDays(1)
				data = dataSource.getData(
					startDate = finalStartDate,
					lastSelectedDate = data.selectedDate.date
				)
			},
			onNextClickListener = { endDate ->
				val finalStartDate = endDate.plusDays(2)
				data = dataSource.getData(
					startDate = finalStartDate,
					lastSelectedDate = data.selectedDate.date
				)
			}
		)
		Spacer(modifier = Modifier.height(20.dp))
		CalContent(data = data) { date ->
			data = data.copy(
				selectedDate = date,
				visibleDates = data.visibleDates.map {
					it.copy(
						isSelected = it.date.isEqual(date.date)
					)
				}
			)
		}
	}
}


@Composable
fun CalenderHeader(
	data: CalendarUiModel,
	onPrevClickListener: (LocalDate) -> Unit,
	onNextClickListener: (LocalDate) -> Unit,
) {
	val headingText = if (data.selectedDate.isToday) {
		"Today"
	} else {
		data.selectedDate.date.format(
			DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
		)
	}
	
	Row {
		Text(
			text = headingText,
			modifier = Modifier
				.weight(1f)
				.heightIn(min = 70.dp)
				.align(Alignment.CenterVertically),
			fontSize = 24.sp,
			color = OrangeDark,
			fontWeight = FontWeight.ExtraBold
		)
		IconButton(onClick = {
			onPrevClickListener(data.startDate.date)
		}) {
			Icon(
				imageVector = Icons.Filled.ChevronLeft,
				contentDescription = "Back",
				tint = OrangeDark
			)
		}
		IconButton(onClick = {
			onNextClickListener(data.endDate.date)
		}) {
			Icon(
				imageVector = Icons.Filled.ChevronRight,
				contentDescription = "Next",
				tint = OrangeDark
			)
		}
	}
}

@Composable
fun CalContent(
	data: CalendarUiModel,
	onDateClickListener: (CalendarUiModel.Date) -> Unit,
) {
	LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 48.dp)) {
		items(data.visibleDates.size) { index ->
			CalContentItem(
				date = data.visibleDates[index],
				onDateClickListener
			)
		}
	}
}

@Composable
fun CalContentItem(
	date: CalendarUiModel.Date,
	onClickListener: (CalendarUiModel.Date) -> Unit,
) {
	val fontColor = if (date.isSelected) White else OrangePrimary
	Card(
		modifier = Modifier
			.padding(vertical = 1.dp, horizontal = 1.dp)
			.clickable {
				onClickListener(date)
			},
		shape = RoundedCornerShape(20.dp),
		colors = CardDefaults.cardColors(
			containerColor = if (date.isSelected) {
				OrangePrimary
			} else {
				Color.Transparent
			}
		),
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 16.dp, bottom = 16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			CalContentText(text = date.day.uppercase(), color = fontColor)
			Icon(
				imageVector = Icons.Filled.Add,
				contentDescription = "",
				tint = fontColor,
				modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
			)
			CalContentText(date.date.dayOfMonth.toString(), fontColor)
		}
	}
}

@Composable
fun CalContentText(text: String, color: Color) {
	Text(
		text = text, color = color,
		fontSize = 14.sp,
		fontWeight = FontWeight.ExtraBold
	)
}

@Preview(showSystemUi = true)
@Composable
fun CalendarAppPreview() {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(OrangeOnPrimary),
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		MealCalender(
			modifier = Modifier.padding(10.dp).padding(top = 20.dp)
		)
	}
}

data class CalendarUiModel(
	val selectedDate: Date,
	val visibleDates: List<Date>
) {
	
	val startDate: Date = visibleDates.first()
	val endDate: Date = visibleDates.last()
	
	data class Date(
		val date: LocalDate,
		val isSelected: Boolean,
		val isToday: Boolean
	) {
		val day: String =
			date.format(DateTimeFormatter.ofPattern("E"))
	}
}

class CalendarDataSource {
	val today: LocalDate
		get() {
			return LocalDate.now()
		}
	
	
	fun getData(startDate: LocalDate = today, lastSelectedDate: LocalDate): CalendarUiModel {
		val firstDayOfWeek = startDate.with(DayOfWeek.MONDAY)
		val endDayOfWeek = firstDayOfWeek.plusDays(7)
		val visibleDates = getDatesBetween(firstDayOfWeek, endDayOfWeek)
		return toUiModel(visibleDates, lastSelectedDate)
	}
	
	private fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
		val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
		return Stream.iterate(startDate) { date ->
			date.plusDays( 1)
		}
			.limit(numOfDays)
			.collect(Collectors.toList())
	}
	
	private fun toUiModel(
		dateList: List<LocalDate>,
		lastSelectedDate: LocalDate
	): CalendarUiModel {
		return CalendarUiModel(
			selectedDate = toItemUiModel(lastSelectedDate, true),
			visibleDates = dateList.map {
				toItemUiModel(it, it.isEqual(lastSelectedDate))
			},
		)
	}
	
	private fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean) = CalendarUiModel.Date(
		isSelected = isSelectedDate,
		isToday = date.isEqual(today),
		date = date,
	)
}