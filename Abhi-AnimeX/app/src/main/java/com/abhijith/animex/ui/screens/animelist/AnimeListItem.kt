package com.abhijith.animex.ui.screens.animelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abhijith.animex.R
import com.abhijith.animex.domain.model.AnimeItem
import com.abhijith.animex.ui.components.ScoreTag
import com.abhijith.animex.ui.components.WatchTrailerButton

@Composable
fun AnimeListItem(
    animeEntity: AnimeItem, onItemClicked: (AnimeItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onItemClicked(animeEntity) },
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(animeEntity.imageUrl)
                    .crossfade(true).build(),
                contentDescription = null,
                placeholder = painterResource(android.R.drawable.ic_menu_report_image),
                error = painterResource(android.R.drawable.stat_notify_error),
                modifier = Modifier
                    .width(125.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = animeEntity.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_regular)
                        ),
                    )
                    Text(
                        text = animeEntity.source,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_regular)
                        ),
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ScoreTag(rating = animeEntity.score)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = animeEntity.synopsis,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 4,
                        color = Color.Gray,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_regular)
                        ),
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    WatchTrailerButton(
                        text = getString(
                            LocalContext.current, R.string.watch_trailer
                        ),
                        youtubeId = animeEntity.youtubeId
                    )
                }
            }
        }
    }
}