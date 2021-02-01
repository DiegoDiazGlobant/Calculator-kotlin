package com.example.calculatorkt.mvp

interface CalculatorContract {

    interface Model {
        var operationValue: String
    }

    interface Presenter {
        fun onButtonPressed(buttonText: String)
    }

    interface View {
        fun showOperationValue(operation: String)
    }
}
