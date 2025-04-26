package org.example

import org.example.game.SnakeGameController

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val consoleRenderer = ConsoleGameRenderer()
    val consoleInputHandler = ConsoleInputHandler()
    val consoleGame = SnakeGameController(
        renderer = consoleRenderer,
        inputHandler = consoleInputHandler
    )
    consoleGame.start()
}