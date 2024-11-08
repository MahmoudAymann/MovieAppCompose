package com.mdi.movie.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mdi.movie.R
import com.mdi.movie.core.navigation.MovieAppNavGraph
import com.mdi.movie.core.ui.components.AppActionBar
import com.mdi.movie.core.ui.theme.MovieAppTheme
import com.mdi.movie.features.movieslist.data.model.MoviesType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                MainContent(mainViewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun MainContent(mainViewModel: MainViewModel) {
    var selectedTypeState by remember { mutableStateOf(MoviesType.Popular) }
    var isDropdownExpanded by remember { mutableStateOf(false) }


    Scaffold(topBar = {
        AppActionBar(stringResource(id = R.string.app_name),
            onBackIconClick = {

            },
            onTypeSelected = { selectedType ->
                selectedTypeState = selectedType
                //send the action to movies list screen
                mainViewModel.updateSelectedType(selectedType)
            },
            isDropdownExpanded = isDropdownExpanded,
            onDropdownToggle = {
                isDropdownExpanded = !isDropdownExpanded
            })
    }) { padding ->
        MovieAppNavGraph(
            modifier = Modifier
                .padding(padding)
                .then(Modifier.padding(8.dp)),
            mainViewModel = mainViewModel
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        MainContent(hiltViewModel())
    }
}