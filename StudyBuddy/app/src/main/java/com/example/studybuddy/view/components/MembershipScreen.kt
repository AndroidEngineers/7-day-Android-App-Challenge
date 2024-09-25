package com.example.studybuddy.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studybuddy.ui.theme.buttonColor

@Composable
fun MembershipPurchaseScreen() {
    var selectedPlan by remember { mutableStateOf("Monthly") }
    val buttonText = when (selectedPlan) {
        "Monthly" -> "Pay ₹99"
        "Quarterly" -> "Pay ₹249"
        "Yearly" -> "Pay ₹799"
        else -> "Purchase"
    }

    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Choose Your Plan",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            PlanOption(
                title = "Monthly Plan",
                price = "₹99/month",
                isSelected = selectedPlan == "Monthly",
                onClick = { selectedPlan = "Monthly" }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PlanOption(
                title = "Quarterly Plan",
                price = "₹83/month",
                isSelected = selectedPlan == "Quarterly",
                onClick = { selectedPlan = "Quarterly" }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PlanOption(
                title = "Annual Plan",
                price = "₹67/month",
                isSelected = selectedPlan == "Yearly",
                onClick = { selectedPlan = "Yearly" }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* Handle purchase */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp, vertical = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor // Set the custom color here
                )
            ) {
                Text(text = buttonText)
            }
        }
    }
}

@Composable
fun PlanOption(
    title: String,
    price: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) buttonColor else Color.Transparent

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = price, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MembershipPurchaseScreenPreview() {
    MembershipPurchaseScreen()
}
