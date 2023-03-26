package dmitri.comp.math.equation

import dmitri.comp.math.interfaces.Equation

class QuadraticEquation(override val eq: String = "x^2 - 8*x + 9") : Equation {

    override fun f(x: Double) : Double {
        return x * x - 8 * x + 9
    }

    override fun fDerivative(x: Double) : Double {
        return 2 * x - 8
    }
}