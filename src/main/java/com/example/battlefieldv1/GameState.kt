package com.example.battlefieldv1

data class GameState(
    val turnNumber: Int = 0,
    val playerHitCount: Int = 0,
    val playerMissCount: Int = 10,
    val playerHint: String = "Welcome to BattleShip",
    val hasWon: Boolean = false,
    val hasLost: Boolean = false,

    )

enum class BoardCellValue{
    SHIP ,
    GUESSED,
    NONE,
    COORDINATE,
    BLANK
}