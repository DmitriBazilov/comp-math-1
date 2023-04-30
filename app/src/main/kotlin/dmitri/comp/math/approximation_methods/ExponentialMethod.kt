package dmitri.comp.math.approximation_methods

import dmitri.comp.math.entity.Point
import dmitri.comp.math.util.ApproximationCounter
import kotlin.math.exp
import kotlin.math.ln

class ExponentialMethod : Method {

    override fun solve(points: List<Point>) : List<Double>? {
        val tmp = points.map { it.copy() }.toList()
        for (p: Point in points) {
            if (p.y <= 0) return null
            p.y = ln(p.y)
        }
        var result = LinearMethod().solve(points)?.toMutableList() ?: return null
        result[0] = exp(result[0])
        var a = result[0]
        result[0] = result[1]
        result[1] = a
        result[2] = ApproximationCounter().count(tmp) {x -> result[0] * exp(result[1] * x)}
        return result
    }
}