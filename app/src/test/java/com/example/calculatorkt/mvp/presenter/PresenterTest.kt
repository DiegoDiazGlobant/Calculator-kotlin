package com.example.calculatorkt.mvp.presenter

import com.example.calculatorkt.mvp.CalculatorContract
import com.example.calculatorkt.mvp.model.CalculatorModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PresenterTest {

    private lateinit var presenter: CalculatorContract.Presenter
    private val model = CalculatorModel()
    private val view: CalculatorContract.View = mock()

    companion object {
        const val EMPTY = ""
        const val ADD = "+"
        const val SUB = "-"
        const val PRODUCT = "*"
        const val DIV = "/"
        const val ZERO = "0"
        const val ONE = "1"
        const val DOUBLE_ONE = "1.0"
        const val DOUBLE_NINE = "9.0"
        const val DOUBLE_NEGATIVE_EIGHT = "-8.0"
        const val DOUBLE_NEGATIVE_TWO = "-2.0"
        const val INCOMPLETE_OPERATION = "1+"
        const val TRUE_VALUE = true
        const val FALSE_VALUE = false
    }

    @Before
    fun setup() {
        presenter = CalculatorPresenter(model, view)
    }

    @Test
    fun `when the number button is pressed show operation and result values`() {
        presenter.onNumberButtonPressed(ONE)
        assertEquals(ONE, model.operationValue)
        verify(view).showOperationValue(model.operationValue)
        verify(view).showResultValue(ONE)
    }

    @Test
    fun `when the operator button is pressed show operation and result values`() {
        model.setNewOperand(ONE)
        presenter.onOperatorButtonPressed(ADD)
        assertEquals(INCOMPLETE_OPERATION, model.operationValue)
        verify(view).showOperationValue(model.operationValue)
        verify(view).showResultValue(ADD)
    }

    @Test
    fun `when the operator button is pressed show a wrong operator error`() {
        model.setNewOperand(ONE)
        model.setNewOperator(SUB)
        presenter.onOperatorButtonPressed(DIV)
        verify(view).showWrongOperator()
    }

    @Test
    fun `when the operator button is pressed show a divide by zero error`() {
        model.setNewOperand(ONE)
        model.setNewOperator(DIV)
        model.setNewOperand(ZERO)
        presenter.onEqualsButtonPressed()
        verify(view).showDivideByZero()
    }

    @Test
    fun `when the equals button is pressed show a incomplete operation error`() {
        model.setNewOperand(ONE)
        model.setNewOperator(DIV)
        presenter.onEqualsButtonPressed()
        verify(view).showIncompleteOperation()
    }

    @Test
    fun `when the equals button is pressed with add operation`() {
        model.setNewOperand(ONE)
        model.setNewOperator(ADD)
        model.setNewOperand(ZERO)
        presenter.onEqualsButtonPressed()
        assertEquals(DOUBLE_ONE, model.getResultValue())
        verify(view).showResultValue(model.getResultValue())
    }

    @Test
    fun `when the equals button is pressed with sub operation`() {
        model.setNewOperand(ONE)
        model.setNewOperator(SUB)
        model.setNewOperand(ZERO)
        presenter.onEqualsButtonPressed()
        assertEquals(DOUBLE_ONE, model.getResultValue())
        verify(view).showResultValue(model.getResultValue())
    }

    @Test
    fun `when the equals button is pressed with product operation`() {
        model.setNewOperand(ONE)
        model.setNewOperator(PRODUCT)
        model.setNewOperand(DOUBLE_NINE)
        presenter.onEqualsButtonPressed()
        assertEquals(DOUBLE_NINE, model.getResultValue())
        verify(view).showResultValue(model.getResultValue())
    }

    @Test
    fun `when the equals button is pressed with div operation`() {
        model.setNewOperand(DOUBLE_NINE)
        model.setNewOperator(DIV)
        model.setNewOperand(ONE)
        presenter.onEqualsButtonPressed()
        assertEquals(DOUBLE_NINE, model.getResultValue())
        verify(view).showResultValue(model.getResultValue())
    }

    @Test
    fun `when the equals button is pressed with add operation and negative numbers`() {
        model.setNewOperator(SUB)
        model.setNewOperand(ONE)
        model.setNewOperator(ADD)
        model.setNewOperator(SUB)
        model.setNewOperand(ONE)
        presenter.onEqualsButtonPressed()
        assertEquals(DOUBLE_NEGATIVE_TWO, model.getResultValue())
        verify(view).showResultValue(model.getResultValue())
    }

    @Test
    fun `when the equals button is pressed with sub operation and negative numbers`() {
        model.setNewOperator(SUB)
        model.setNewOperand(DOUBLE_NINE)
        model.setNewOperator(SUB)
        model.setNewOperator(SUB)
        model.setNewOperand(ONE)
        presenter.onEqualsButtonPressed()
        assertEquals(DOUBLE_NEGATIVE_EIGHT, model.getResultValue())
        verify(view).showResultValue(model.getResultValue())
    }

    @Test
    fun `when the equals button is pressed with product operation and negative numbers`() {
        model.setNewOperator(SUB)
        model.setNewOperand(DOUBLE_NINE)
        model.setNewOperator(PRODUCT)
        model.setNewOperator(SUB)
        model.setNewOperand(ONE)
        presenter.onEqualsButtonPressed()
        assertEquals(DOUBLE_NINE, model.getResultValue())
        verify(view).showResultValue(model.getResultValue())
    }

    @Test
    fun `when the equals button is pressed with div operation and negative numbers`() {
        model.setNewOperator(SUB)
        model.setNewOperand(DOUBLE_NINE)
        model.setNewOperator(DIV)
        model.setNewOperator(SUB)
        model.setNewOperand(ONE)
        presenter.onEqualsButtonPressed()
        assertEquals(DOUBLE_NINE, model.getResultValue())
        verify(view).showResultValue(model.getResultValue())
    }

    @Test
    fun `when the clear button is long pressed show a totally clear display`() {
        presenter.onClearAllButtonPressed()
        assertEquals(EMPTY, model.getResultValue())
        assertEquals(EMPTY, model.operationValue)
        verify(view).clearValues()
    }

    @Test
    fun `when the clear button is short pressed remove last symbol on last value`() {
        model.setNewOperand(ONE)
        presenter.onClearLastButtonPressed()
        assertEquals(EMPTY, model.operationValue)
        verify(view).clearValues()
    }

    @Test
    fun `when the clear button is short pressed remove last symbol on first operand`() {
        model.setNewOperand(ONE)
        model.setNewOperand(ZERO)
        presenter.onClearLastButtonPressed()
        assertEquals(ONE, model.operationValue)
        verify(view).clearLast(model.operationValue)
    }

    @Test
    fun `when the clear button is short pressed remove last symbol on the operator`() {
        model.setNewOperand(ONE)
        model.setNewOperator(PRODUCT)
        presenter.onClearLastButtonPressed()
        assertEquals(ONE, model.operationValue)
        verify(view).clearLast(model.operationValue)
    }

    @Test
    fun `when the clear button is short pressed remove last symbol on second operand`() {
        model.setNewOperand(ONE)
        model.setNewOperator(ADD)
        model.setNewOperand(ZERO)
        presenter.onClearLastButtonPressed()
        assertEquals(INCOMPLETE_OPERATION, model.operationValue)
        verify(view).clearLast(model.operationValue)
    }
}
