package dmitri.comp.math

import dmitri.comp.math.processors.*
import java.util.*

class App {

    val chooseMethod : String
        get() {
            return "Что нужно решить?" +
                    "\n\t 1. Система линейных уравнений(Метод Гаусса)" +
                    "\n\t 2. Система нелинейных уравнений(Метод Ньютона)" +
                    "\n\t 3. Нелинейное уравнение(Метод половинного деления, Метод Ньютона, Метод простой итерации)" +
                    "\n\t 4. Интеграл(Методы прямоугольников, Метод Симпсона, Метод трапеций" +
                    "\n\t 5. Апроксимация функции" +
                    "\n\t 6. Интерполяция"
        }

    fun start() {
        do {
            println(chooseMethod)
            print("Введите номер: ")
            var method : Int? = null
            do {
                var methodString : String = readln().trim()
                if (methodString != "1" && methodString != "2" &&
                    methodString != "3" && methodString != "4" &&
                    methodString != "5" && methodString != "6") {
                        print("Некорректный номер. Попробуйте еще раз: ")
                } else {
                    method = methodString.toInt()
                }
            } while (method == null)

            when (method) {
                1 -> SLAEGaussProcessor().processMethod()
                2 -> NotLinearSystemNewtonProcessor().processMethod()
                3 -> NotLinearEquationProcessor().processMethod()
                4 -> IntegralProcessor().processMethod()
                5 -> ApproximationProcessor().processMethod()
                6 -> InterpolationProcessor().processMethod()
            }
        } while (true)
    }
    
}

fun main() {
    Locale.setDefault(Locale.US)
    App().start()
}
