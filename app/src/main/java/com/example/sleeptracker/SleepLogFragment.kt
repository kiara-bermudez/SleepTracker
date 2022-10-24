package com.example.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class SleepLogFragment: Fragment() {

    private val logEntries = mutableListOf<SleepEntity2>()
    private lateinit var logRecyclerView: RecyclerView
    private lateinit var sleepAdapter: SleepAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_sleep_log, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        logRecyclerView = view.findViewById(R.id.log_recycler_view)
        sleepAdapter = SleepAdapter(view.context, logEntries)
        logRecyclerView.adapter = sleepAdapter

        lifecycleScope.launch {
            (activity?.application as SleepApplication).db.sleepDao().getAll().collect{ databaseList ->
                logEntries.clear()
                logEntries.addAll(databaseList)
                sleepAdapter.notifyDataSetChanged()
            }
        }

        logRecyclerView.layoutManager = LinearLayoutManager(view.context).also {
            val dividerItemDecoration = DividerItemDecoration(view.context, it.orientation)
            logRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        // Update the return statement to return the inflated view from above
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated

    }

    companion object {
        fun newInstance(): SleepLogFragment {
            return SleepLogFragment()
        }
    }

}