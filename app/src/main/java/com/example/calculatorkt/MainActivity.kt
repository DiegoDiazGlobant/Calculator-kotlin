package com.example.calculatorkt

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorkt.databinding.ActivityMainBinding
import com.example.calculatorkt.mvp.CalculatorContract
import com.example.calculatorkt.mvp.model.CalculatorModel
import com.example.calculatorkt.mvp.presenter.CalculatorPresenter
import com.example.calculatorkt.mvp.view.CalculatorView

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: CalculatorContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CalculatorPresenter(CalculatorModel(), CalculatorView(this, binding))
        setListeners()
    }

    private fun setListeners() {
        setUpClickListenerToOperatorButton(binding.buttonAdd)
        setUpClickListenerToOperatorButton(binding.buttonSub)
        setUpClickListenerToOperatorButton(binding.buttonProduct)
        setUpClickListenerToOperatorButton(binding.buttonDivision)
        setUpClickListenerToOperandButton(binding.buttonZero)
        setUpClickListenerToOperandButton(binding.buttonOne)
        setUpClickListenerToOperandButton(binding.buttonTwo)
        setUpClickListenerToOperandButton(binding.buttonThree)
        setUpClickListenerToOperandButton(binding.buttonFour)
        setUpClickListenerToOperandButton(binding.buttonFive)
        setUpClickListenerToOperandButton(binding.buttonSix)
        setUpClickListenerToOperandButton(binding.buttonSeven)
        setUpClickListenerToOperandButton(binding.buttonEight)
        setUpClickListenerToOperandButton(binding.buttonNine)
        binding.buttonEquals.setOnClickListener { presenter.onEqualsButtonPressed() }
        binding.buttonClear.setOnClickListener { presenter.onClearLastButtonPressed() }
        binding.buttonClear.setOnLongClickListener { presenter.onClearAllButtonPressed() }
    }

    private fun setUpClickListenerToOperatorButton(buttonPressed: Button) {
        buttonPressed.setOnClickListener { presenter.onOperatorButtonPressed(buttonPressed.text.toString()) }
    }

    private fun setUpClickListenerToOperandButton(buttonPressed: Button) {
        buttonPressed.setOnClickListener { presenter.onNumberButtonPressed(buttonPressed.text.toString()) }
    }
}
