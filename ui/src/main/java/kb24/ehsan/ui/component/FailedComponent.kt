package kb24.ehsan.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
fun FailedComponent(
    modifier: Modifier = Modifier,
    message: String,
    onAction: () -> Unit
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center
) {
    ScreenStatusImage(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        imageRes = R.drawable.ic_launcher_background
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        text = message,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleSmall
    )
    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = onAction
    ) {
        Text(
            text = stringResource(R.string.retry_button_title)
        )
    }
}

@Preview
@Composable
private fun FailedComponentPreview() {
    FirouzehTheme {
        FailedComponent(message = "message") { }
    }
}