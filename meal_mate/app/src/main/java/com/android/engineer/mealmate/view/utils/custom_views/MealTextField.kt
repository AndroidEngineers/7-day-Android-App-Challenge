package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.android.engineer.mealmate.ui.theme.Transparent

@Composable
fun MealTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    errorMsg: String = ""
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Transparent,
            unfocusedContainerColor = Transparent
        ),
        leadingIcon = {
            Icon(
                icon,
                contentDescription = label
            )
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        modifier = modifier.fillMaxWidth(),
        isError = isError,
        supportingText = {
            if(isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMsg,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        trailingIcon = {
            if (isError)
                Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colorScheme.error)
        },
    )
}

@Composable
@Preview(showBackground = true)
fun MealTextFieldPreview() {
    val usernameState = remember { mutableStateOf("") }
    MealTextField(
        value = usernameState.value,
        onValueChange = { usernameState.value = it },
        label = "Username",
        icon = Icons.Filled.Person
    )
}
