package com.mnh.bleperipheralsimulator

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeripheralViewModel @Inject constructor(private val peripheralGattRepository: PeripheralGattRepository) : ViewModel() {

    private val TAG = "PeripheralViewModel"

    val centralState = peripheralGattRepository.centralState

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_ADVERTISE, Manifest.permission.BLUETOOTH_CONNECT])
    fun startPeripheral() {
        peripheralGattRepository.startPeripheral()
    }

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_ADVERTISE, Manifest.permission.BLUETOOTH_CONNECT])
    fun stopPeripheral() {
        peripheralGattRepository.stopPeripheral()
    }

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_ADVERTISE, Manifest.permission.BLUETOOTH_CONNECT])
    override fun onCleared() {
        super.onCleared()
        peripheralGattRepository.stopPeripheral()
    }
}
