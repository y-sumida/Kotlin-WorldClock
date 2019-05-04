package com.example.worldclock

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_timezone_select.*

class TimezoneSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timezone_select)

        setResult(Activity.RESULT_CANCELED)

        val adapter = TimezoneAdapter(this)
        clockList.adapter = adapter

        clockList.setOnItemClickListener { _, _, position, _ ->
            val timezone = adapter.getItem(position)

            val result = Intent()
            result.putExtra("timezone", timezone)

            setResult(Activity.RESULT_OK, result)

            finish()
        }
    }
}
