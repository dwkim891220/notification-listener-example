package com.example.notificationlistenerexample

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.example.notificationlistenerexample.databinding.ActivityMainBinding
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLogger()
        initNotificationListenPermission()
    }

    private fun initLogger(){
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    private fun initNotificationListenPermission(){
        val cn = ComponentName(this, NotificationListenerTestService::class.java)
        val flat: String? = Settings.Secure.getString(
            contentResolver,
            "enabled_notification_listeners"
        )
        val enabled = flat != null && flat.contains(cn.flattenToString())

        if(!enabled) {
            startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }
    }
}