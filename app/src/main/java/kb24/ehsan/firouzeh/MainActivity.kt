package kb24.ehsan.firouzeh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import kb24.ehsan.news.navigation.ArticleDetailRout
import kb24.ehsan.news.navigation.ArticlesRout
import kb24.ehsan.news.screen.articles.ArticlesScreen
import kb24.ehsan.news.screen.detail.ArticleDetailScreen
import kb24.ehsan.news.screen.detail.ArticleDetailViewModel
import kb24.ehsan.news.screen.detail.ArticleDetailViewModelFactory
import kb24.ehsan.ui.theme.FirouzehTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirouzehTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .displayCutoutPadding()
                        .navigationBarsPadding()
                ) { innerPadding ->
                    MainNavHost(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun MainNavHost(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        NavHost(
            modifier = modifier.fillMaxSize(),
            navController = navController,
            startDestination = ArticlesRout
        ) {
            composable<ArticlesRout> {
                ArticlesScreen(
                    viewModel = hiltViewModel(),
                    navigateNext = navController::navigate
                )
            }
            composable<ArticleDetailRout> {
                val rout = it.savedStateHandle.toRoute<ArticleDetailRout>()
                val viewModel = hiltViewModel<ArticleDetailViewModel, ArticleDetailViewModelFactory>(
                    creationCallback = { factory ->
                        factory.create(rout.articleId)
                    }
                )
                ArticleDetailScreen(viewModel = viewModel,navigateBack = navController::navigateUp)
            }
        }
    }
}