package dmitri.comp.math.solvers

import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.Equation
import dmitri.comp.math.interfaces.NotLinearEquationSolver
import kotlin.math.abs

class NotLinearEquationHalfDivisionSolver : NotLinearEquationSolver<SearchInterval, NotLinearEquationAnswer> {
    override fun solve(interval: SearchInterval, equation: Equation, eps : Double): NotLinearEquationAnswer {
        var a = interval.left
        var b = interval.right
        var x : Double

        var counter = 0
        do {
            counter++
            x = (a + b) / 2.0
            if (equation.f(a) * equation.f(x) > 0) {
                a = x;
            } else {
                b = x;
            }
        } while (!(abs(a - b) <= eps) || !(abs(equation.f(x)) < eps));

        return NotLinearEquationAnswer(0, x, counter, equation)
    }
}