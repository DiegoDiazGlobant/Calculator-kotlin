package com.example.calculatorkt.mvp.view

import android.app.Activity
import com.example.calculatorkt.R
import com.example.calculatorkt.databinding.ActivityMainBinding
import com.example.calculatorkt.mvp.CalculatorContract
import com.example.calculatorkt.util.Constants.EMPTY
import com.google.android.material.snackbar.Snackbar

class CalculatorView(activity: Activity, private val binding: ActivityMainBinding) :
    ActivityView(activity), CalculatorContract.View {


    override fun showOperationValue(operation: String?) {
        binding.textViewOperations.text = operation
    }

    override fun showResultValue(result: String?) {
        binding.textViewResult.text = result
    }

    override fun showWrongOperator() {
        Snackbar.make(
            binding.textViewResult,
            context?.getString(R.string.activity_main_calculator_wrong_operator) ?: EMPTY,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showIncompleteOperation() {
        Snackbar.make(
            binding.textViewResult,
            context?.getString(R.string.activity_main_calculator_incomplete_operation_error) ?: EMPTY,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showDivideByZero() {
        Snackbar.make(
            binding.textViewResult,
            context?.getString(R.string.activity_main_calculator_divide_by_zero_error) ?: EMPTY,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun clearValues() {
        showOperationValue(context?.getString(R.string.activity_main_calculator_operation_text))
        showResultValue(context?.getString(R.string.activity_main_calculator_result_text))
    }

    override fun clearLast(operation: String?) {
        showOperationValue(operation)
        showResultValue(context?.getString(R.string.activity_main_calculator_result_text))
    }
}
