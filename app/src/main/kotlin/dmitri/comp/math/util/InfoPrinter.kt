package dmitri.comp.math.util

import dmitri.comp.math.entity.*
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

    public fun <T> printArray(array: Array<T>, delimiter : String) {
        for (i in array.indices) {
            print(array[i])
            print(delimiter)
        }
        println()
    }

    fun printSLAEAnswer(answer : SLAEAnswer) {
        println(answer.status.message)
        printDelimiter()
        if (answer.status == SLAEAnswer.GaussResult.OK) {
            println("Треугольная матрица: ")
            printMatrix(answer.triangleMatrix!!)
            printDelimiter()
            println("Определитель матрицы: " + answer.det)
            printDelimiter()
            println("Количество перестановок строк: " + answer.swaps)
            printDelimiter()
            print("Корни системы: ")
            printArray <Double> (answer.roots!!.toTypedArray())
            printDelimiter()
            println("Невязки: ")
            printArray<String>(answer.discrepancies!!, '\n'.toString())
            println()
//            printArray <Double> (answer.discrepancies!!.toTypedArray())
        }
    }

    fun printNewtonAnswer(answer : NewtonSystemAnswer) {
        if (answer.status != 0) {
            println("Начальное приближение равноудаленно от точек решения")
        } else {
            println("Решение системы на данном интервале: " + answer.x + ", " + answer.y)
        }
    }

    fun printIntegralAnswer(answer: IntegralAnswer) {
        if (answer.status != 0) {
            println("Не получилось решить интеграл")
        } else {
            println("Приближенное решение интеграла для уравнения ${answer.equation.eq} на интервале ${answer.interval} = ${answer.answer}")
            println("Решение было найдено за ${answer.it} итераций")
        }
    }

    fun printApproximationAnswer(answer: ApproximationAnswer) {
        println("Итог")
        if (answer.status != 0) {
            println("Найти аппроксимацию не удалось")
        } else {
            println("Наилучшая аппроксимация: ${answer.name}.")
            println("Неточность: ${answer.mnk}")
            print("Коэффициенты: ")
            InfoPrinter().printArray(answer.coef.toTypedArray())
        }
    }
}
