package dmitri.comp.math.SLAE

import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.SLAEAnswer
import dmitri.comp.math.util.InfoPrinter
import java.math.BigDecimal

class GaussSLAESolver : SLAESolver<SLAEAnswer> {

    override fun solve(matrix : Matrix): SLAEAnswer? {
        val size = matrix.size

        for (i in 0 until size - 1) {
            var k = i + 1
            while (matrix.matrix[i][i] == 0.0 && k < size) {
                matrix.swapLines(i, k)
                k++
            }
            if (matrix.matrix[i][i] == 0.0) {
                return SLAEAnswer(SLAEAnswer.GaussResult.NOT_ZERO_ELEMENT)
            }

            for (j in i + 1 until size) {
                val c : Double = matrix.matrix[j][i] / matrix.matrix[i][i]

                matrix.plusToRow(matrix.getMultipliedRow(-c, i), j)

            }
        }
        var roots = DoubleArray(size)

        for (i in size - 1 downTo  0) {
            var sum = 0.0
            for (j in i + 1 until size) {
                sum += matrix.matrix[i][j] * roots[j]
            }
            if (matrix.matrix[i][i] == 0.0) {
                return SLAEAnswer(SLAEAnswer.GaussResult.ZERO_DIAGONAL_ELEMENT)
            }
            roots[i] = (matrix.matrix[i][size] - sum) / matrix.matrix[i][i]
        }


        val answer = SLAEAnswer(SLAEAnswer.GaussResult.OK)
        answer.roots = roots
        answer.det = getDetFromTriangle(matrix)
        answer.discrepancies = getDiscrepancies(matrix, roots)
        answer.triangleMatrix = matrix
        return answer
        return null
    }

    private fun getDiscrepancies(matrix: Matrix, roots: DoubleArray) : DoubleArray {
        var discrepancies = DoubleArray(matrix.size) {0.0}
        for (i in 0 until matrix.size) {
            var sum = 0.0
            // var tmp : BigDecimal = BigDecimal(0.0)
            for (j in 0 until matrix.size) {
                sum += matrix.matrix[i][j] * roots[j]
                // var tmp2 = BigDecimal(matrix.matrix[i][j])
                // tmp2.multiply(BigDecimal(roots[j]))
                // print(tmp2)
                // tmp.add(tmp2)
                // println(tmp.toDouble())
            }
            discrepancies[i] = matrix.matrix[i][matrix.size] - sum
            // discrepancies[i] = tmp.toDouble()
        }
        return discrepancies
    }

    private fun getDetFromTriangle(matrix: Matrix) : Double {
        var det = 1.0
        for (i in 0 until matrix.size) {
            det *= matrix.matrix[i][i]
        }
        return det
    }
}
