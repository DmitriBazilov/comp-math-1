package dmitri.comp.math.entity

import dmitri.comp.math.interfaces.EquationSystem
import java.util.function.BiFunction
import kotlin.math.pow

class firstSystem : EquationSystem() {
    override val system: String
        get() = " { x^2 + y^2 = 4\n<\n { y = 3x^2"

    override val firstEquation: (Double, Double) -> Double
        get() = { x, y -> -(x.pow(2)) - (y.pow(2)) + 4 }

    override val secondEquation: (Double, Double) -> Double
        get() = { x, y -> y + -3 * x.pow(2) }

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