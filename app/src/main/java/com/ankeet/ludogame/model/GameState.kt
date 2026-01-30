package com.ankeet.ludogame.model

data class GameState(
    val currentPlayer: PlayerColor,
    val diceValue: Int,
    val isGameOver: Boolean = false
)
