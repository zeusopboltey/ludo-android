package com.ankeet.ludogame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ankeet.ludogame.model.*

class MainActivity : AppCompatActivity() {

    private lateinit var dice: Dice

    private lateinit var tvDiceValue: TextView
    private lateinit var tvCurrentPlayer: TextView
    private lateinit var btnRollDice: Button

    private val players = listOf(
        Player(PlayerColor.RED),
        Player(PlayerColor.BLUE),
        Player(PlayerColor.GREEN),
        Player(PlayerColor.YELLOW)
    )

    private var currentPlayerIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dice = Dice()

        tvDiceValue = findViewById(R.id.tvDiceValue)
        tvCurrentPlayer = findViewById(R.id.tvCurrentPlayer)
        btnRollDice = findViewById(R.id.btnRollDice)

        tvDiceValue.text = "Dice: -"
        updateCurrentPlayerUI()

        btnRollDice.setOnClickListener {
            handleDiceRoll()
        }
    }

    private fun handleDiceRoll() {
        val value = dice.roll()
        tvDiceValue.text = "Dice: $value"

        val player = players[currentPlayerIndex]

        if (value == 6) {
            handleSix(player)
            // same player continues
        } else {
            moveActiveToken(player, value)
            moveToNextPlayer()
        }
    }

    // 🎲 Dice = 6 logic
    private fun handleSix(player: Player) {
        val homeToken = player.tokens.firstOrNull {
            it.state == TokenState.HOME
        }

        if (homeToken != null) {
            homeToken.state = TokenState.ACTIVE
            homeToken.position =
                BoardPath.entryIndexByColor[player.color]!!
            homeToken.stepsMoved = 0

            println(
                "Token ${homeToken.id} of ${player.color} entered board at ${homeToken.position}"
            )
        } else {
            moveActiveToken(player, 6)
        }
    }

    // 🚶 Move ACTIVE token
    private fun moveActiveToken(player: Player, steps: Int) {
        val token = player.tokens.firstOrNull {
            it.state == TokenState.ACTIVE
        }

        if (token != null) {

            // Prevent overshoot beyond finish
            if (token.stepsMoved + steps > BoardPath.TOTAL_MAIN_PATH) {
                println("Move exceeds board limit for ${player.color}")
                return
            }

            val oldPos = token.position

            token.stepsMoved += steps
            token.position =
                BoardPath.nextPosition(token.position, steps)

            println(
                "Token ${token.id} of ${player.color} moved from $oldPos to ${token.position} " +
                        "(steps=${token.stepsMoved})"
            )

            // 🏁 Finish condition
            val homeEntry =
                BoardPath.homeStartIndexByColor[player.color]!!

            if (token.position == homeEntry &&
                token.stepsMoved == BoardPath.TOTAL_MAIN_PATH
            ) {
                token.state = TokenState.FINISHED
                println("🎉 Token ${token.id} of ${player.color} FINISHED")
            }

        } else {
            println("No ACTIVE token to move for ${player.color}")
        }
    }

    private fun moveToNextPlayer() {
        currentPlayerIndex =
            (currentPlayerIndex + 1) % players.size
        updateCurrentPlayerUI()
    }

    private fun updateCurrentPlayerUI() {
        val player = players[currentPlayerIndex]
        tvCurrentPlayer.text =
            "Current Player: ${player.color}"
    }
}
