package com.example.calculatorkt.mvp

import com.example.calculatorkt.util.ResultEnum

interface CalculatorContract {

    interface Model {
        var operationValue: String
        var resultEnum: ResultEnum
        fun setNewOperand(value: String)
        fun setNewOperator(value: String)
        fun canPressOperator(value: String): Boolean
        fun getResultValue(): String
        fun deleteLast()
        fun resetValues()
    }

    interface Presenter {
        fun onNumberButtonPressed(buttonText: String?)
        fun onOperatorButtonPressed(buttonText: String?)
        fun onEqualsButtonPressed()
        fun onClearAllButtonPressed(): Boolean
        fun onClearLastButtonPressed()
    }

    interface View {
        fun showOperationValue(operation: String?)
        fun showResultValue(result: String?)
        fun showWrongOperator()
        fun showIncompleteOperation()
        fun showDivideByZero()
        fun clearValues()
        fun clearLast(operation: String?)
    }
}
