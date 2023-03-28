package dmitri.comp.math.interfaces

import dmitri.comp.math.entity.SearchInterval

interface IntegralRectangleSolver<R>: IntegralSolver<R> {

    fun getHeight(equation: Equation, left: Double, right: Double)
}