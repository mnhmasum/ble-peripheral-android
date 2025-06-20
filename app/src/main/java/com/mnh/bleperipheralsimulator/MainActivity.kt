package com.mnh.bleperipheralsimulator

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mnh.bleperipheralsimulator.ui.theme.AppTheme
import com.mnh.bleperipheralsimulator.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_ADVERTISE, Manifest.permission.BLUETOOTH_CONNECT])
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface()
            }
        }
    }

}

@SuppressLint("MissingPermission")
@Composable
private fun Surface(viewModel: PeripheralViewModel = viewModel()) {
    val centralState = viewModel.centralState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startPeripheral()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopPeripheral()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        MainLayout(
            value = centralState.value,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun MainLayout(value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Current State:")
        Text(text = value, style = Typography.headlineMedium)
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        MainLayout("Preview State", Modifier.fillMaxSize())
    }
}