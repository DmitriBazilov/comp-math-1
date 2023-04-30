package dmitri.comp.math.interfaces

import dmitri.comp.math.entity.Point

interface ApproximationSolver<R> {

    fun solve(size: Int, points: List<Point>) : R
}