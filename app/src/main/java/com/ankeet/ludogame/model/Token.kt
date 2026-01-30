package com.ankeet.ludogame.model

data class Token(
    val id: Int,
    val color: PlayerColor,
    var position: Int = -1,        // -1 = HOME
    var stepsMoved: Int = 0,        // steps completed on main path
    var state: TokenState = TokenState.HOME
)
