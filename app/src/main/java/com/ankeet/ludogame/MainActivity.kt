package com.ankeet.ludogame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ankeet.ludogame.model.Dice
import com.ankeet.ludogame.model.PlayerColor

class MainActivity : AppCompatActivity() {

    private lateinit var dice: Dice
    private lateinit var tvDiceValue: TextView
    private lateinit var tvCurrentPlayer: TextView
    private lateinit var btnRollDice: Button

    private val players = listOf(
        PlayerColor.RED,
        PlayerColor.BLUE,
        PlayerColor.GREEN,
        PlayerColor.YELLOW
    )

    private var currentPlayerIndex = 0
    private var hasExtraTurn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dice = Dice()

        tvDiceValue = findViewById(R.id.tvDiceValue)
        tvCurrentPlayer = findViewById(R.id.tvCurrentPlayer)
        btnRollDice = findViewById(R.id.btnRollDice)

        updateCurrentPlayerUI()

        btnRollDice.setOnClickListener {
            val value = dice.roll()
            tvDiceValue.text = "Dice: $value"

            if (value == 6) {
                hasExtraTurn = true
            } else {
                if (hasExtraTurn) {
                    hasExtraTurn = false
                } else {
                    moveToNextPlayer()
                }
            }
        }
    }

    private fun moveToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
        updateCurrentPlayerUI()
    }

    private fun updateCurrentPlayerUI() {
        val player = players[currentPlayerIndex]
        tvCurrentPlayer.text = "Current Player: $player"
    }
}
