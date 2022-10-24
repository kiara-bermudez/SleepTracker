package com.example.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

private const val TAG = "SleepStatsFragment"

class SleepStatsFragment : Fragment() {

    private val logEntries = mutableListOf<SleepEntity2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_sleep_stats, container, false)

        val avgSleepTextView = view.findViewById<TextView>(R.id.avgSleepTime)
        val minSleepTextView = view.findViewById<TextView>(R.id.minSleepTime)
        val maxSleepTextView = view.findViewById<TextView>(R.id.maxSleepTime)

        lifecycleScope.launch {
            (activity?.application as SleepApplication).db.sleepDao().getAll().collect{ databaseList ->
                logEntries.clear()
                logEntries.addAll(databaseList)
                calcStats(avgSleepTextView, minSleepTextView, maxSleepTextView)
            }
        }


        // Update the return statement to return the inflated view from above
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated

    }

    fun calcStats(avgSleepTextView: TextView, minSleepTextView: TextView, maxSleepTextView: TextView) {
        var minHrs = 100
        var maxHrs = -1
        var sumHrs = 0
        var numEntries = 0
        for (entry in logEntries) {
            var hrs = entry.hours.toInt()
            if ( hrs < minHrs) {
                minHrs = hrs
            } else if (hrs > maxHrs) {
                maxHrs = hrs
            }

            sumHrs += hrs
            numEntries++
        }

        var avgHrs = 0
        if (numEntries != 0) {
            avgHrs = sumHrs / numEntries
        }

        avgSleepTextView.text = avgHrs.toString()
        minSleepTextView.text = minHrs.toString()
        maxSleepTextView.text = maxHrs.toString()
    }

    companion object {
        fun newInstance(): SleepStatsFragment {
            return SleepStatsFragment()
        }
    }
}