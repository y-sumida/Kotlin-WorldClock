package com.example.worldclock

import android.app.Activity
import android.content.Context
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

        showWorldClocks()
    }

    private fun showWorldClocks() {
        val pref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val timezones = pref.getStringSet("timezone", setOf())

        selectedClockList.adapter = TimezoneAdapter(this, timezones.toTypedArray())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1
            && resultCode == Activity.RESULT_OK
            && data != null) {

            val timezone = data.getStringExtra("timezone")

            val pref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val timezones = pref.getStringSet("timezone", mutableSetOf())

            timezones.add(timezone)

            pref.edit().putStringSet("timezone", timezones).apply()

            showWorldClocks()
        }
    }
}
