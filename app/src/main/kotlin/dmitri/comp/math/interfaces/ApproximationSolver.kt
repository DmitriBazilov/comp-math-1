package dmitri.comp.math.interfaces

import dmitri.comp.math.entity.Point

interface ApproximationSolver {

    fun solve(size: Int, points: List<Point>)
}