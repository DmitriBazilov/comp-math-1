package dmitri.comp.math.util

import dmitri.comp.math.entity.Matrix

class InfoPrinter {

    public fun printMatrix(matrix: Matrix) {
        val matrixArray = matrix.matrix
        for (row in matrixArray) {
            printArray(row)
        }
    }

    public fun <T> printArray(array: Array<T>) {
        for (i in array.indices) {
            print(array[i])
            print(" ")
        }
        println()
    }
}