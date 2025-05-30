package kb24.ehsan.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kb24.ehsan.ui.R
import kb24.ehsan.ui.theme.FirouzehTheme

@Composable
fun EmptyComponent(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    @StringRes messageRes: Int
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center
) {
    ScreenStatusImage(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        imageRes = imageRes
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        text = stringResource(messageRes),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleSmall
    )
}

@Preview
@Composable
private fun EmptyComponentPreview() {
    FirouzehTheme {
        EmptyComponent(
            imageRes = R.drawable.ic_launcher_background,
            messageRes = R.string.sample_empty_message
        )
    }
}