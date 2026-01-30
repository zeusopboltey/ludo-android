package com.ankeet.ludogame.model

data class Token(
    val id: Int,
    val color: PlayerColor,
    var position: Int = -1, // -1 means HOME
    var state: TokenState = TokenState.HOME
)
