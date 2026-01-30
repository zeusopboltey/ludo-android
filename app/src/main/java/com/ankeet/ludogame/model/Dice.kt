package com.ankeet.ludogame.model

import kotlin.random.Random

class Dice {
    fun roll(): Int = Random.nextInt(1, 7)
}
