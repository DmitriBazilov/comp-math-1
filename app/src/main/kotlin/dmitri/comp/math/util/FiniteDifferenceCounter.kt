package dmitri.comp.math.util

import dmitri.comp.math.entity.Point

class FiniteDifferenceCounter {

    fun count(points: List<Point>): Array<DoubleArray> {
        val n = points.size
        val arr = Array(n + 1) { DoubleArray(n) }
        for (i in 0 until n) {
            arr[0][i] = points[i].x
            arr[1][i] = points[i].y
        }
        for (i in 2 until n + 1) {
            for (j in 0 until n + 1 - i) {
                arr[i][j] = arr[i - 1][j + 1] - arr[i - 1][j]
            }
        }
        return arr
    }
}