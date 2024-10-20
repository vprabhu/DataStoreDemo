package com.vpdevs.datastoredemo.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vpdevs.datastoredemo.ui.theme.DataStoreDemoTheme

@Composable
fun HomeScreen(
    userName: String,
    loginTimes: Int,
    onLogoutEvent: () -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome $userName",
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(75.dp))
        Text(
            text = "$userName have logged in $loginTimes times"
        )
        Spacer(modifier = Modifier.height(75.dp))
        Button(
            onClick = {
                onLogoutEvent()
            }
        ) {
            Text(
                text = "Logout"
            )
        }

    }


}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    DataStoreDemoTheme() {
        HomeScreen("Vicky", 8){

        }
    }
}
