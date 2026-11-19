package com.example.expensenote_22

data class ExpenseItem(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val amount: Double,
    val category: String,
    val date: String,
)