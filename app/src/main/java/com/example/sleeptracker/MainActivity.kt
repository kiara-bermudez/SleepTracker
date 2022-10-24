package com.example.sleeptracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sleeptracker.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

// Part 2
class MainActivity : AppCompatActivity() {

    private val sleepEntries = mutableListOf<SleepEntity2>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {

            val intent = Intent(this, AddSleepActivity::class.java)
            startActivity(intent)
        }

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val sleepLogFragment: Fragment = SleepLogFragment()
        val sleepStatsFragment: Fragment = SleepStatsFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_log -> fragment = sleepLogFragment
                R.id.nav_stats -> fragment = sleepStatsFragment
            }
            fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_log

    }
}