package org.example.game

import org.example.model.BoardConfig
import org.example.model.Direction
import org.example.model.Position
import java.util.*

class Snake(
    private val boardConfig: BoardConfig,
    initialPosition: Position = Position(
        boardConfig.width / 2,
        boardConfig.height / 2
    )
) {
    private val body: LinkedList<Position> = LinkedList()
    private var currentDirection = Direction.UP

    private var lastRemovedSegment: Position? = null

    init {
        body.addFirst(initialPosition)
    }

    fun move(): Position {
        val head = body.first()
        val newHead = when (currentDirection) {
            Direction.UP -> Position(head.x, head.y - 1)
            Direction.DOWN -> Position(head.x, head.y + 1)
            Direction.LEFT -> Position(head.x - 1, head.y)
            Direction.RIGHT -> Position(head.x + 1, head.y)
        }

        body.addFirst(newHead)

        lastRemovedSegment = body.removeLast()

        return lastRemovedSegment!!
    }

    fun grow() {
        lastRemovedSegment?.let {
            body.addLast(it)
            lastRemovedSegment = null
        }
    }

    fun changeDirection(newDirection: Direction) {
        // Prevent 180-degree turns
        val invalidMoves = mapOf(
            Direction.UP to Direction.DOWN,
            Direction.DOWN to Direction.UP,
            Direction.LEFT to Direction.RIGHT,
            Direction.RIGHT to Direction.LEFT
        )

        if (invalidMoves[currentDirection] != newDirection) {
            currentDirection = newDirection
        }
    }

    fun getBody(): List<Position> = body.toList()

    fun hasSelfCollision(): Boolean {
        val head = body.first()
        return body.drop(1).any { it.isSameAs(head) }
    }
}