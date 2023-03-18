package dmitri.comp.math.equation

import dmitri.comp.math.interfaces.EquationSystem
import kotlin.math.cos
import kotlin.math.sin

class SecondSystem : EquationSystem() {
    override val system: String
        get() = " { sin(x) + y - 1 = 0\n<\n { -2x^3 - 4y + 5 = 0"

    override val firstEquation: (Double, Double) -> Double
        get() = { x, y -> -sin(x) - y + 1 }
    override val secondEquation: (Double, Double) -> Double
        get() = { x, y -> 2 * x * x * x + 4 * y - 5 }
    override val jacobianEquations: List<List<(Double, Double) -> Double>>
        get() {
            val result = ArrayList<List<(Double, Double) -> Double>>()
            val firstLine = ArrayList<(Double, Double) -> Double>()
            val secondLine = ArrayList<(Double, Double) -> Double>()

            firstLine.add { x, y -> cos(x) }
            firstLine.add { x, y -> 1.0 }
            firstLine.add(firstEquation)

            secondLine.add {x, y -> -6 * x * 2}
            secondLine.add {x, y -> -4.0}
            secondLine.add(secondEquation)

            result.add(firstLine)
            result.add(secondLine)
            return result
        }

}