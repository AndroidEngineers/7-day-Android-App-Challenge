package com.example.anifetch.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(query:TextFieldValue,
              onQueryChange:(TextFieldValue)->Unit,
              modifier: Modifier = Modifier)
{
    BasicTextField(value = query,
        onValueChange =onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp),
        textStyle = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onSurface)
    )
}