package com.example.worldclock

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_timezone_row.view.*
import java.util.*

class TimezoneAdapter(private val context: Context,
                      private val timezones: Array<String> = TimeZone.getAvailableIDs()) : BaseAdapter() {
    private val inflater = LayoutInflater.from(context)

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: createView(parent)

        val timezoneId = getItem(position)
        val timezone = TimeZone.getTimeZone(timezoneId)

        val viewHolder = view.tag as ViewHolder

        viewHolder.name.text = "${timezone.displayName}(${timezone.id}"

        viewHolder.clock.timeZone = timezone.id

        return view
    }

    override fun getItem(position: Int) = timezones[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = timezones.size

    private class ViewHolder(view: View) {
        val name = view.timezone
        val clock = view.clock
    }

    private fun createView(parent: ViewGroup?) : View {
        val view = inflater.inflate(R.layout.list_timezone_row, parent, false)
        view.tag = ViewHolder(view)
        return view
    }
}