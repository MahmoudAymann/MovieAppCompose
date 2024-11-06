package com.mdi.movie.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdi.movie.R
import com.mdi.movie.core.navigation.MovieAppNavGraph
import com.mdi.movie.core.ui.components.AppActionBar
import com.mdi.movie.core.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    Scaffold(topBar = { AppActionBar(stringResource(id = R.string.app_name)) }) { padding ->
        MovieAppNavGraph(modifier = Modifier
            .padding(padding)
            .then(Modifier.padding(8.dp)))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        MainContent()
    }
}