package dmitri.comp.math.solvers

import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.Equation
import dmitri.comp.math.interfaces.NotLinearEquationSolver
import kotlin.math.abs

class NotLinearEquationSimpleIterationSolver : NotLinearEquationSolver<SearchInterval, NotLinearEquationAnswer> {
    override fun solve(interval: SearchInterval, equation: Equation, eps: Double): NotLinearEquationAnswer {
        var a = interval.left
        var b = interval.right
        var aDer = equation.fDerivative(a)
        var bDer = equation.fDerivative(b)
        var lambda = -1.0 / java.lang.Double.max(aDer, bDer)
        var x0 = b
        var x1 = fi(x0, equation, lambda)
        var count = 0
        do {
            count++
            var x2 = fi(x1, equation, lambda)
            var m : Double = abs(x1 - x0)
            if (m <= eps)
                break
            x0 = x1
            x1 = x2
        } while (true)
        return NotLinearEquationAnswer(0, x1, count, equation)
    }

    private fun fi(x: Double, equation: Equation, lambda: Double): Double {
        return x + lambda * equation.f(x)
    }
}