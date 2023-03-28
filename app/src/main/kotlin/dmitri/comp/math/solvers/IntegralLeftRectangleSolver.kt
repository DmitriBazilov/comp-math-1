package dmitri.comp.math.solvers

import dmitri.comp.math.entity.IntegralAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.Equation
import dmitri.comp.math.interfaces.IntegralRectangleSolver

class IntegralLeftRectangleSolver: IntegralRectangleSolver<IntegralAnswer> {

    override fun solve(interval: SearchInterval, equation: Equation, eps: Double): IntegralAnswer {
        TODO("Not yet implemented")
    }

    override fun getHeight(equation: Equation, left: Double, right: Double) {
        TODO("Not yet implemented")
    }

}