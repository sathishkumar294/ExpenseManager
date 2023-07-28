package com.sathish.expensemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.sathish.expensemanager.ui.theme.ExpenseManagerTheme

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
        Transaction("Food", "Dinner at KFC", 10)
        Transaction("Food", "Dinner at KFC", 20, false)
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
private fun Amount(amount: Int, isExpense: Boolean) {
    val color =
        if (isExpense) MaterialTheme.colorScheme.error else Color("#008800".toColorInt())
    Text(text = amount.toString(),
        color = color,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = 32.dp))
}

@Composable
private fun RowScope.Description(title: String, modifier: Modifier.Companion = Modifier) {
    Text(
        text = title,
        color = MaterialTheme.typography.headlineMedium.color,
        fontSize = 16.sp,
        modifier = modifier
            .weight(3F)
            .padding(start = 16.dp)
    )
}

@Composable
private fun Category(category: String) {
    Text(
        text = category,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 14.sp
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpenseManagerTheme {
        TransactionsList()
    }
}