package kb24.ehsan.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    uri: String
) = AsyncImage(
    modifier = modifier
        .fillMaxWidth()
        .aspectRatio(16 / 9f),
    model = uri,
    contentDescription = null,
    onError = {
        it.result.throwable.printStackTrace()
    },
    onLoading = {

    }
)