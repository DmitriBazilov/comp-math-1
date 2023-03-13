package dmitri.comp.math.SLAE

import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.SLAEAnswer

class GaussSLAESolver : SLAESolver<SLAEAnswer> {

    override fun solve(matrix : Matrix): SLAEAnswer? {
        val size = matrix.size

        for (i in 0 until size) {
            var k = i + 1
            while (matrix.matrix[i][i] == 0.0 && k < size) {
                matrix.swapLines(i, k)
                k++
            }
            if (matrix.matrix[i][i] == 0.0) {
                return SLAEAnswer(-1)
            }

            for (j in i + 1 .. size) {
                val c : Double = matrix.matrix[j][i] / matrix.matrix[i][i]

                matrix.plusToRow(matrix.getMultipliedRow(-c, i), j)

            }
        }

        TODO("need to add reverse")


        return null
    }
}