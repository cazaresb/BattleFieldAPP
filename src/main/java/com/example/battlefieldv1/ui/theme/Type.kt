package com.example.battlefieldv1.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.battlefieldv1.R

// Set of Material typography styles to start with
val Poppings = FontFamily(
    Font(R.font.poppings_regular, FontWeight.Normal),
    Font(R.font.poppings_bold, FontWeight.Bold)
)
val Typography = Typography(

    h1 = TextStyle(
        fontFamily = Poppings,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
    ),
    h2 = TextStyle(
        fontFamily = Poppings,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    body1 = TextStyle(
        fontFamily = Poppings,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)