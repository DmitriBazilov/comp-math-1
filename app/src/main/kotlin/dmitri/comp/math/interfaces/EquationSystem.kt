package dmitri.comp.math.interfaces

import java.util.function.BiFunction

abstract class EquationSystem {

    abstract val system : String

    abstract val jacobianEquations : List<List<(Double, Double) -> Double>>

    abstract val firstEquation : (Double, Double) -> Double

    abstract val secondEquation : (Double, Double) -> Double

}