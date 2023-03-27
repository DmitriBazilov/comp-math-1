package dmitri.comp.math.entity

import dmitri.comp.math.interfaces.Equation

class NotLinearEquationAnswer(val status: Int, val x: Double, val cnt: Int, val equation: Equation) {
    override fun toString(): String {
        var result : String = if (status == 0) "Метод выполнился успешно\n" else "Метод завершился с ошибкой"
        if (status == 0) {
            result += "Корень: $x, количество итераций: $cnt, уравнение: ${equation.eq}"
        }
        return result
    }
}