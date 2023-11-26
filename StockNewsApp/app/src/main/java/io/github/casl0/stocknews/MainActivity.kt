package io.github.casl0.stocknews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.casl0.stocknews.ui.theme.StockNewsAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockNewsAppTheme {
                StockNewsApp(viewModel)
            }
        }
    }
}

@Composable
private fun StockNewsApp(viewModel: MainViewModel) {
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(snackbarHostState) {
        viewModel.hasMessage.collect { message ->
            snackbarHostState.showSnackbar(
                    message = message.toString(),
                    duration = SnackbarDuration.Short,
            )
        }
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) {
        Column(
                modifier = Modifier
                        .padding(it)
                        .verticalScroll(rememberScrollState())
        ) {
            val uiState by viewModel.uiState.collectAsState()
            uiState.stockCategories.forEach { category ->
                StockCategoryItem(
                        categoryName = category.categoryName,
                        isSubscribed = category.isSubscribed,
                        onCheckedChange = { checked ->
                            viewModel.toggleSubscribed(
                                    category.topicName,
                                    checked,
                            )
                        },
                        modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun StockCategoryItem(
        categoryName: String,
        isSubscribed: Boolean,
        onCheckedChange: (Boolean) -> Unit,
        modifier: Modifier = Modifier
) {
    Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
                text = categoryName,
                modifier = Modifier.weight(1f),
        )
        Switch(
                checked = isSubscribed,
                onCheckedChange = onCheckedChange,
        )
    }
}

@Preview
@Composable
private fun PreviewStockCategoryItem() {
    StockNewsAppTheme {
        StockCategoryItem(
                categoryName = "Technology",
                isSubscribed = false,
                onCheckedChange = {}
        )
    }
}
