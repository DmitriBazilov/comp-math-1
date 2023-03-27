package dmitri.comp.math.solvers

import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.equation.QuadraticEquation
import dmitri.comp.math.interfaces.Equation
import dmitri.comp.math.interfaces.NotLinearEquationSolver
import java.lang.Double.max
import kotlin.math.abs

class NotLinearEquationNewtonSolver : NotLinearEquationSolver<SearchInterval, NotLinearEquationAnswer> {
    override fun solve(interval: SearchInterval, equation: Equation, eps: Double): NotLinearEquationAnswer {
        var a = interval.left
        var b = interval.right
        var x0: Double =
            if (equation.f(a) * equation.fDerivativeDerivative(a) > 0.0)
                a
            else
                b
        var cnt : Int = 0
        do {
            cnt++
            x0 -= equation.f(x0) / equation.fDerivative(x0)
        } while (abs(equation.f(x0)) > eps)
        return NotLinearEquationAnswer(0, x0, cnt, equation)
    }
}