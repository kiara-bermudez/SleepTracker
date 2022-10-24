package com.example.sleeptracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SleepAdapter (private val context: Context, private val mEntries: List<SleepEntity2>) : RecyclerView.Adapter<SleepAdapter.ViewHolder>()
{
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val dateTextView = itemView.findViewById<TextView>(R.id.dateTV)
        val totalSleepTextView = itemView.findViewById<TextView>(R.id.totalSleepTV)
        //val sleepTimesTextView = itemView.findViewById<TextView>(R.id.sleepTimesTV)

        fun bind(entry: SleepEntity2) {
            dateTextView.text = entry.date
            totalSleepTextView.text = entry.hours.toString()
            //sleepTimesTextView.text = entry.endTime.toString()
        }
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.fragment_sleep_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: SleepAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val entry = mEntries[position]
        // Set item views based on your views and data model
        viewHolder.bind(entry)
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mEntries.size
    }
}