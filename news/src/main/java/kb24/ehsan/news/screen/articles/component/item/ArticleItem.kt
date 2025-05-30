package kb24.ehsan.news.screen.articles.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kb24.ehsan.ui.component.ImageComponent
import kb24.ehsan.ui.theme.FirouzehTheme

@Composable
internal fun ArticleItem(
    modifier: Modifier = Modifier,
    data: ArticleItemUiModel,
    onItemClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onItemClick,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            data.image?.let {
                ImageComponent(
                    modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                    uri = it
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                            )
                        ),
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = data.title,
                color = MaterialTheme.colorScheme.surfaceDim
            )
        }
        data.shortBrief?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            HorizontalDivider(modifier = Modifier)
        }
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                text = data.queryName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                modifier = Modifier,
                text = data.date,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun ArticleItemPreview(
    @PreviewParameter(ArticleItemPreviewParameter::class) data: ArticleItemUiModel
) {
    FirouzehTheme {
        ArticleItem(
            data = data,
            onItemClick = {}
        )
    }
}

internal class ArticleItemPreviewParameter : PreviewParameterProvider<ArticleItemUiModel> {
    override val values: Sequence<ArticleItemUiModel>
        get() = sequenceOf(
            ArticleItemUiModel(
                id = 1,
                image = "https://biztoc.com/cdn/948/og.png",
                title = "How a small, family-run wine company in New York hit back against Donald Trump’s tariffs",
                shortBrief = "Gerber Kawasaki's CEO and Co-Founder, Ross Gerber, has said he hasn't dumped his Tesla Inc. (NASDAQ:TSLA) position and that his firm still holds the EV giant's shares despite Tesla insider trades and Gerber's bearish views on the company.\nWhat Happened: \"I ha…",
                queryName = "Apple",
                date = "2025-05-29 08:12:26"
            ),
            ArticleItemUiModel(
                id = 1,
                image = "https://biztoc.com/cdn/948/og.png",
                title = "How a small, family-run wine company in New York hit back against Donald Trump’s tariffs",
                shortBrief = null,
                queryName = "Apple",
                date = "2025-05-29 08:12:26"
            ),
            ArticleItemUiModel(
                id = 1,
                image = null,
                title = "How a small, family-run wine company in New York hit back against Donald Trump’s tariffs",
                shortBrief = "Gerber Kawasaki's CEO and Co-Founder, Ross Gerber, has said he hasn't dumped his Tesla Inc. (NASDAQ:TSLA) position and that his firm still holds the EV giant's shares despite Tesla insider trades and Gerber's bearish views on the company.\nWhat Happened: \"I ha…",
                queryName = "Apple",
                date = "2025-05-29 08:12:26"
            ),
            ArticleItemUiModel(
                id = 1,
                image = null,
                title = "How a small, family-run wine company in New York hit back against Donald Trump’s tariffs",
                shortBrief = null,
                queryName = "Apple",
                date = "2025-05-29 08:12:26"
            )
        )
}