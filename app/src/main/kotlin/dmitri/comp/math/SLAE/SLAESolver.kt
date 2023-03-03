package dmitri.comp.math.SLAE

import dmitri.comp.math.entity.Matrix

interface SLAESolver<T> {

    fun solve(matrix : Matrix) : T?

}