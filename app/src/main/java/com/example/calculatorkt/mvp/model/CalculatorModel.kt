package com.example.calculatorkt.mvp.model

import com.example.calculatorkt.mvp.CalculatorContract
import com.example.calculatorkt.util.Constants.ADD
import com.example.calculatorkt.util.Constants.DIV
import com.example.calculatorkt.util.Constants.EMPTY
import com.example.calculatorkt.util.Constants.PRODUCT
import com.example.calculatorkt.util.Constants.SUB
import com.example.calculatorkt.util.Constants.ZERO
import com.example.calculatorkt.util.ResultEnum

class CalculatorModel : CalculatorContract.Model {

    override var operationValue: String = EMPTY
    private var firstOperand: String = EMPTY
    private var operator: String = EMPTY
    private var secondOperand: String = EMPTY
    override var resultEnum: ResultEnum = ResultEnum.NONE
    override var equalsPressed = false

    companion object {
        private const val STRING_BEGIN_POSITION = 0
        private const val STRING_LAST_POSITION = 1
    }

    override fun setNewOperand(value: String) {
        operationValue += value
        if (operator.isEmpty()) {
            firstOperand += value
        } else {
            secondOperand += value
        }
    }

    override fun setNewOperator(value: String) {
        operationValue += value
        if (firstOperand.isEmpty()) {
            firstOperand += value
        } else if (operator.isEmpty()) {
            operator = value
        } else if (secondOperand.isEmpty()) {
            secondOperand += value
        }
    }

    override fun canPressOperator(value: String) = firstOperand.isEmpty() && value == SUB ||
            firstOperand.isNotEmpty() && firstOperand != SUB && operator.isEmpty() ||
            operator.isNotEmpty() && secondOperand.isEmpty() && value == SUB

    override fun getResultValue(): String {
        if (incompleteOperation()) {
            resultEnum = ResultEnum.INCOMPLETE_OPERATION_ERROR
            return EMPTY
        }
        return doOperation()
    }

    private fun doOperation(): String {
        return when (operator) {
            ADD -> {
                resultEnum = ResultEnum.SUCCESS
                (firstOperand.toDouble() + secondOperand.toDouble()).toString()
            }
            SUB -> {
                resultEnum = ResultEnum.SUCCESS
                (firstOperand.toDouble() - secondOperand.toDouble()).toString()
            }
            PRODUCT -> {
                resultEnum = ResultEnum.SUCCESS
                (firstOperand.toDouble() * secondOperand.toDouble()).toString()
            }
            DIV -> {
                if (secondOperand == ZERO) {
                    resultEnum = ResultEnum.DIVIDE_BY_ZERO_ERROR
                    return EMPTY
                }
                resultEnum = ResultEnum.SUCCESS
                (firstOperand.toDouble() / secondOperand.toDouble()).toString()
            }
            else -> firstOperand
        }
    }

    private fun incompleteOperation() = operator.isNotEmpty() && secondOperand.isEmpty()

    override fun deleteLast() {
        if (secondOperand.isNotEmpty()) {
            secondOperand =
                secondOperand.substring(STRING_BEGIN_POSITION, secondOperand.length - STRING_LAST_POSITION)
        } else if (operator.isNotEmpty()) {
            operator = operator.substring(STRING_BEGIN_POSITION, operator.length - STRING_LAST_POSITION)
        } else if (firstOperand.isNotEmpty()) {
            firstOperand =
                firstOperand.substring(STRING_BEGIN_POSITION, firstOperand.length - STRING_LAST_POSITION)
        }
        operationValue = "$firstOperand$operator$secondOperand"
    }

    override fun resetValues() {
        firstOperand = EMPTY
        operator = EMPTY
        secondOperand = EMPTY
        operationValue = EMPTY
        resultEnum = ResultEnum.NONE
        equalsPressed = false
    }

    override fun updateValues() {
        val result = getResultValue()
        if (resultEnum != ResultEnum.DIVIDE_BY_ZERO_ERROR && resultEnum != ResultEnum.INCOMPLETE_OPERATION_ERROR) {
            firstOperand = EMPTY
            operator = EMPTY
            secondOperand = EMPTY
            operationValue = EMPTY
            equalsPressed = false
            setNewOperand(result)
        }
    }
}
