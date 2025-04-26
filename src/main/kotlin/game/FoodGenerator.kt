package org.example.game

import org.example.model.BoardConfig
import org.example.model.Position

class FoodGenerator(private val boardConfig: BoardConfig) {
    fun generateFood(snake: Snake): Position {
        val occupiedPositions = snake.getBody()

        while (true) {
            val food = Position(
                (0 until boardConfig.width).random(),
                (0 until boardConfig.height).random()
            )

            if (food !in occupiedPositions) {
                return food
            }
        }
    }
}