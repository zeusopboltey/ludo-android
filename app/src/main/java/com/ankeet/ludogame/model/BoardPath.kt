package com.ankeet.ludogame.model

/**
 * Represents the main circular Ludo board path.
 * Total positions = 52 (0 to 51)
 */
object BoardPath {

    const val TOTAL_MAIN_PATH = 52

    /**
     * Entry point on main path for each player color
     * These are standard Ludo positions
     */
    val entryIndexByColor = mapOf(
        PlayerColor.RED to 0,
        PlayerColor.BLUE to 13,
        PlayerColor.GREEN to 26,
        PlayerColor.YELLOW to 39
    )

    /**
     * Home stretch start index for each color
     * After completing one full round
     */
    val homeStartIndexByColor = mapOf(
        PlayerColor.RED to 51,
        PlayerColor.BLUE to 12,
        PlayerColor.GREEN to 25,
        PlayerColor.YELLOW to 38
    )

    /**
     * Calculates next position on main path with wrap-around
     */
    fun nextPosition(current: Int, steps: Int): Int {
        return (current + steps) % TOTAL_MAIN_PATH
    }
}
