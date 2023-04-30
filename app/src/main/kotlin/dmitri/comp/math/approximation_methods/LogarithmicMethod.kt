package dmitri.comp.math.approximation_methods

import dmitri.comp.math.entity.ApproximationAnswer
import dmitri.comp.math.entity.Point
import dmitri.comp.math.util.ApproximationCounter
import javax.sound.sampled.Line
import kotlin.math.ln

class LogarithmicMethod : Method {

    override fun solve(points: List<Point>) : List<Double>? {
        var tmp = points.map { it.copy() }.toList()
        for (p: Point in points) {
            if (p.x <= 0) return null
            p.x = ln(p.x)
        }
        var coef = LinearMethod().solve(points)?.toMutableList() ?: return null
        coef[2] = ApproximationCounter().count(tmp) {x: Double -> coef[0] + ln(x) * coef[1]}
        return coef
    }
}