package org.example

import org.example.interfaces.InputHandler
import org.example.model.Direction

class ConsoleInputHandler : InputHandler {
    override fun getNextDirection(): Direction? {
        return when (readLine()?.firstOrNull()) {
            'w' -> Direction.UP
            's' -> Direction.DOWN
            'a' -> Direction.LEFT
            'd' -> Direction.RIGHT
            else -> null
        }
    }
}