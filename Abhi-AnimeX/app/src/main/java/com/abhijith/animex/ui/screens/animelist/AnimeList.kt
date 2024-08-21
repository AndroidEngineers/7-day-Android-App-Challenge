import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.abhijith.animex.ui.screens.animelist.AnimeListItem

@Composable
fun AnimeList() {
    val names = List(15) { "$it" }

    LazyColumn {
        items(names.count()) {
            AnimeListItem(name = names[it])
        }
    }
}