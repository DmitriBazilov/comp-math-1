package dmitri.comp.math

import dmitri.comp.math.SLAE.GaussSLAESolver
import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.SLAEAnswer
import dmitri.comp.math.processors.SLAEGaussProcessor
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.reader.MatrixUserReader
import dmitri.comp.math.util.InfoPrinter
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.NoSuchElementException

class App {

    val chooseMethod : String
        get() {
            return "Что нужно решить?" +
                    "\n\t 1. Система линейных уравнений(Метод Гаусса)" +
                    "\n\t 2. Система нелинейных уравнений(Метод половинного деления, Метод Ньютона, Метод простой итерации)" +
                    "\n\t 3. Нелинейное уравнение(Метод Ньютона)"
        }

    fun start() {
        println(chooseMethod)
        print("Введите номер: ")
        var method : Int? = null
        do {
            var methodString : String = readln()
            if (methodString != "1" && methodString != "2" && methodString != "3") {
                print("Некорректный номер. Попробуйте еще раз: ")
            } else {
                method = methodString.toInt()
            }
        } while (method == null)

        when (method) {
            1 -> SLAEGaussProcessor().processMethod()
            else -> TODO()
        }
    }
    
}

fun main() {
    Locale.setDefault(Locale.US)
    App().start()
}
