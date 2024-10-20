package com.vpdevs.datastoredemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLoginButtonClicked: (String, String) -> Unit
) {

    val userName = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login",
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(50.dp))

        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            label = {
                Text("UserName")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            value = userName.value,
            onValueChange = {
                userName.value = it
            }
        )
        Spacer(modifier = Modifier.height(50.dp))

        TextField(
            label = {
                Text("Password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            value = password.value,
            onValueChange = {
                password.value = it
            }
        )
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                onLoginButtonClicked(userName.value, password.value)
            }
        ) {
            Text("Login")
        }

    }

}
