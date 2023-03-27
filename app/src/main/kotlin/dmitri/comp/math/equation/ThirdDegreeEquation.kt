package dmitri.comp.math.equation

import dmitri.comp.math.interfaces.Equation

class ThirdDegreeEquation(override val eq: String = "x^3 + 2,28*x^2 − 1,934*x − 3,907") : Equation {
    override fun f(x: Double): Double {
        return x * x * x + 2.28 * x * x - 1.934 * x - 3.907
    }

    override fun fDerivative(x: Double): Double {
        return 3 * x * x + 2 * 2.28 * x - 1.934
    }

    override fun fDerivativeDerivative(x: Double): Double {
        return 6.0 * x + 2 * 2.28
    }
}