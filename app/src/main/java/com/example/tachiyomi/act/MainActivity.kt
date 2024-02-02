package com.example.tachiyomi.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.tachiyomi.R
import com.example.tachiyomi.frg.FragmentTrangChu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = findViewById<TextView>(R.id.chu)
        text.setOnClickListener {
            Toast.makeText(this, "chu", Toast.LENGTH_SHORT).show()
        }
        supportFragmentManager.beginTransaction().replace(R.id.ln_main, FragmentTrangChu()).commit()
    }
}