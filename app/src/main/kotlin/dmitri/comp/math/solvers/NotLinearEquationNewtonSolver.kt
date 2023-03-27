package dmitri.comp.math.solvers

import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.equation.QuadraticEquation
import dmitri.comp.math.interfaces.Equation
import dmitri.comp.math.interfaces.NotLinearEquationSolver

class NotLinearEquationNewtonSolver : NotLinearEquationSolver<SearchInterval, NotLinearEquationAnswer> {
    override fun solve(interval: SearchInterval, equation: Equation, eps: Double): NotLinearEquationAnswer {

        TODO()
    }
}