package com.example.ninehoursvideo.calculator

object Operations {
    fun subtraction(firstOperator : String , secondOperator : String) : String {
        return (firstOperator.toDouble().minus(secondOperator.toDouble())).toString()
    }
    fun addition(firstOperator : String , secondOperator : String) : String {
        return (firstOperator.toDouble().plus(secondOperator.toDouble())).toString()
    }
    fun multiplication(firstOperator : String , secondOperator : String) : String {
        return (firstOperator.toDouble().times(secondOperator.toDouble())).toString()
    }
    fun division(firstOperator : String , secondOperator : String) : String {
        return (firstOperator.toDouble().div(secondOperator.toDouble())).toString()
    }
    fun removeZeroAfterDot(result : String) : String {
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0 , result.length - 2)
        }
        return value
    }

    fun isOperatorAdded(value : String) : Boolean {
        return if (value.startsWith(Constant.MINUS)) false
        else value.contains(Constant.DIVIDE) || value.contains(Constant.PLUS) || value.contains(
            Constant.MINUS
        ) || value.contains(Constant.MULTIPLY)
    }
}