package com.example.sleeptracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sleeptracker.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

// Part 2
class MainActivity : AppCompatActivity() {

    private val sleepEntries = mutableListOf<SleepEntity2>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recyclerView = findViewById(R.id.recyclerView)

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {

            val intent = Intent(this, AddSleepActivity::class.java)
            startActivity(intent)
        }

        val sleepAdapter = SleepAdapter(this, sleepEntries)
        recyclerView.adapter = sleepAdapter

        lifecycleScope.launch {
            (application as SleepApplication).db.sleepDao().getAll().collect{ databaseList ->
                sleepEntries.clear()
                sleepEntries.addAll(databaseList)
                sleepAdapter.notifyDataSetChanged()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            recyclerView.addItemDecoration(dividerItemDecoration)
        }
    }
}