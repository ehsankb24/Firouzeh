package kb24.ehsan.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
internal fun ScreenStatusImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int
) = Image(
    modifier = modifier
        .fillMaxWidth(0.4f)
        .aspectRatio(1f),
    painter = painterResource(imageRes),
    contentDescription = null
)