package com.sathish.expensemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.sathish.expensemanager.ui.theme.ExpenseManagerTheme
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TransactionsList()
                }
            }
        }
    }
}

@Composable
fun TransactionsList() {
    Column {
        TransactionDate(Date(), 20, 10)
        Transaction("Food", "Dinner at KFC", 10)
        Transaction("Food", "Dinner at KFC", 20, false)
    }
}

@Composable
fun TransactionDate(date: Date, totalIncome: Int, totalExpense: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DateText(date)
        Amount(totalIncome, false, 14.sp)
        Amount(totalExpense, true, 14.sp)
    }
}

@Composable
fun Transaction(category: String, title: String, amount: Int, isExpense: Boolean = true) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Category(category)
        Description(title, Modifier)
        Amount(amount, isExpense)
    }
}

@Composable
private fun Amount(amount: Int, isExpense: Boolean, fontSize: TextUnit = 12.sp) {
    val color =
        if (isExpense) MaterialTheme.colorScheme.error else Color("#008800".toColorInt())
    Text(
        text = amount.toString(),
        color = color,
        fontSize = fontSize,
        modifier = Modifier.padding(start = 32.dp)
    )
}

@Composable
private fun RowScope.Description(title: String, modifier: Modifier.Companion = Modifier) {
    Text(
        text = title,
        color = MaterialTheme.typography.headlineMedium.color,
        fontSize = 11.sp,
        modifier = modifier
            .weight(3F)
            .padding(start = 16.dp)
    )
}

@Composable
private fun Category(category: String, fontSize: TextUnit = 12.sp) {
    Text(
        text = category,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = fontSize
    )
}

@Composable
private fun DateText(date: Date, fontSize: TextUnit = 12.sp) {
    Text(
        text = SimpleDateFormat("dd-MMM-yyyy").format(date),
        color = MaterialTheme.colorScheme.secondary,
        fontSize = fontSize
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpenseManagerTheme {
        TransactionsList()
    }
}