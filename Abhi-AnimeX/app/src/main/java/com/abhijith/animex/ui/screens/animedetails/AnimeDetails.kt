package com.abhijith.animex.ui.screens.animedetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abhijith.animex.R
import com.abhijith.animex.domain.model.AnimeItem
import com.abhijith.animex.ui.components.GenreTag
import com.abhijith.animex.ui.components.RatingTag
import com.abhijith.animex.ui.components.StatItem
import com.abhijith.animex.ui.components.YoutubeVideoTile
import com.abhijith.animex.ui.screens.animedetails.viewmodel.AnimeDetailsViewModel
import com.abhijith.animex.ui.screens.error.ErrorScreen
import com.abhijith.animex.ui.screens.loading.LoadingScreen

@Composable
fun AnimeDetails(animeDetailsViewModel: AnimeDetailsViewModel = viewModel()) {
    when (val uiState = animeDetailsViewModel.uiState.collectAsState().value) {
        is AnimeDetailsUiState.Error -> ErrorScreen(uiState.message)
        is AnimeDetailsUiState.Loading -> LoadingScreen()
        is AnimeDetailsUiState.Success -> AnimeDetailsInfo(uiState.item, animeDetailsViewModel)
    }
}

@Composable
fun AnimeDetailsInfo(animeItem: AnimeItem, animeDetailsViewModel: AnimeDetailsViewModel) {
    val yearAndJapaneseName =
        animeDetailsViewModel.formatYearAndJapaneseName(animeItem.year, animeItem.japaneseName)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(animeItem.imageUrl)
                        .crossfade(true).build(),
                    contentDescription = null,
                    placeholder = painterResource(android.R.drawable.ic_menu_report_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = animeItem.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_regular)
                        ),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = yearAndJapaneseName,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        color = Color.Gray,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_regular)
                        ),
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    RatingTag(rating = animeItem.rating)
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            StatItem(
                                imageVector = Icons.Default.PlayArrow,
                                modifier = Modifier.rotate(270F),
                                contentDesc = getString(LocalContext.current, R.string.rank),
                                count = animeItem.rank
                            )
                            StatItem(
                                imageVector = Icons.Default.Info,
                                contentDesc = getString(LocalContext.current, R.string.score),
                                count = animeItem.score
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = animeItem.synopsis,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_regular)
                        ),
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(animeItem.genres.count()) {
                            GenreTag(animeItem.genres[it])
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    YoutubeVideoTile(animeItem.youtubeId)
                }
            }
        }
    }
}