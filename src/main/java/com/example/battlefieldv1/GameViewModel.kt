package com.example.battlefieldv1

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel : ViewModel() {
    var state by mutableStateOf(GameState())

    val boardItems: MutableMap<Int, BoardCellValue> = mutableMapOf(


        /*
           x,x,x,x,x
           x,x,x,x,x
           x,x,x,x,x
           x,x,x,x,x
           x,x,x,x,x

         Conv
        */
        1 to BoardCellValue.BLANK, //0,0
        2 to BoardCellValue.COORDINATE, //0,1
        3 to BoardCellValue.COORDINATE, //0,2
        4 to BoardCellValue.COORDINATE, //0,3
        5 to BoardCellValue.COORDINATE, //0,4
        6 to BoardCellValue.COORDINATE, //0,5

        7 to BoardCellValue.COORDINATE, //1,0
        8 to BoardCellValue.NONE, //1,1
        9 to BoardCellValue.NONE, //1,2
        10 to BoardCellValue.NONE, //1,3
        11 to BoardCellValue.NONE, //1,4
        12 to BoardCellValue.NONE, //1,5

        13 to BoardCellValue.COORDINATE, //2,0
        14 to BoardCellValue.NONE, //2,1
        15 to BoardCellValue.NONE, //2,2
        16 to BoardCellValue.NONE, //2,3
        17 to BoardCellValue.NONE, //2,4
        18 to BoardCellValue.NONE, //2,5

        19 to BoardCellValue.COORDINATE, //3,0
        20 to BoardCellValue.NONE, //3,1
        21 to BoardCellValue.NONE, //3,2
        22 to BoardCellValue.NONE, //3,3
        23 to BoardCellValue.NONE, //3,4
        24 to BoardCellValue.NONE, //3,5

        25 to BoardCellValue.COORDINATE, //4,0
        26 to BoardCellValue.NONE, //4,1
        27 to BoardCellValue.NONE, //4,2
        28 to BoardCellValue.NONE, //4,3
        29 to BoardCellValue.NONE, //4,4
        30 to BoardCellValue.NONE, //4,5

        31 to BoardCellValue.COORDINATE, //5,0
        32 to BoardCellValue.NONE, //5,1
        33 to BoardCellValue.NONE, //5,2
        34 to BoardCellValue.NONE, //5,3
        35 to BoardCellValue.NONE, //5,4
        36 to BoardCellValue.NONE, //5,5

    )



    fun gameReset() {
        boardItems.forEach { (i) ->
            if (
                boardItems[i] == BoardCellValue.GUESSED || boardItems[i] == BoardCellValue.SHIP
            ) {
                boardItems[i] = BoardCellValue.NONE
            }

        }
        state = state.copy(
            turnNumber = 0,
            playerHitCount = 0,
            playerMissCount = 10,
            playerHint = "Welcome to BattleShip",
            hasWon = false,
            hasLost = false,
        )
        occupiedList = newGame()

    }

    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.BoardTapped -> {
                addValueToBoard(action.cellNo)
            }
        }
    }

    private fun addValueToBoard(cellNo: Int) {

        if( //if not an invalid guess
            boardItems[cellNo] != BoardCellValue.COORDINATE
            && boardItems[cellNo] != BoardCellValue.BLANK
        )
        if (!state.hasWon && !state.hasLost) { //if we haven't won or lost, continue to allow guess
            if (!checkForLoss()) { // check for loss, and change boolean accordingly
                if (occupiedList.contains(cellNo)) { //if the guess is where a ship is,
                    boardItems[cellNo] = BoardCellValue.SHIP
                    state = state.copy(
                        playerHint = "Hit a ship!",
                        playerHitCount = state.playerHitCount + 1,
                        turnNumber = state.turnNumber + 1
                    )

                    if (checkForWin()) {
                        state = state.copy(
                            hasWon = true,
                            playerHint = "You sunk all the ships!",
                        )

                    }

                } else if (!occupiedList.contains(cellNo)) {
                    boardItems[cellNo] = BoardCellValue.GUESSED
                    Log.d("Place Ship", boardItems.toString())
                    state = state.copy(
                        playerHint = "Missed a ship!",
                        playerMissCount = state.playerMissCount - 1,
                        turnNumber = state.turnNumber + 1

                    )

                    if (checkForLoss()) {
                        state = state.copy(
                            hasLost = true,
                            playerHint = "You missed too many times. You lose :("
                        )

                    }
                }
            }
        }

    }

    private fun checkForLoss(): Boolean {
        return state.playerMissCount == 0
    }

    private fun checkForWin(): Boolean {
        return state.playerHitCount == 9
    }
}



