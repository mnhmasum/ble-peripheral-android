package com.mnh.bleperipheralsimulator

import PeripheralViewModel
import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.mnh.bleperipheralsimulator.ui.theme.BLEPeripheralSimulatorTheme

class MainActivity : ComponentActivity() {

    private var viewModel: PeripheralViewModel? = null

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_ADVERTISE, Manifest.permission.BLUETOOTH_CONNECT])
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[PeripheralViewModel::class.java]
        viewModel?.startPeripheral()

        enableEdgeToEdge()
        setContent {
            BLEPeripheralSimulatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_ADVERTISE, Manifest.permission.BLUETOOTH_CONNECT])
    override fun onDestroy() {
        super.onDestroy()
        viewModel?.stopPeripheral()
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BLEPeripheralSimulatorTheme {
        Greeting("Android")
    }
}