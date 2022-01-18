package net.woogear.domain

import java.lang.IllegalArgumentException

enum class OperationType {
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE;

    fun isPlus(): Boolean {
        return this == PLUS
    }

    fun isMinus(): Boolean {
        return this == MINUS
    }

    fun isMultiply(): Boolean {
        return this == MULTIPLY
    }

    fun isDivide(): Boolean {
        return this == DIVIDE
    }
}


class Calculator {
    private var answer = 0
    private var operationType: OperationType = OperationType.PLUS

    fun evaluate(s: String): Int {
        val splitTexts: List<String> = s.split(" ")

        for (text in splitTexts) {
            if (isOperationType(text)) {
                setOperationType(text)
                continue
            }

            if (text.isInt()) {
                operateByType(text.toInt(), operationType)
                continue
            }

            throw IllegalArgumentException()
        }

        return answer
    }

    private fun isOperationType(text: String): Boolean {
        return text == "+" || text == "-" || text == "*" || text == "/"
    }

    private fun setOperationType(text: String) {
        when (text) {
            "+" -> operationType = OperationType.PLUS
            "-" -> operationType = OperationType.MINUS
            "*" -> operationType = OperationType.MULTIPLY
            "/" -> operationType = OperationType.DIVIDE
        }
    }

    private fun operateByType(number: Int, operationType: OperationType) {
        if (operationType.isPlus()) {
            answer += number
            return
        }

        if (operationType.isMinus()) {
            answer -= number
            return
        }
    }

    private fun String.isInt(): Boolean {
        return when(toIntOrNull()) {
            null -> false
            else -> true
        }
    }
}