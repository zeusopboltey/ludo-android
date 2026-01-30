package com.ankeet.ludogame.model

class Player(
    val color: PlayerColor
) {
    val tokens: List<Token> = List(4) { index ->
        Token(
            id = index,
            color = color
        )
    }
}
