package com.abhijith.animex.ui.components

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abhijith.animex.R

@Composable
fun AnimeCard(name: String, isFavorite: Boolean = false, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cdn.myanimelist.net/images/anime/1006/143302.jpg")
                    .crossfade(true).build(),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
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
                    Column {
                        Text(
                            text = "Anime Title",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            color = Color.Black,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = FontFamily(
                                Font(R.font.montserrat_regular)
                            ),
                        )
                        Text(
                            text = "Anime category: $name",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            color = Color.Gray,
                            fontFamily = FontFamily(
                                Font(R.font.montserrat_regular)
                            ),
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Popularity: 4*",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            color = Color.Black,
                            fontFamily = FontFamily(
                                Font(R.font.montserrat_regular)
                            ),
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "This Japanese Lorem Ipsum is based on the kanji frequency count at tidraso.co.uk and includes about 50% kanji, 25% hiragana, 20% katakana and 5% roman numerals and punctuation. Katakana and hiragana cluster in strings between 1 to 4 chars at random points in each paragraph. Hiragana occurs more often at the end of sentences, rather in clumps of 1 to 4 chars rather than just single chars. Katakana is very unlikely to appear as a single character in Japanese text, but hiragana could. Exclamation and question marks are \"double-byte\", not standard ascii ones. Suggestions for improvements or alternatives are welcome.",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 4,
                            color = Color.Gray,
                            fontFamily = FontFamily(
                                Font(R.font.montserrat_regular)
                            ),
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    FavoriteButton(
                        text = if (isFavorite) "Remove from Favorites" else "Add to Favorites",
                        onClick = {})
                }
            }
        }
    }
}