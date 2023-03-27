package dmitri.comp.math.interfaces

interface NotLinearEquationSolver<E, T> {

    fun solve(interval: E, equation: Equation, eps: Double): T
}