package com.mdi.movie.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mdi.movie.R

@Composable
fun ErrorDialog(
    title: String = stringResource(R.string.error),
    message: String,
    onRetryClick: (() -> Unit)? = null,
    onDismissClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f) // Width less than the full screen
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Icon at the top
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Message
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    onDismissClick?.let {
                        TextButton(onClick = it) {
                            Text(stringResource(R.string.dismiss))
                        }
                    }
                    onRetryClick?.let {
                        TextButton(onClick = it) {
                            Text(stringResource(R.string.retry))
                        }
                    }
                }
            }
        }
    }
}