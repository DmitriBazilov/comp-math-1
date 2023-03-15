package dmitri.comp.math.interfaces

import dmitri.comp.math.entity.Matrix

interface SLAESolver<T> {

    fun solve(matrix : Matrix) : T?

}