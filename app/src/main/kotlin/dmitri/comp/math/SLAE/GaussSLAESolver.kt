package dmitri.comp.math.SLAE

import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.SLAEAnswer

class GaussSLAESolver : SLAESolver<SLAEAnswer> {

    override fun solve(matrix : Matrix): SLAEAnswer? {
        val size = matrix.size

        for (i in 0 until size) {
            if (matrix.matrix[i][i] == 0.0) {
                //todo replace rows
            }

            for (j in i + 1 .. size) {
                val c : Double = matrix.matrix[j][i] / matrix.matrix[i][i]

                matrix.plusToRow(matrix.getMultipliedRow(-c, i), j)

            }
        }


        return null
    }
}