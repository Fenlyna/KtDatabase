package com.amia.latihanuas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amia.latihanuas.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnX.setOnClickListener {
            calculate(Operation.MULTIPLY)
        }
        binding.btnDivide.setOnClickListener {
            calculate(Operation.DIVIDE)
        }
        binding.btnPlus.setOnClickListener {
            calculate(Operation.PLUS)
        }
        binding.btnMinus.setOnClickListener {
            calculate(Operation.MINUS)
        }
        binding.btnPxl.setOnClickListener {
            calculate(Operation.LUAS)
        }


    }

    private fun calculate(operation: Operation) {
        if (binding.edNum1.text.isNotEmpty() && binding.edNum2.text.toString().isNotEmpty()){
            val num1 = binding.edNum1.text.toString().toFloat()
            val num2 = binding.edNum2.text.toString().toFloat()

            val result: Float = when(operation) {
                Operation.PLUS -> {
                    num1 + num2
                }
                Operation.MINUS -> {
                    num1 - num2
                }
                Operation.MULTIPLY -> {
                    num1 * num2
                }
                Operation.DIVIDE -> {
                    num1 / num2
                }
                Operation.LUAS -> {
                    (num1 * num2) * 2
                }
            }
            binding.edNum3.setText(result.toString())
        } else {
            Toast.makeText(this, "Harap masukan angka", Toast.LENGTH_SHORT).show()
        }


    }
}