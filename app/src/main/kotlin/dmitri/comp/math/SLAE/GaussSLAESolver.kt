package dmitri.comp.math.SLAE

import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.SLAEAnswer
import dmitri.comp.math.util.InfoPrinter
import java.math.BigDecimal
import java.text.DecimalFormat

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

    private fun getDiscrepancies(matrix: Matrix, roots: DoubleArray) : Array<String> {
        var discrepancies = Array<String>(matrix.size) {""}
        for (i in 0 until matrix.size) {
             var tmp : BigDecimal = BigDecimal(0.0)
            for (j in 0 until matrix.size) {
                 var tmp2 = BigDecimal(matrix.matrix[i][j])
                 tmp2 = tmp2.multiply(BigDecimal(roots[j]))
                 tmp = tmp.plus(tmp2)
            }
//            println(tmp)
            val format : DecimalFormat = DecimalFormat("0.00E00")

            discrepancies[i] = format.format(tmp.minus(BigDecimal(matrix.matrix[i][matrix.size])))
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
