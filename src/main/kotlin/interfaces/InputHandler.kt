package org.example.interfaces

import org.example.model.Direction


interface InputHandler {
    fun getNextDirection(): Direction?
}