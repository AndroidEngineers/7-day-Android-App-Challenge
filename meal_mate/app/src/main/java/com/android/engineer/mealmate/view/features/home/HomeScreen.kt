package com.android.engineer.mealmate.view.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.view.utils.constants.STATIC_BREAK_FAST_IMAGE
import com.android.engineer.mealmate.view.utils.custom_views.MealIconButton
import com.android.engineer.mealmate.view.utils.custom_views.MealImageLoading
import com.android.engineer.mealmate.view.utils.custom_views.MealSearchView

@Composable
fun HomeScreen(navHostController: NavHostController, userName: String, paddingValues: PaddingValues) {
    val viewModel = viewModel<RecipeViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeOnPrimary)
            .padding(paddingValues = paddingValues)
            .padding(all = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ShowTopView(userName = userName)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))
        if(viewModel.isShowNextMealView.value) {
            ShowNextMeal()
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))
        MealSearchView(
            viewModel = viewModel,
            navHostController = navHostController
        )
    }
}

@Composable
fun ShowTopView(userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(id = R.string.hello).plus(", ").plus(userName), fontSize = 30.sp)
        MealIconButton(
            onClick = {},
            text = stringResource(id = R.string.meal_card),
            icon = R.drawable.ic_meal_card
        )
    }
}

@Composable
fun ShowNextMeal() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        onClick = { },
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.next_meal_for),
                color = OrangePrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "1 hr",
                color = OrangePrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MealImageLoading(imageUrl = STATIC_BREAK_FAST_IMAGE)
            Column(
                modifier = Modifier.padding(vertical = 20.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.break_fast),
                    color = OrangePrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Fruits",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "Duration: 15 mins",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "Serves: 1",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}
