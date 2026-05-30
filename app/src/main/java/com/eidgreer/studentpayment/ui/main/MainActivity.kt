package com.eidgreer.studentpayment.ui.main

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.eidgreer.studentpayment.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val groupsButton = findViewById<Button>(R.id.groupsButton)
        val reportsButton = findViewById<Button>(R.id.reportsButton)
        val settingsButton = findViewById<Button>(R.id.settingsButton)

        groupsButton.setOnClickListener {
            // Navigate to Groups Fragment
        }

        reportsButton.setOnClickListener {
            // Navigate to Reports Fragment
        }

        settingsButton.setOnClickListener {
            // Navigate to Settings Fragment
        }
    }
}
