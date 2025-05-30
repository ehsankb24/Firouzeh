package kb24.ehsan.news.screen.articles.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kb24.ehsan.ui.component.shimmerLoadingAnimation
import kb24.ehsan.ui.theme.FirouzehTheme

@Composable
fun ArticleLoadingItem(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .shimmerLoadingAnimation(
                    shape = RoundedCornerShape(16.dp)
                ),
        )
        Spacer(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(60.dp)
                .shimmerLoadingAnimation()
        )
        HorizontalDivider(modifier = Modifier)
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(
                modifier = Modifier
                    .width(70.dp)
                    .height(30.dp)
                    .shimmerLoadingAnimation(),
            )
            Spacer(
                modifier = Modifier
                    .width(50.dp)
                    .height(30.dp)
                    .shimmerLoadingAnimation(),
            )
        }
    }
}

@Preview
@Composable
private fun ArticleLoadingItemPreview() {
    FirouzehTheme {
        ArticleLoadingItem()
    }
}