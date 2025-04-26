package org.example.interfaces

import org.example.model.GameState

interface GameRenderer {
    fun render(gameState: GameState)
    fun displayGameOver(score: Int)
}