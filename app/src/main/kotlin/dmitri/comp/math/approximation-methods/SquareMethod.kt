package dmitri.comp.math.`approximation-methods`

import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.Point
import dmitri.comp.math.entity.SLAEAnswer
import dmitri.comp.math.solvers.GaussSLAESolver
import dmitri.comp.math.util.ApproximationCounter
import java.util.*


class SquareMethod {

    private var SX = 0.0
    private var SY = 0.0

    private var SXX = 0.0
    private var SXXX = 0.0
    private var SXXXX = 0.0

    private var SXY = 0.0
    private var SXXY = 0.0

    fun solve(points: List<Point>): List<Double?> {
        val n: Int = points.size

        for (p in points) {
            val x: Double = p.x
            val y: Double = p.y
            SX += x
            SY += y
            SXX += x * x
            SXY += x * y
            SXXX += x * x * x
            SXXXX += x * x * x * x
            SXXY += x * x * y
        }

        val matrix = Array(3) { Array(4) {0.0} }
        matrix[0] = arrayOf(n.toDouble(), SX, SXX, SY)
        matrix[1] = arrayOf(SX, SXX, SXXX, SXY)
        matrix[2] = arrayOf(SXX, SXXX, SXXXX, SXXY)
        val answer: SLAEAnswer? = GaussSLAESolver().solve(Matrix(3, matrix))


        val x0 = answer!!.roots!![0]
        val x1 = answer.roots!![1]
        val x2 = answer.roots!![2]

        return listOf(x0, x1, x2,
                ApproximationCounter().count(points) { x -> x0 + x1 * x + x2 * x * x })

    }
}