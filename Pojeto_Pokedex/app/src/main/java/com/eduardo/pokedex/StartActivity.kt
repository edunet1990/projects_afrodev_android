package com.eduardo.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.eduardo.pokedex.utils.name

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        supportActionBar?.hide()

        val btn_name = findViewById<Button>(R.id.btn_name)
        val text_name = findViewById<EditText>(R.id.text_name)
        //val user_name = findViewById<TextView>(R.id.user_name)
        // Botão Welcome
        btn_name.setOnClickListener{

            val intent = Intent(this@StartActivity, WelcomeActivity::class.java)
            startActivity(intent)
            name = text_name.text.toString()
        }
    }
}