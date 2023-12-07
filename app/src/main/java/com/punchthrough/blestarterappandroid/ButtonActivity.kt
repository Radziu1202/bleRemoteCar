package com.punchthrough.blestarterappandroid

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import com.punchthrough.blestarterappandroid.ble.ConnectionManager

class ButtonActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)
        val rightButton: Button = findViewById(R.id.right_button)
        val leftButton: Button = findViewById(R.id.left_button)
        // Retrieve the BluetoothCharacteristic from the Intent
        val characteristic = intent.getParcelableExtra<BluetoothGattCharacteristic>("characteristic_key")
        val device = intent.getParcelableExtra<BluetoothDevice>("device")


            rightButton.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(view: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            // Handle button press
                            onRightButtonPressed(characteristic,device)
                            return true
                        }
                        MotionEvent.ACTION_UP -> {
                            // Handle button release
                            onRightButtonReleased(characteristic,device)
                            return true
                        }
                    }
                    return false
                }
            })

            leftButton.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(view: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            // Handle button press
                            onLeftButtonPressed(characteristic,device)
                            return true
                        }
                        MotionEvent.ACTION_UP -> {
                            // Handle button release
                            onLeftButtonReleased(characteristic,device)
                            return true
                        }
                    }
                    return false
                }
            })
        }

        private fun onRightButtonPressed(characteristic: BluetoothGattCharacteristic,device:BluetoothDevice) {
            ConnectionManager.writeCharacteristic(device, characteristic, "prawo start".toByteArray());
        }

        private fun onRightButtonReleased(characteristic: BluetoothGattCharacteristic,device:BluetoothDevice) {
            ConnectionManager.writeCharacteristic(device, characteristic, "prawo stop".toByteArray());
        }

        private fun onLeftButtonPressed(characteristic: BluetoothGattCharacteristic,device:BluetoothDevice) {
            ConnectionManager.writeCharacteristic(device, characteristic, "lewo start".toByteArray());
        }

        private fun onLeftButtonReleased(characteristic: BluetoothGattCharacteristic,device:BluetoothDevice) {
            ConnectionManager.writeCharacteristic(device, characteristic, "lewo stop".toByteArray());
        }


            // Do something with the characteristic





}