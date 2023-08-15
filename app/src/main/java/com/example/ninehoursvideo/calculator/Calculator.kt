package com.example.ninehoursvideo.calculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ninehoursvideo.calculator.Operations.addition
import com.example.ninehoursvideo.calculator.Operations.division
import com.example.ninehoursvideo.calculator.Operations.isOperatorAdded
import com.example.ninehoursvideo.calculator.Operations.multiplication
import com.example.ninehoursvideo.calculator.Operations.removeZeroAfterDot
import com.example.ninehoursvideo.calculator.Operations.subtraction
import com.example.ninehoursvideo.databinding.ActivityCalculatorBinding

class Calculator : AppCompatActivity() {
    private lateinit var binding : ActivityCalculatorBinding
    private var lastNumeric : Boolean = false
    private var lastDot : Boolean = false
    private var lastOperator : String? = null
    private var lastPercentage : Boolean = false
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onDigit(view : View) {
        binding.apply {
            currentResult.append((view as TextView).text)
            lastNumeric = true
            lastDot = false
        }
    }

    fun onClear(view : View) {
        binding.apply {
            currentResult.text = ""
            lastOperation.text = ""
        }
    }

    fun onDecimal(view : View) {
        if (lastNumeric && ! lastDot) {
            binding.currentResult.append(Constant.DOT)
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view : View) {
        if (lastNumeric && ! isOperatorAdded(binding.currentResult.text.toString())) {
            lastOperator = (view as TextView).text.toString()
            binding.currentResult.append(view.text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual(view : View) {
        if (lastNumeric) {
            var tvValue = binding.currentResult.text.toString()
            try {
                var prefix = ""
                binding.apply {
                    if (lastOperator != null) {
                        if (tvValue.startsWith(lastOperator !!)) {
                            prefix = lastOperator !!
                            tvValue = tvValue.substring(1)
                        }
                        if (tvValue.contains(lastOperator !!)) {
                            val split = tvValue.split(lastOperator !!)
                            var firstOperator = split[0]
                            var secondOperator = split[1]
                            val difference : String
                            lastOperation.text = currentResult.text
                            if (lastPercentage) {
                                secondOperator =
                                    secondOperator.substring(0 , secondOperator.length - 1)
                                secondOperator = ((secondOperator.toDouble()).div(100)).toString()
                                difference = (firstOperator.toDouble().times(
                                    secondOperator.toDouble()
                                )).toString()
                                secondOperator = difference
                            }
                            if (prefix.isNotEmpty()) firstOperator = prefix + firstOperator
                            when (lastOperator !!) {
                                Constant.MINUS    -> {
                                    currentResult.text = removeZeroAfterDot(
                                        subtraction(firstOperator , secondOperator)
                                    )
                                    initializeCalculator()
                                }

                                Constant.DIVIDE   -> {
                                    currentResult.text = removeZeroAfterDot(
                                        division(firstOperator , secondOperator)
                                    )
                                    initializeCalculator()
                                }

                                Constant.PLUS     -> {
                                    currentResult.text = removeZeroAfterDot(
                                        addition(firstOperator , secondOperator)
                                    )
                                    initializeCalculator()
                                }

                                Constant.MULTIPLY -> {
                                    currentResult.text = removeZeroAfterDot(
                                        multiplication(firstOperator , secondOperator)
                                    )
                                    initializeCalculator()
                                }

                                else              -> {
                                    currentResult.text = lastOperation.text
                                }
                            }
                        }
                    } else {
                        if (tvValue.contains(Constant.PERCENTAGE)) {
                            lastOperation.text = currentResult.text
                            val result = tvValue.substring(0 , tvValue.length - 1)
                            currentResult.text = (result.toDouble().div(100)).toString()
                            initializeCalculator()
                        }
                    }
                }
            } catch (e : ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    fun onPercentage(view : View) {
        if (lastNumeric && ! lastPercentage) {
            lastPercentage = true
            binding.currentResult.append((view as TextView).text)
            lastNumeric = true
            lastDot = false
        }
    }

    fun onNegative(view : View) {
        binding.apply {
            if (! currentResult.text.contains(Constant.NEGATIVE)) {
                lastOperation.text = ""
                val negativity = Constant.NEGATIVE + currentResult.text
                currentResult.text = negativity
                lastOperator = Constant.NEGATIVE
                lastNumeric = true
            } else {
                val result = currentResult.text.substring(1 , currentResult.length())
                currentResult.text = result
                lastOperator = null
                lastNumeric = true
            }
        }
    }

    private fun initializeCalculator() {
        lastPercentage = false
        lastNumeric = true
        lastOperator = null
    }
}