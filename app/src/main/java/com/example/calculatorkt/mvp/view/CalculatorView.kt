package com.example.calculatorkt.mvp.view

import android.app.Activity
import com.example.calculatorkt.databinding.ActivityMainBinding
import com.example.calculatorkt.mvp.CalculatorContract

class CalculatorView(activity: Activity, private val binding: ActivityMainBinding) :
    ActivityView(activity), CalculatorContract.View {

    override fun showOperationValue(operation: String) {
        binding.textViewOperations.text = operation
    }
}
