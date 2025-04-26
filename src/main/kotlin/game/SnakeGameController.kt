package org.example.game

import org.example.interfaces.GameRenderer
import org.example.interfaces.InputHandler
import org.example.model.BoardConfig
import org.example.model.GameState
import org.example.model.GameStatus
import org.example.model.Position

class SnakeGameController(
    private val boardConfig: BoardConfig = BoardConfig(),
    private val renderer: GameRenderer,
    private val inputHandler: InputHandler
) {
    private val snake: Snake = Snake(boardConfig)
    private val foodGenerator = FoodGenerator(boardConfig)

    private var food: Position = foodGenerator.generateFood(snake)
    private var score: Int = 0
    private var status: GameStatus = GameStatus.RUNNING

    fun start() {
        renderer.render(
            GameState(
                snake = snake.getBody(), food = food, score = score, boardConfig = boardConfig
            )
        )
        while (status == GameStatus.RUNNING) {
            inputHandler.getNextDirection()?.let { direction ->
                snake.changeDirection(direction)
            }

            update()

            renderer.render(
                GameState(
                    snake = snake.getBody(), food = food, score = score, boardConfig = boardConfig
                )
            )

            Thread.sleep(200)
        }

        renderer.displayGameOver(score)
    }

    fun update() {
        snake.move()

        if (snake.getBody().first() == food) {
            snake.grow()
            score++
            food = foodGenerator.generateFood(snake)
        }

        if (isGameOver()) {
            status = GameStatus.GAME_OVER
        }
    }

    private fun isGameOver(): Boolean {
        val head = snake.getBody().first()

        val wallCollision = head.x < 0 || head.x >= boardConfig.width || head.y < 0 || head.y >= boardConfig.height

        val selfCollision = snake.hasSelfCollision()

        return wallCollision || selfCollision
    }

}