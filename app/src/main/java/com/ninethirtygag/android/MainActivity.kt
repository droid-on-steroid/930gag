package com.ninethirtygag.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ninethirtygag.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, MainFragment.newInstance()).commit()
    }
}