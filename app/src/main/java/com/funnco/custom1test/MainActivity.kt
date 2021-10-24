package com.funnco.custom1test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.funnco.custom1test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.customview.setMaxSectors(9)
        binding.customview.setCurrentSector(9)

        setContentView(binding.root)
    }
}