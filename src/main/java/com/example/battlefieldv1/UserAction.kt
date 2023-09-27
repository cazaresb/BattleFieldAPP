package com.example.battlefieldv1

sealed class UserAction {
    // Add button
    data class BoardTapped(val cellNo: Int): UserAction()
}