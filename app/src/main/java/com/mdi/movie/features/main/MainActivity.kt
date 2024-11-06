package com.mdi.movie.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mdi.movie.R
import com.mdi.movie.core.ui.components.AppActionBar
import com.mdi.movie.core.ui.theme.MovieAppTheme

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
    Scaffold(topBar = { AppActionBar(stringResource(id = R.string.app_name)) }) { _ ->

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        MainContent()
    }
}