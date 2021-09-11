package com.training.calculationchallengev2.model

import java.io.Serializable

data class Equation(
    var formula: String,
    var solution: String,
    var score: Int,
    var time: Int): Serializable