/*
 * Copyright 2020 Shreyas Patil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shreyaspatil.noty.composeapp.component.text

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.shreyaspatil.noty.utils.validator.AuthValidator

@Composable
fun UsernameTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onTextChange: (TextFieldValue<String>) -> Unit,
) {
    var startedTyping by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }

    NotyTextField(
        value = value,
        label = "Username",
        onValueChange = {
            isValid = AuthValidator.isValidUsername(it)
            onTextChange(if (isValid) TextFieldValue.Valid(it) else TextFieldValue.Invalid(it))
            if (!startedTyping) {
                startedTyping = true
            }
        },
        modifier = modifier,
        leadingIcon = { Icon(Icons.Outlined.Person, "User") },
        isError = !isValid && startedTyping,
        helperText = "Minimum 4 characters required"
    )
}
