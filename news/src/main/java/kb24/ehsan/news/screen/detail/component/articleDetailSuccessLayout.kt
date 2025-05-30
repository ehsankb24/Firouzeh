package kb24.ehsan.news.screen.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kb24.ehsan.news.R
import kb24.ehsan.ui.component.ImageComponent
import kb24.ehsan.ui.theme.FirouzehTheme

@Composable
fun ArticleDetailSuccessLayout(
    modifier: Modifier = Modifier,
    data: ArticleDetailLayoutUiModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        data.imageUri?.let {
            ImageComponent(
                modifier = Modifier.fillMaxWidth(),
                uri = it
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
            text = data.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        HorizontalDivider(
            modifier = Modifier.padding(16.dp)
        )
        data.author?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResource(R.string.article_detail_screen_author_title, it),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp),
            text = stringResource(R.string.article_detail_screen_source_title, data.source),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp),
            text = stringResource(R.string.article_detail_screen_date_title, data.date),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )
        HorizontalDivider(modifier = Modifier.padding(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            text = data.content,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun ArticleDetailSuccessLayoutPreview(
    @PreviewParameter(ArticleDetailSuccessLayoutPreviewParameter::class) data: ArticleDetailLayoutUiModel
) {
    FirouzehTheme {
        ArticleDetailSuccessLayout(
            data = data
        )
    }
}

internal class ArticleDetailSuccessLayoutPreviewParameter :
    PreviewParameterProvider<ArticleDetailLayoutUiModel> {
    override val values: Sequence<ArticleDetailLayoutUiModel>
        get() = sequenceOf(
            ArticleDetailLayoutUiModel(
                imageUri = "",
                title = "Despite Bearish Views, Ross Gerber Says Tesla Stake Remains: 'We've Continued Lowering Our Exposure'",
                content = "Gerber Kawasaki's CEO and Co-Founder, Ross Gerber, has said he hasn't dumped his Tesla Inc. (NASDAQ:TSLA) position and that his firm still holds the EV giant's shares despite Tesla insider trades and… [+142 chars]",
                date = "2025-05-29 08:12:02",
                author = "benzinga.com",
                source = "Biztoc.com"
            )
        )
}