package dmitri.comp.math.approximation_methods

import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.Point
import dmitri.comp.math.entity.SLAEAnswer
import dmitri.comp.math.solvers.GaussSLAESolver
import dmitri.comp.math.util.ApproximationCounter
import dmitri.comp.math.util.InfoPrinter
import java.util.*


class CubicMethod : Method {

    private var SX = 0.0
    private var SY = 0.0

    private var S2X = 0.0
    private var S3X = 0.0
    private var S4X = 0.0
    private var S5X = 0.0
    private var S6X = 0.0

    private var SXY = 0.0
    private var SXXY = 0.0
    private var SXXXY = 0.0

    override fun solve(points: List<Point>): List<Double>? {
        val n = points.size
        for (p in points) {
            val x: Double = p.x
            val y: Double = p.y
            SX += x
            SY += y
            S2X += x * x
            SXY += x * y
            S3X += x * x * x
            S4X += x * x * x * x
            S5X += x * x * x * x * x
            S6X += x * x * x * x * x * x
            SXXY += x * x * y
            SXXXY += x * x * x * y
        }
        val matrix = Array(4) { Array(5) {0.0} }
        matrix[0] = arrayOf(n.toDouble(), SX, S2X, S3X, SY)
        matrix[1] = arrayOf(SX, S2X, S3X, S4X, SXY)
        matrix[2] = arrayOf(S2X, S3X, S4X, S5X, SXXY)
        matrix[3] = arrayOf(S3X, S4X, S5X, S6X, SXXXY)
        val answer: SLAEAnswer = GaussSLAESolver().solve(Matrix(4, matrix)) ?: return null
        if (answer.status != SLAEAnswer.GaussResult.OK) return null
        val x0 = answer.roots!![0]
        val x1 = answer.roots!![1]
        val x2 = answer.roots!![2]
        val x3 = answer.roots!![3]
        return listOf(x0, x1, x2, x3,
            ApproximationCounter().count(points) { x -> x0 + x1 * x + x2 * x * x + x3 * x * x * x })

    }
}