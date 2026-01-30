package com.ankeet.ludogame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ankeet.ludogame.model.Dice
import com.ankeet.ludogame.model.Player
import com.ankeet.ludogame.model.PlayerColor

class MainActivity : AppCompatActivity() {

    // Core game objects
    private lateinit var dice: Dice

    // UI elements
    private lateinit var tvDiceValue: TextView
    private lateinit var tvCurrentPlayer: TextView
    private lateinit var btnRollDice: Button

    // Players with tokens
    private val players = listOf(
        Player(PlayerColor.RED),
        Player(PlayerColor.BLUE),
        Player(PlayerColor.GREEN),
        Player(PlayerColor.YELLOW)
    )

    private var currentPlayerIndex = 0

    // Tracks extra turn earned by rolling 6
    private var hasExtraTurn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize dice
        dice = Dice()

        // Bind UI
        tvDiceValue = findViewById(R.id.tvDiceValue)
        tvCurrentPlayer = findViewById(R.id.tvCurrentPlayer)
        btnRollDice = findViewById(R.id.btnRollDice)

        // Initial UI state
        updateCurrentPlayerUI()
        tvDiceValue.text = "Dice: -"

        // (Optional debug log)
        players.forEach { player ->
            println("${player.color} has ${player.tokens.size} tokens")
        }

        // Dice roll logic
        btnRollDice.setOnClickListener {
            val value = dice.roll()
            tvDiceValue.text = "Dice: $value"

            if (value == 6) {
                // Player earns extra turn
                hasExtraTurn = true
            } else {
                if (hasExtraTurn) {
                    // Extra turn consumed, stay on same player
                    hasExtraTurn = false
                } else {
                    // Normal turn ends
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
        tvCurrentPlayer.text = "Current Player: ${player.color}"
    }
}
