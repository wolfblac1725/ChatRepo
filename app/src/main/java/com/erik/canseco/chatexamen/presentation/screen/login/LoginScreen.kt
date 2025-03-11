package com.erik.canseco.chatexamen.presentation.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erik.canseco.chatexamen.R
import com.erik.canseco.chatexamen.ui.theme.ChatExamenTheme

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit
) {
    val state = viewModel.state

        LoginScreen(
            state = state,
            onUserNameChanged = {
                viewModel.onUserNameChanged(it)
            },
            onPasswordChanged = {
                viewModel.onPasswordChanged(it)
            },
            onLoginClicked = {
                viewModel.onLoginClicked(
                    navigateToHome,
                )
            },
            onErrorMessage = {
                viewModel.onErrorMessageSet()
            },
        )
}


@Composable
fun LoginScreen(
    state: LoginDataState,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onErrorMessage: () -> Unit,
) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isErrorLogin) {
                Toast.makeText(
                    LocalContext.current,
                    R.string.login_error,
                    Toast.LENGTH_SHORT
                ).show()
                onErrorMessage()
            }
            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = state.userName,
                    onValueChange = {
                        onUserNameChanged(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = {
                        Text(
                            text = stringResource(R.string.user_name),
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = stringResource(R.string.user_name),
                        )
                    },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)

                )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = {
                        onPasswordChanged(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = {
                        Text(
                            text = stringResource(R.string.password),
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Password,
                            contentDescription = stringResource(R.string.user_name),
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)


                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        onLoginClicked()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    enabled = state.isLoginButtonEnabled()
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(R.string.login),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
            }

        }

}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreviewLight() {
    ChatExamenTheme {
        LoginScreen(
            LoginDataState(),
            {},
            {},
            {},
            {},
        )
    }
}
