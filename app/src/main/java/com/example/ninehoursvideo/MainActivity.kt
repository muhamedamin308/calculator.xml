package com.example.ninehoursvideo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ninehoursvideo.age.CalculateAge
import com.example.ninehoursvideo.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rand = Random(10).nextInt()
        val person = Person()
        person.age = 12
        binding.apply {
            button.setOnClickListener {
                textView.text = "Hello $rand"
                val int = Intent(this@MainActivity, CalculateAge::class.java)
                startActivity(int)
            }
        }
    }
}