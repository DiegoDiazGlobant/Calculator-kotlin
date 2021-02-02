package com.example.calculatorkt.mvp.model

import com.example.calculatorkt.mvp.CalculatorContract
import com.example.calculatorkt.util.Constants.EMPTY

class CalculatorModel() : CalculatorContract.Model {

    override var operationValue: String = EMPTY
}
