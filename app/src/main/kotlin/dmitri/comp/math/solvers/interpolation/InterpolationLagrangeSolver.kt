package dmitri.comp.math.solvers.interpolation

import dmitri.comp.math.entity.Point
import dmitri.comp.math.interfaces.InterpolationSolver

class InterpolationLagrangeSolver : InterpolationSolver {

    override fun solve(points: List<Point>): DoubleArray {
        val n: Int = points.size
        val l = DoubleArray(n)


        for (i in 0 until n) {
            var c = 1.0
            for (j in 0 until n) {
                if (i != j) {
                    c *= (points[i].x - points[j].x)
                }
            }
            l[i] = points[i].y / c
        }

        return l
    }

    override fun getResult(x: Double, points: List<Point>, coefs: DoubleArray) : Double {
        var result = 0.0
        for (i in points.indices) {
            var product = 1.0
            for (j in points.indices) {
                if (i != j) {
                    product *= (x - points[j].x)
                }
            }
            result += coefs[i] * product
        }
        return result
    }
}