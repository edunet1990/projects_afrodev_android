package com.eduardo.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.eduardo.pokedex.utils.name
import java.util.*

class WelcomeActivity : AppCompatActivity() {

    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        supportActionBar?.hide()
        val loaginfTime = Handler().postDelayed({
            val intent = Intent(this@WelcomeActivity, HomePokemonList::class.java)
            startActivity(intent)
            finish()
        }, 3000)

        val user_name = findViewById<TextView>(R.id.user_name)
        val txt_starting = findViewById<TextView>(R.id.txt_starting)

        user_name.text = name +"!"
        txt_starting.text = "${updateTime()}"
        var count = 3
        // Update TextView every second
        handler.post(object : Runnable {
            override fun run() {
                handler.postDelayed(this, 1000)
                updateTime()
                txt_starting.text = "Starting your Pokedex in ${count--}..."
            }
        })
    }

    fun updateTime() : Calendar {
        //Set Current time
        val currentDate = Calendar.getInstance()

        //Set Envent Date
        val eventDate = Calendar.getInstance()
        val diff = eventDate.timeInMillis - currentDate.timeInMillis

        return eventDate
    }
}