package com.example.calculatorkt.mvp.presenter

import com.example.calculatorkt.mvp.CalculatorContract
import com.example.calculatorkt.util.Constants.EMPTY
import com.example.calculatorkt.util.ResultEnum

class CalculatorPresenter(
    private val model: CalculatorContract.Model,
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {

    override fun onNumberButtonPressed(buttonText: String) {
        model.setNewOperand(buttonText)
        view.showOperationValue(model.operationValue)
        view.showResultValue(buttonText)
    }

    override fun onOperatorButtonPressed(buttonText: String) {
        if (model.equalsPressed) {
            model.updateValues()
        }
        if (model.canPressOperator(buttonText)) {
            model.setNewOperator(buttonText)
            view.showOperationValue(model.operationValue)
            view.showResultValue(buttonText)
        } else {
            view.showWrongOperator()
        }
    }

    override fun onEqualsButtonPressed() {
        val result = model.getResultValue()
        when (model.resultEnum) {
            ResultEnum.DIVIDE_BY_ZERO_ERROR -> view.showDivideByZero()
            ResultEnum.INCOMPLETE_OPERATION_ERROR -> view.showIncompleteOperation()
            ResultEnum.SUCCESS -> {
                view.showResultValue(result)
                model.equalsPressed = true
            }
        }
    }

    override fun onClearLastButtonPressed() {
        model.deleteLast()
        if (model.operationValue == EMPTY) {
            view.clearValues()
        } else {
            view.clearLast(model.operationValue)
        }
    }

    override fun onClearAllButtonPressed(): Boolean {
        model.resetValues()
        view.clearValues()
        return true
    }
}
