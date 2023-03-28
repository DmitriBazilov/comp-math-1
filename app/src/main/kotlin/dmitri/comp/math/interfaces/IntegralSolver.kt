package dmitri.comp.math.interfaces

import dmitri.comp.math.entity.SearchInterval

interface IntegralSolver<R> {

    fun solve(interval : SearchInterval, equation: Equation, eps: Double) : R
}