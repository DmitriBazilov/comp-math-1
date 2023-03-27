package dmitri.comp.math.equation

import dmitri.comp.math.interfaces.Equation
import kotlin.math.cos
import kotlin.math.sin

class TranscendentalEquation(override val eq: String = "sin(2x) + cos(x) = 0") : Equation {
    override fun f(x: Double): Double {
        return sin(2 * x) + cos(x)
    }

    override fun fDerivative(x: Double): Double {
        return 2 * cos(2 * x) - sin(x)
    }

    override fun fDerivativeDerivative(x: Double): Double {
        return -4.0 * sin(2 * x) - cos(x)
    }
}