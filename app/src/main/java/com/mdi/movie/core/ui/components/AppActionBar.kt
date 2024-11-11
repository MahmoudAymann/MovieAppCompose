package com.mdi.movie.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mdi.movie.R
import com.mdi.movie.features.movieslist.data.model.MoviesType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppActionBar(
    title: String?,
    onBackIconClick: (() -> Unit)? = null,
    onTypeSelected: (MoviesType) -> Unit,
    isDropdownExpanded: Boolean = false,
    showDropDownMenu: Boolean = false,
    onDropdownToggle: () -> Unit = {}
) {
    TopAppBar(title = { Text(title.orEmpty()) }, navigationIcon = {
        onBackIconClick?.let {
            IconButton(onClick = { onBackIconClick.invoke() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    }, actions = {
        // Add the dropdown menu button
        if (showDropDownMenu) Box {
            IconButton(onClick = onDropdownToggle) {
                Icon(Icons.Default.MoreVert, contentDescription = "Filter Options")
            }
            DropdownMenu(
                expanded = isDropdownExpanded, onDismissRequest = onDropdownToggle
            ) {
                MoviesType.entries.forEach { type ->
                    DropdownMenuItem(text = {
                        Text(text = type.name.replaceFirstChar { it.uppercase() })
                    }, onClick = {
                        onTypeSelected(type)
                        onDropdownToggle() // Close the dropdown after selection
                    })
                }
            }
        }
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = Color.White,
        actionIconContentColor = Color.White
    )
    )
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAppActionBar() {
    MaterialTheme {
        AppActionBar(title = stringResource(R.string.app_name),
            onBackIconClick = {},
            onTypeSelected = {},
            isDropdownExpanded = false,
            onDropdownToggle = {})
    }
}