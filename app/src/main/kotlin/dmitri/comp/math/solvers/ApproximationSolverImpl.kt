package dmitri.comp.math.solvers

import dmitri.comp.math.`approximation-methods`.LinearMethod
import dmitri.comp.math.entity.Point
import dmitri.comp.math.interfaces.ApproximationSolver

class ApproximationSolverImpl : ApproximationSolver {
    override fun solve(size: Int, points: List<Point>) {
        var l : List<Double>? = LinearMethod().solve(points)
        l?.forEach { it -> print(it) }
    }
}