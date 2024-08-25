package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.data.model.response.SearchByIngredients
import com.android.engineer.mealmate.data.model.response.SearchByNutrients
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.view.features.home.FORWARD_SLASH
import com.android.engineer.mealmate.view.features.home.RecipeViewModel
import com.android.engineer.mealmate.view.utils.constants.nav.graph.RECIPE_DETAILS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealSearchView(viewModel: RecipeViewModel, navHostController: NavHostController) {
    val searchText by viewModel.searchText.collectAsState()
    val searchByNutrients by viewModel.searchByNutrients.collectAsState()
    val searchByIngredients by viewModel.searchByIngredients.collectAsState()
    val isActive by viewModel.isActive.collectAsState()
    val historyItem by viewModel.historyItem.collectAsState()
    val isSearchByNutrients by viewModel.isSearchByNutrients.collectAsState()
    val isBottomSheetShowing by viewModel.isBottomSheetShowing.collectAsState()

    DockedSearchBar(
        modifier = Modifier
            .fillMaxWidth(),
        colors = SearchBarDefaults.colors(
            containerColor = Color.White
        ),
        query = searchText,
        onQueryChange = {
            viewModel.onQueryChange(it)
        },
        onSearch = {
            viewModel.onSearch(searchText)
        },
        active = isActive,
        onActiveChange = {
            viewModel.onActiveChange(it)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                fontSize = 12.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_icon)
            )
        },
        trailingIcon = {
            if (isActive) {
                Icon(
                    modifier = Modifier.clickable {
                        viewModel.onCloseIconClicked()
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_icon)
                )
            } else {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            viewModel.onFilterIconClicked()
                        },
                    imageVector = Icons.Default.FilterList,
                    contentDescription = stringResource(id = R.string.search_icon)
                )
            }
        }
    ) {
        historyItem.takeLast(3).forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 14.dp)
                    .clickable {
                        viewModel.onQueryChange(item)
                        viewModel.onSearch(item)
                    }
            ) {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    imageVector = Icons.Default.History,
                    contentDescription = stringResource(id = R.string.history_icon)
                )
                Text(
                    text = item
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
    if (historyItem.size > 2) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (isSearchByNutrients) {
                items(items = searchByNutrients) { item ->
                    RowItemByNutrients(item = item) {
                        navHostController.navigate(RECIPE_DETAILS.plus(FORWARD_SLASH).plus(item.title))
                    }
                }
            } else {
                items(items = searchByIngredients) { item ->
                    RowItemByIngredients(item = item) {
                        navHostController.navigate(RECIPE_DETAILS.plus(FORWARD_SLASH).plus(item.title))
                    }
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.empty_search_list),
                color = OrangePrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

    if (isBottomSheetShowing) {
       MealModelBottomSheet(onDismiss = { viewModel.showHideBottomSheet(isShow = false) }, skipPartiallyExpanded = true)
    }
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
fun RowItemByNutrients(item: SearchByNutrients, onItemSelected: (Int) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        onClick = { onItemSelected(item.id) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MealImageLoading(imageUrl = item.image, imageSize = 120.dp)
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = item.title,
                    color = OrangePrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_kcal),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = LocalContentColor.current
                    )
                    Text(
                        text = item.calories.toString(),
                        color = OrangePrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_carbo),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = LocalContentColor.current
                    )
                    Text(
                        text = item.carbs,
                        color = OrangePrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_protein),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = LocalContentColor.current
                    )
                    Text(
                        text = item.protein,
                        color = OrangePrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fats),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = LocalContentColor.current
                    )
                    Text(
                        text = item.fat,
                        color = OrangePrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun RowItemByIngredients(item: SearchByIngredients, onItemSelected: (Int) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        onClick = { onItemSelected(item.id) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MealImageLoading(imageUrl = item.image, imageSize = 120.dp)
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = item.title,
                    color = OrangePrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                ShowIngredientsCountView(item)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (item.likes == 0) Icons.Default.SentimentVeryDissatisfied else Icons.Default.SentimentVerySatisfied,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (item.likes == 0) Color.Red else Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = if (item.likes == 0) "" else item.likes.toString(),
                    color = OrangePrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}


@Composable
fun ShowIngredientsCountView(item: SearchByIngredients) {
    if (item.missedIngredientCount > 0) {
        ShowCountView(
            countTitle = stringResource(id = R.string.missed_ingredient),
            count = item.missedIngredientCount.toString()
        )
    }

    if (item.usedIngredientCount > 0) {
        ShowCountView(
            countTitle = stringResource(id = R.string.used_ingredient),
            count = item.usedIngredientCount.toString()
        )
    }

    if (item.unusedIngredients.isNotEmpty()) {
        ShowCountView(
            countTitle = stringResource(id = R.string.unused_ingredient),
            count = item.unusedIngredients.size.toString()
        )
    }
}

@Composable
fun ShowCountView(countTitle: String, count: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = countTitle,
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = count,
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(20.dp))
    }
}

@Preview
@Composable
fun MealSearchItemPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeOnPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MealSearchView(viewModel = RecipeViewModel(), navHostController = rememberNavController())
    }
}
