package dmitri.comp.math.interfaces

import dmitri.comp.math.entity.Point

interface InterpolationSolver {

    fun solve(points : List<Point>) : DoubleArray
}