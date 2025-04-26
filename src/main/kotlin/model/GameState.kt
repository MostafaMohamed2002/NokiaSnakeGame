package org.example.model

data class GameState(
    val snake: List<Position>,
    val food: Position,
    val score: Int,
    val boardConfig: BoardConfig
)