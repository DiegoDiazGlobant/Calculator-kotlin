package com.example.calculatorkt.mvp.presenter

import com.example.calculatorkt.mvp.CalculatorContract

class CalculatorPresenter(
    private val model: CalculatorContract.Model,
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {

    override fun onButtonPressed(buttonText: String) {
        model.operationValue = buttonText
        view.showOperationValue(model.operationValue)
    }
}
