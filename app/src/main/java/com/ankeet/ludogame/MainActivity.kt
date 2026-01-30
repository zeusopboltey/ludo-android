package com.ankeet.ludogame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ankeet.ludogame.model.Dice

class MainActivity : AppCompatActivity() {

    private lateinit var dice: Dice
    private lateinit var tvDiceValue: TextView
    private lateinit var btnRollDice: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize game objects
        dice = Dice()

        // Bind UI
        tvDiceValue = findViewById(R.id.tvDiceValue)
        btnRollDice = findViewById(R.id.btnRollDice)

        // Dice roll action
        btnRollDice.setOnClickListener {
            val value = dice.roll()
            tvDiceValue.text = "Dice: $value"
        }
    }
}
