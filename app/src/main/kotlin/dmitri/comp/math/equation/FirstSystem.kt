package dmitri.comp.math.equation

import dmitri.comp.math.interfaces.EquationSystem
import kotlin.math.pow
import kotlin.math.sqrt

class FirstSystem : EquationSystem() {
    override val system: String
        get() = " { x^2 - 4 = y^2\n<\n { y = 3x^2"

    override val firstEquation: (Double, Double) -> Double
        get() = { x, y -> 4 - x * x - y * y }

    override val secondEquation: (Double, Double) -> Double
        get() = { x, y -> 3 * x * x - y }

    override val jacobianEquations: List<List<(Double, Double) -> Double>>
        get() {
            val result = ArrayList<List<(Double, Double) -> Double>>()
            val firstLine = ArrayList<(Double, Double) -> Double>()
            val secondLine = ArrayList<(Double, Double) -> Double>()
            firstLine.add { x, y -> 2 * x }
            firstLine.add { x, y -> 2 * y }
            firstLine.add(firstEquation)

            secondLine.add { x, y -> -6 * x }
            secondLine.add { x, y -> 1.0 }
            secondLine.add(secondEquation)

            result.add(firstLine)
            result.add(secondLine)

            return result
        }
}