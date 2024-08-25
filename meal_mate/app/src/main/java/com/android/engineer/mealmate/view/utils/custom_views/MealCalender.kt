package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.engineer.mealmate.ui.theme.OrangeDark
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.ui.theme.White
import com.android.engineer.mealmate.view.features.meal_plan.MealCalendarViewModel
import com.android.engineer.mealmate.view.features.meal_plan.model.CalendarUiModel


@Composable
fun MealCalender(
	modifier: Modifier = Modifier,
	viewModel: MealCalendarViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
	val data by viewModel.data.collectAsState()
	val headingText = viewModel.getHeadingText(data)

	Column(modifier = modifier.fillMaxWidth()) {
		Spacer(modifier = Modifier.height(10.dp))
		CalenderHeader(
			headingText = headingText,
			onPrevClickListener = { viewModel.onPrevClick(data.startDate.date) },
			onNextClickListener = { viewModel.onNextClick(data.endDate.date) }
		)
		Spacer(modifier = Modifier.height(20.dp))
		CalContent(data = data, onDateSelected =  { date -> viewModel.onDateSelected(date) })
	}
}

@Composable
fun CalenderHeader(
	headingText: String,
	onPrevClickListener: () -> Unit,
	onNextClickListener: () -> Unit,
) {
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
		NavigationIconButton(
			imageVector = Icons.Filled.ChevronLeft,
			contentDescription = "Back",
			onClick = onPrevClickListener
		)
		NavigationIconButton(
			imageVector = Icons.Filled.ChevronRight,
			contentDescription = "Next",
			onClick = onNextClickListener
		)
	}
}

@Composable
fun NavigationIconButton(
	imageVector: ImageVector,
	contentDescription: String,
	onClick: () -> Unit,
	tint: Color = OrangeDark
) {
	IconButton(onClick = onClick) {
		Icon(
			imageVector = imageVector,
			contentDescription = contentDescription,
			tint = tint
		)
	}
}

@Composable
fun CalContent(
	data: CalendarUiModel,
	onDateSelected: (CalendarUiModel.Date) -> Unit,
	columns: GridCells = GridCells.Fixed(7)
) {
	LazyVerticalGrid(columns = columns) {
		items(data.visibleDates.size) { index ->
			CalContentItem(
				date = data.visibleDates[index],
				onDateSelected = onDateSelected
			)
		}
	}
}



@Composable
fun CalContentItem(
	date: CalendarUiModel.Date,
	onDateSelected: (CalendarUiModel.Date) -> Unit,
	modifier: Modifier = Modifier,
	shape: Shape = RoundedCornerShape(20.dp),
	paddingValues: PaddingValues = PaddingValues(vertical = 16.dp),
	selectedColor: Color = OrangePrimary,
	unselectedColor: Color = Color.Transparent,
	fontColor: Color = if (date.isSelected) White else OrangePrimary
) {
	Card(
		modifier = modifier
			.padding(1.dp)
			.clickable { onDateSelected(date) },
		shape = shape,
		colors = CardDefaults.cardColors(
			containerColor = if (date.isSelected) selectedColor else unselectedColor
		)
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(paddingValues),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			CalContentText(text = date.day.uppercase(), color = fontColor)
			Icon(
				imageVector = Icons.Filled.Add,
				contentDescription = null,
				tint = fontColor,
				modifier = Modifier.padding(vertical = 10.dp)
			)
			CalContentText(date.date.dayOfMonth.toString(), color = fontColor)
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

@Preview(showSystemUi = true, widthDp = 800)
@Composable
fun CalendarAppPreview() {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(OrangeOnPrimary),
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		MealCalender(
			modifier = Modifier.padding(top = 30.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
		)
	}
}