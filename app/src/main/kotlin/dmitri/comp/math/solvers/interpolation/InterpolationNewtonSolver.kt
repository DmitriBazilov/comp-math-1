package dmitri.comp.math.solvers.interpolation

import dmitri.comp.math.entity.Point
import dmitri.comp.math.interfaces.InterpolationSolver
import dmitri.comp.math.util.FiniteDifferenceCounter
import kotlin.math.pow

class InterpolationNewtonSolver : InterpolationSolver {

    override fun solve(points: List<Point>): DoubleArray {

        val n: Int = points.size
        val arr = Array(n) { DoubleArray(n) }

        for (i in 0 until n) {
            arr[i][0] = points[i].y
        }

        var k = 1
        while (k <= n) {
            for (i in 0 until n - k) {
                arr[i][k] = (arr[i + 1][k - 1] - arr[i][k - 1]) / (points[i + k].x - points[i].x)
            }
            k++
        }
        val brr = DoubleArray(n)
        for (i in 0 until n) {
            brr[i] = arr[0][i]
        }

        return brr
    }

    override fun getResult(x: Double, points: List<Point>, coefs: DoubleArray) : Double {
        var result = points[0].y
        for (i in 1 until coefs.size) {
            var product = 1.0
            for (j in 0 until i) {
                product *= (x - points[j].x)
            }
            result += coefs[i] * product
        }
        return result
    }

    private fun fact(n : Int) : Double {
        var result = 1.0
        for (i in 2..n) {
            result *= i
        }
        return result
    }
}