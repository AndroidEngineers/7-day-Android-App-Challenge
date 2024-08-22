import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abhijith.animex.ui.screens.animelist.AnimeListItem
import com.abhijith.animex.ui.screens.animelist.viewmodel.AnimeListViewModel

@Composable
fun AnimeList(viewModel: AnimeListViewModel = viewModel()) {
    val animeItems = viewModel.items.collectAsState()

    LazyColumn {
        items(animeItems.value.count()) {
            AnimeListItem(
                anime = animeItems.value[it],
                onItemClicked = viewModel::onItemClick,
                onButtonClicked = viewModel::onButtonClick
            )
        }
    }
}
// to bring the click events outside the composable if possible