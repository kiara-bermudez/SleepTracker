package com.example.sleeptracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddSleepActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_sleep_item)

        val dateET = findViewById<EditText>(R.id.editTextDate)
        val startTimeET = findViewById<EditText>(R.id.editTextHours)
        //val endTimeET = findViewById<EditText>(R.id.editTextEndTime)
        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            lifecycleScope.launch(IO) {
                (application as SleepApplication).db.sleepDao().insert(
                    SleepEntity2(
                        date = dateET.text.toString(),
                        hours = startTimeET.text.toString()
                        //endTime = endTimeET.text.toString()
                    )
                )
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}