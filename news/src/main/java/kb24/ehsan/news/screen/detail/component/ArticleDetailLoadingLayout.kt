package kb24.ehsan.news.screen.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kb24.ehsan.ui.component.shimmerLoadingAnimation
import kb24.ehsan.ui.theme.FirouzehTheme

@Composable
fun ArticleDetailLoadingLayout(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .shimmerLoadingAnimation(),
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
                .shimmerLoadingAnimation(),
        )
        HorizontalDivider(
            modifier = Modifier.padding(16.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(30.dp)
                .shimmerLoadingAnimation()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .height(30.dp)
                .shimmerLoadingAnimation(),
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .height(30.dp)
                .shimmerLoadingAnimation(),
        )
        HorizontalDivider(
            modifier = Modifier.padding(16.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .shimmerLoadingAnimation(),
        )
    }
}

@Preview
@Composable
private fun ArticleDetailLoadingLayoutPreview() {
    FirouzehTheme {
        ArticleDetailLoadingLayout()
    }
}