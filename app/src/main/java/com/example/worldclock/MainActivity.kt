package com.example.worldclock

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timezone = TimeZone.getDefault()

        defaultTimezone.text = timezone.displayName

        add.setOnClickListener {
            val intent = Intent(this, TimezoneSelectActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }
}
