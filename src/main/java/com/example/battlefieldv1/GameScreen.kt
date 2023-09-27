package com.example.battlefieldv1

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.battlefieldv1.ui.theme.*

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable

fun GameScreen(
    viewModel: GameViewModel
) {
    var state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        // Title
        Text(
            text = "Battleship",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.surface),
            contentAlignment = Alignment.Center
        ){
            BoardBase()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f),
                cells = GridCells.Fixed(6)
            ) {
                viewModel.boardItems.forEach { (cellNo, boardCellValue) ->
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    viewModel.onAction(
                                        UserAction.BoardTapped(cellNo)
                                    )
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){

                            if(

                                boardCellValue == BoardCellValue.COORDINATE
                                || boardCellValue == BoardCellValue.BLANK
                                 )
                            {
                                Number(cellNo)
                            }
                            AnimatedVisibility(
                                visible = viewModel.boardItems[cellNo] != BoardCellValue.NONE,
                                enter = scaleIn(tween(100))
                            ) {

                                if(viewModel.boardItems[cellNo] == BoardCellValue.GUESSED) {
                                    Miss()
                                } else if (viewModel.boardItems[cellNo] == BoardCellValue.SHIP) {
                                    Hit()
                                }

                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = "Turn: ${state.turnNumber}",

                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "Hits: ${state.playerHitCount}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "Misses left: ${state.playerMissCount}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary

            )
        }

        Button(
            onClick = {
                viewModel.gameReset()
            },
            shape = RoundedCornerShape(5.dp),
            elevation = ButtonDefaults.elevation(5.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.background
            )
        ){
            Text(
                text = "Play Again",
                style = MaterialTheme.typography.body1,
            )
        }

        Text(
            text = state.playerHint,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onSurface
        )

    }
}





@Preview
@Composable
fun Prev() {
    GameScreen(viewModel = GameViewModel())


}
@Preview
@Composable
fun Prev2() {

    Column {
        Hit()
        Miss()
    }

}