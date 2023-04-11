package dmitri.comp.math.solvers

import dmitri.comp.math.entity.IntegralAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.Equation
import dmitri.comp.math.interfaces.IntegralSolver

class IntegralLeftRectangleSolver: IntegralSolver<IntegralAnswer> {

    override fun solve(interval: SearchInterval, equation: Equation, eps: Double): IntegralAnswer {
        TODO("Not yet implemented")
    }
}