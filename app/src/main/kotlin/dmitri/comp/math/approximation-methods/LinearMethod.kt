package dmitri.comp.math.`approximation-methods`

import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.Point
import dmitri.comp.math.entity.SLAEAnswer
import dmitri.comp.math.solvers.GaussSLAESolver
import dmitri.comp.math.util.ApproximationCounter


class LinearMethod {

    private var SX = 0.0
    private var SY = 0.0
    private var SXX = 0.0
    private var SXY = 0.0

    fun solve(points: List<Point>): List<Double>? {
        val n = points.size
        for (p in points) {
            val x: Double = p.x
            val y: Double = p.y
            SX += x
            SY += y
            SXX += x * x
            SXY += x * y
        }
        val matrix = Array(2) { Array(3) {0.0} }
        matrix[0] = arrayOf(SXX, SX, SXY)
        matrix[1] = arrayOf(SX, n.toDouble(), SY)
        val answer: SLAEAnswer = GaussSLAESolver().solve(Matrix(2, matrix)) ?: return null
        val a = answer.roots!![0]
        val b = answer.roots!![1]
        val list: MutableList<Double> = ArrayList()
        list.add(a)
        list.add(b)
        list.add(ApproximationCounter().count(points) { x -> a * x + b })
        return list
    }
}