package com.example.battlefieldv1

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel

import com.example.battlefieldv1.ui.theme.BattleFieldV1Theme

/*
* Battleship Game with Canvas
*
* Pseudocode/ToDO
*
* Create Initial UI
* Create game board (8*8) /
* Randomly place 4 ships on the board //
* 4 ships with different lengths //
* User taps on screen to choose which spot to guess //
* 3 misses allowed
* Animation when taking a guess /
* Animation when missing /
* Animation when hitting /
*
*
*
* */

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleFieldV1Theme {
                    val viewModel = GameViewModel()

                    GameScreen(viewModel = viewModel)


            }
        }
    }
}


