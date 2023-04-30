package dmitri.comp.math.approximation_methods

import dmitri.comp.math.entity.Point
import dmitri.comp.math.util.ApproximationCounter
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow

class PowerMethod : Method {

    override fun solve(points: List<Point>) : List<Double>? {
        var tmp = points.map { it.copy() }.toList()
        for (p: Point in points) {
            if (p.x <= 0 || p.y <= 0) return null
            p.x = ln(p.x)
            p.y = ln(p.y)
        }
        var coef = LinearMethod().solve(points)?.toMutableList() ?: return null
        var a = exp(coef[0])
        coef[0] = coef[1]
        coef[1] = a
        coef[2] = ApproximationCounter().count(tmp) {x: Double -> coef[0] * x.pow(coef[1])}
        return coef
    }

}