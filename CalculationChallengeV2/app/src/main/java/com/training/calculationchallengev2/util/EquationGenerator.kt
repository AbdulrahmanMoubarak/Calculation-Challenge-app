package com.training.calculationchallengev2.util

import com.training.calculationchallengev2.model.Equation

class EquationGenerator {

    private var list: ArrayList<Equation> = arrayListOf(
        Equation("x - 4 = 10", "14", 5, 30),
        Equation("2x - 4 = 10", "7", 5, 30),
        Equation("5x - 6 = 3x - 8", "-1", 10, 40),
        Equation("ln(x) = 3", "20.09", 10, 30),
        Equation("(1/5)x = (1/4)x + 4", "-80", 10, 30),
        Equation("60 ÷ (8 + 2)", "6", 5, 15),
        )

    fun generateRandomEquation(): Equation {
        val num = (0..(list.size-1)).random()
        return list[num]
    }
}