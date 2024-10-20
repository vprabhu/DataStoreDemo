package com.vpdevs.datastoredemo.ui.screens.demo

import android.content.res.Configuration
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vpdevs.datastoredemo.ui.theme.DataStoreDemoTheme
import com.vpdevs.datastoredemo.viewmodel.DSViewModel


@Composable
fun DemoScreen() {

    val viewModel: DSViewModel = viewModel()
    val newValue = remember { mutableStateOf("") }

    val playerList by viewModel.playerList.collectAsState()
//    viewModel.getValue()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            value = newValue.value,
            onValueChange = {
                newValue.value = it
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.incrementCounter(newValue.value)
            }
        ) {
            Text(text = "Save Data")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = playerList,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displaySmall,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
//                viewModel.getValue()
            }
        ) {
            Text(text = "Get Data")
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    DataStoreDemoTheme {
        DemoScreen()
    }
}
