package com.merp.jet.my.pdf.reader.app.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merp.jet.my.pdf.reader.app.R
import com.merp.jet.my.pdf.reader.app.components.EmailInput
import com.merp.jet.my.pdf.reader.app.components.PasswordInput
import com.merp.jet.my.pdf.reader.app.components.ReaderLogo

@Composable
fun LoginScreen(navController: NavController) {

    val showLoginForm = rememberSaveable { mutableStateOf(true) }

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Top,
        Alignment.CenterHorizontally
    ) {
        Spacer(
            Modifier
                .height(100.dp)
                .fillMaxWidth()
        )
        ReaderLogo()
        Spacer(modifier = Modifier.height(50.dp))
        if (showLoginForm.value) {
            UserForm(loading = false, isCreateAccount = false) { email, password ->
                // TODO: Login FB account
            }
        } else {
            UserForm(loading = false, isCreateAccount = true) { email, password ->
                // TODO: Create FB account
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            Modifier,
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            val text =
                if (showLoginForm.value)
                    stringResource(R.string.lbl_sign_up)
                else
                    stringResource(R.string.lbl_login)

            Text(text = stringResource(R.string.lbl_new_user))
            Text(
                text = text, Modifier
                    .padding(start = 5.dp)
                    .clickable { showLoginForm.value = !showLoginForm.value },
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun UserForm(
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = { email, password -> }
) {
    val email: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val password: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val passwordVisibility: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest: FocusRequester = FocusRequester.Default
    val keyboardController: SoftwareKeyboardController = LocalSoftwareKeyboardController.current!!
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        if (isCreateAccount) {
            Text(
                text = stringResource(R.string.lbl_create_account_note),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
        }

        // Email
        EmailInput(
            modifier = Modifier,
            emailState = email,
            label = stringResource(R.string.lbl_email),
            enable = !loading,
            onAction = KeyboardActions {
                passwordFocusRequest.requestFocus()
            }
        )

        // Password
        PasswordInput(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            label = stringResource(R.string.lbl_password),
            enable = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
            }
        )

        // Signup / Login
        SwitchButton(
            text = if (isCreateAccount) stringResource(R.string.lbl_create_account)
            else stringResource(R.string.lbl_login),
            loading = loading,
            validInputs = valid
        ) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController.hide()
        }
    }
}

@Composable
fun SwitchButton(
    text: String,
    loading: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = !loading && validInputs,
        shape = CircleShape,
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
    ) {
        if (loading) CircularProgressIndicator(Modifier.size(25.dp))
        else Text(text = text)
    }
}