package dmitri.comp.math.util

import dmitri.comp.math.entity.Point
import kotlin.math.pow
import kotlin.math.sqrt

class ApproximationCounter {

    fun count(points: List<Point>, fi : (x : Double) -> Double): Double {
        var s = 0.0
        for (p in points) {
            s += fi(p.x - p.y).pow(2)
        }
        return sqrt(s / points.size)
    }
}