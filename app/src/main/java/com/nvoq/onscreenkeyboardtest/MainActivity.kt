package com.nvoq.onscreenkeyboardtest

import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.os.Bundle
import android.provider.Settings
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var cname = "MainActivity"

    private var startTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart: Button? = findViewById(R.id.buttonStart)

        buttonStart?.setOnClickListener {
            val startSettings = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
            startSettings.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startSettings.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startSettings.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)

            startSettings.component

            applicationContext.startActivity(startSettings)

            startTime = System.currentTimeMillis()
            println(cname+".onCreate startTime " + startTime)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println(cname + ".onDestroy")
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val view = super.onCreateView(name, context, attrs)

        applicationContext.contentResolver.registerContentObserver(
                Settings.Secure.CONTENT_URI, true, mSecureSettingsChanged)

        return view
    }

    private val mSecureSettingsChanged: ContentObserver = object : ContentObserver(null) {
        override fun deliverSelfNotifications(): Boolean {
            return false
        }

        override fun onChange(selfChange: Boolean) {
            val endTimeMillis = System.currentTimeMillis()
            println(cname + ".mSecureSettingsChanged.onChange step is completed " + endTimeMillis + " elapsed " + (endTimeMillis  - startTime))
        }
    }
}