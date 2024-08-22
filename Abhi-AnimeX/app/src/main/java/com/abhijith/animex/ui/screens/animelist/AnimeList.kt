import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abhijith.animex.ui.screens.animelist.AnimeListItem
import com.abhijith.animex.ui.screens.animelist.viewmodel.AnimeListViewModel

@Composable
fun AnimeList(viewModel: AnimeListViewModel = viewModel()) {
    val names = viewModel.items.collectAsState()

    LazyColumn {
        items(names.value.count()) {
            AnimeListItem(
                name = names.value[it],
                onItemClicked = viewModel::onItemClick,
                onButtonClicked = viewModel::onButtonClick
            )
        }
    }
}
// to bring the click events outside the composable