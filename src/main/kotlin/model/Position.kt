package org.example.model

data class Position(
    val x: Int,
    val y: Int
) {
    fun isSameAs(other: Position) = x == other.x && y == other.y
}