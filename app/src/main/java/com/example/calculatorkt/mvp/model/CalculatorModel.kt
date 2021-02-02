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

    override fun canPressOperator(value: String) = !firstOperand.isEmpty() && operator.isEmpty() && secondOperand.isEmpty()

    override fun getResultValue(): String {
        resultEnum = ResultEnum.SUCCESS
        if (incompleteOperation()) {
            resultEnum = ResultEnum.INCOMPLETE_OPERATION_ERROR
            return EMPTY
        }
        return doOperation()
    }

    private fun doOperation(): String {
        return when (operator) {
            ADD -> (firstOperand.toDouble() + secondOperand.toDouble()).toString()
            SUB -> (firstOperand.toDouble() - secondOperand.toDouble()).toString()
            PRODUCT -> (firstOperand.toDouble() * secondOperand.toDouble()).toString()
            DIV -> {
                if (secondOperand == ZERO) {
                    resultEnum = ResultEnum.DIVIDE_BY_ZERO_ERROR
                    return EMPTY
                }
                (firstOperand.toDouble() / secondOperand.toDouble()).toString()
            }
            else -> firstOperand
        }
    }

    private fun incompleteOperation() = !operator.isEmpty() && secondOperand.isEmpty()

    override fun deleteLast() {
        if (!secondOperand.isEmpty()) {
            secondOperand =
                secondOperand.substring(CalculatorModel.STRING_BEGIN_POSITION, secondOperand.length - CalculatorModel.STRING_LAST_POSITION)
        } else if (!operator.isEmpty()) {
            operator = operator.substring(CalculatorModel.STRING_BEGIN_POSITION, operator.length - CalculatorModel.STRING_LAST_POSITION)
        } else if (!firstOperand.isEmpty()) {
            firstOperand =
                firstOperand.substring(CalculatorModel.STRING_BEGIN_POSITION, firstOperand.length - CalculatorModel.STRING_LAST_POSITION)
        }
        operationValue = "$firstOperand$operator$secondOperand"
    }

    override fun resetValues() {
        firstOperand = EMPTY
        operator = EMPTY
        secondOperand = EMPTY
        operationValue = EMPTY
        resultEnum = ResultEnum.SUCCESS
    }
}
