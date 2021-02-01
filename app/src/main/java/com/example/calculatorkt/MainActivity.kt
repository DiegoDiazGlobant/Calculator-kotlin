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
        setUpClickListenerToButton(binding.buttonAdd)
        setUpClickListenerToButton(binding.buttonSub)
        setUpClickListenerToButton(binding.buttonProduct)
        setUpClickListenerToButton(binding.buttonDivision)
        setUpClickListenerToButton(binding.buttonZero)
        setUpClickListenerToButton(binding.buttonOne)
        setUpClickListenerToButton(binding.buttonTwo)
        setUpClickListenerToButton(binding.buttonThree)
        setUpClickListenerToButton(binding.buttonFour)
        setUpClickListenerToButton(binding.buttonFive)
        setUpClickListenerToButton(binding.buttonSix)
        setUpClickListenerToButton(binding.buttonSeven)
        setUpClickListenerToButton(binding.buttonEight)
        setUpClickListenerToButton(binding.buttonNine)
    }

    private fun setUpClickListenerToButton(buttonPressed: Button) {
        buttonPressed.setOnClickListener { presenter.onButtonPressed(buttonPressed.text.toString()) }
    }
}
