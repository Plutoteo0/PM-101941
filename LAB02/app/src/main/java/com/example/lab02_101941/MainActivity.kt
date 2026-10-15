package com.example.lab02_101941

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var firstNumber: Double = 0.0
    private var currentOperator: String = ""
    private var isNewNumber: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        val buttons = listOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2",
            R.id.btn3 to "3", R.id.btn4 to "4", R.id.btn5 to "5",
            R.id.btn6 to "6", R.id.btn7 to "7", R.id.btn8 to "8", R.id.btn9 to "9"
        )
        val operators = listOf(R.id.btnPlus to "+", R.id.btnMinus to "-", R.id.btnMultiply to "*", R.id.btnDivide to "/")


        for ((id, value) in buttons) {
            onButtonCLick(tvResult, id, value)
        }
        for ((id, op) in operators) {
            onOperatorClick(tvResult, id, op)
        }
        onClearClick(R.id.btnC)
        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            onEqualsClick(tvResult)
        }
    }

    private fun onButtonCLick(tvResult: TextView, buttonId: Int, value: String) {
        findViewById<Button>(buttonId).setOnClickListener {
            val currentText = tvResult.text.toString()
            if (isNewNumber || currentText == "0") {
                tvResult.text = value
                isNewNumber = false
            } else {
                tvResult.text = currentText + value
            }
        }
    }

    private fun onClearClick(buttonId: Int) {
        val button = findViewById<Button>(buttonId)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        button.setOnClickListener {
            tvResult.text = "0"
            firstNumber = 0.0
            currentOperator = ""
            isNewNumber = true
        }
    }

    private fun onOperatorClick(tvResult: TextView, buttonId: Int, operator: String) {
        findViewById<Button>(buttonId).setOnClickListener {
            firstNumber = tvResult.text.toString().toDoubleOrNull() ?: 0.0
            currentOperator = operator
            isNewNumber = true
        }
    }
    private fun onEqualsClick(tvResult: TextView){
        val secondNumber = tvResult.text.toString().toDoubleOrNull() ?: 0.0
        val result = when (currentOperator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0.0 ) firstNumber / secondNumber else 0.0
            else -> secondNumber
        }
        tvResult.text = result.toString()
        isNewNumber = true
    }
}