package dmitri.comp.math.util

import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.SLAEAnswer
import java.math.BigDecimal

class InfoPrinter {

    public fun printDelimiter() {
        println("---------------------------------------------------------")
    }

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

    public fun printSLAEAnswer(answer : SLAEAnswer) {
        println(answer.status.message)
        printDelimiter()
        if (answer.status == SLAEAnswer.GaussResult.OK) {
            println("Треугольная матрица: ")
            printMatrix(answer.triangleMatrix!!)
            printDelimiter()
            println("Определитель матрицы: " + answer.det)
            printDelimiter()
            print("Корни системы: ")
            printArray <Double> (answer.roots!!.toTypedArray())
            printDelimiter()
            print("Невязки: ")
            for (disc in answer.discrepancies!!) {
                val b = BigDecimal(disc)
                print(b)
                print(" ")
            }
            println()
//            printArray <Double> (answer.discrepancies!!.toTypedArray())
        }
    }
}
