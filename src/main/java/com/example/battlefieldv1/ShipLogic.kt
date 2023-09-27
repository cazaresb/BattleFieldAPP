package com.example.battlefieldv1

import android.util.Log
import java.util.*
import com.example.battlefieldv1.GameViewModel

/**
 * A data class representing a coordinate in the matrix.
 */
data class Coordinate(val row: Int, val col: Int)

var occupiedList = newGame()

fun placeShips(): Array<IntArray> {
    // Create a 5x5 matrix with zeros
    val matrix = Array(5) { IntArray(5) }

    val ships = listOf(listOf(1, 2, 3), listOf(4, 5), listOf(6, 7, 8, 9))

    // Place the ships randomly in the matrix, checking for overlap
    val rand = Random()
    for (ship in ships.shuffled(rand)) {
        // Find a random empty spot in the matrix
        var row = rand.nextInt(5 - ship.size + 1)
        var col = rand.nextInt(5)
        while (!isEmpty(matrix, row, col, ship.size)) {
            row = rand.nextInt(5 - ship.size + 1)
            col = rand.nextInt(5)
        }
        // Place the ship in the matrix
        for (i in ship.indices) {
            matrix[row + i][col] = ship[i]
        }
    }

    return matrix
}

/**
 * Checks if the given range of rows and columns in the matrix are empty.
 * Returns true if all elements in the range are zero, false otherwise.
 */
fun isEmpty(matrix: Array<IntArray>, row: Int, col: Int, size: Int): Boolean {
    for (i in row until row + size) {
        for (j in col - 1..col + 1) {
            if (i in matrix.indices && j in matrix[i].indices && matrix[i][j] != 0) {
                return false
            }
        }
    }
    return true
}
// First
fun getListOccupiedCoordinates(matrix: Array<IntArray>): List<Coordinate> {
    val coords = mutableListOf<Coordinate>()
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            if (matrix[i][j] != 0) {
                coords.add(Coordinate(i, j))
            }
        }
    }
    return coords
}
/*
* Convert from coordinates to usable number to place in the board
*
*  */
fun convertCoordinatesToIndex(position: Coordinate): Int {
    // Calculate the index based on the row and column
    val index = (position.row) * 5 + (position.col + 1) + 8 + (position.row - 1 )

    // Return the calculated index
    return index
}
fun getConvertedCoordinatesList(list: List<Coordinate>): List<Int> {
    val newList = mutableListOf<Int>()
    for(i in list.indices){
        val converted = convertCoordinatesToIndex(list[i])
        newList.add(converted)
    }
    return newList
}
fun newGame(): List<Int>{
    val gameMatrix = placeShips()
    val listOfOccupied = getListOccupiedCoordinates(gameMatrix)

    Log.d("Place ships", "Coords: ${listOfOccupied.toString()}")

    val finalConversion = getConvertedCoordinatesList(listOfOccupied)
    Log.d("Place Ships", "Final Conversion to Usable Indices: $finalConversion")

    return finalConversion
}

