package org.example

import org.example.interfaces.GameRenderer
import org.example.model.GameState

class ConsoleGameRenderer : GameRenderer {
    override fun render(gameState: GameState) {
        print("\u001b[H\u001b[2J")
        System.out.flush()

        val board = Array(gameState.boardConfig.height) {
            Array(gameState.boardConfig.width) { "." }
        }

        gameState.snake.forEachIndexed { index, pos ->
            board[pos.y][pos.x] = if (index == 0) "O" else "o"
        }

        board[gameState.food.y][gameState.food.x] = "X"

        println("Snake Game - Score: ${gameState.score}")
        board.forEach { row ->
            println(row.joinToString(" "))
        }
    }

    override fun displayGameOver(score: Int) {
        println("Game Over! Final Score: $score")
    }
}