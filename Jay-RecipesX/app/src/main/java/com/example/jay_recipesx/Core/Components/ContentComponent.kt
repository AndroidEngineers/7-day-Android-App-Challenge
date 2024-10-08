import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jay_recipesx.Core.AppFunctions.AppFunctions.Companion.removeHtmlTags

@Composable
fun ContentComponent(
    modifier: Modifier = Modifier,
    heading: String,
    content: String,
    maxLine: Int = Int.MAX_VALUE
) {

    Column {
        Text(text = heading,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            removeHtmlTags(content),
            textAlign = TextAlign.Justify,
            maxLines = maxLine,
            overflow = TextOverflow.Ellipsis
        )
    }
}