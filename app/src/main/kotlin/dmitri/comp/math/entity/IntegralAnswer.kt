package dmitri.comp.math.entity

import dmitri.comp.math.interfaces.Equation

class IntegralAnswer(val status : Int, val equation: Equation, val answer: Double?, val it: Int?, val interval: SearchInterval) {
}