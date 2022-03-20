package com.example.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bManager = this.getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        val bAdapter = bManager.getAdapter()
        if (bAdapter == null){
            bluetoothStatusTv.text = "Bluetooth is not available"
        }else{
            bluetoothStatusTv.text = "Bluetooth is available"
        }
        if (bAdapter.isEnabled){
            bluetoothTv.setImageResource(R.drawable.ic_bluetooth_on)
        }else{
            bluetoothTv.setImageResource(R.drawable.ic_bluetooth_off)

        }

        turnOnBtn.setOnClickListener{
            if(bAdapter.isEnabled){
                Toast.makeText(this,"Already On",Toast.LENGTH_SHORT).show()
                bluetoothTv.setImageResource(R.drawable.ic_bluetooth_on)
            }else{
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    bAdapter.enable()
                    bluetoothTv.setImageResource(R.drawable.ic_bluetooth_on)
                    Toast.makeText(this,"Bluetooth turned On",Toast.LENGTH_SHORT).show()
                }


            }

        }
        turnOffBtn.setOnClickListener{
            if(!bAdapter.isEnabled){
                Toast.makeText(this,"Already off",Toast.LENGTH_SHORT).show()
                bluetoothTv.setImageResource(R.drawable.ic_bluetooth_off)
            }else{
                bAdapter.disable()
                Toast.makeText(this,"Bluetooth turned off",Toast.LENGTH_SHORT).show()
                bluetoothTv.setImageResource(R.drawable.ic_bluetooth_off)
            }

        }


    }
}