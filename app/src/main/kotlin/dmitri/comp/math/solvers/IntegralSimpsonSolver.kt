package dmitri.comp.math.solvers

import dmitri.comp.math.entity.IntegralAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.Equation
import dmitri.comp.math.interfaces.IntegralSolver
import kotlin.math.abs
import kotlin.math.pow

class IntegralSimpsonSolver: IntegralSolver<IntegralAnswer> {

    private val DEFAULT_N: Long = 4
    private val ACCURACY_ORDER: Double = 4.0

    override fun solve(interval: SearchInterval, equation: Equation, eps: Double): IntegralAnswer {
        var prevAns : Double = Double.MAX_VALUE
        var current : Double
        var n: Long = DEFAULT_N
        var it = 0

        while (true) {
            var step : Double = (interval.right - interval.left) / n
            println("текущий шаг: $step n : $n")
            current = equation.f(interval.left) + equation.f(interval.right)
            it++
            var x0 = interval.left
            for (i in 1 until n) {
                var x1 = x0 + step * i
                var sq = equation.f(x1) * 2
                if ((i % 2).toInt() != 0) {
                    sq *= 2
                }
                current += sq
            }
            current *= (step / 3.0)
            println("Новое значение интеграла: $current")
            if (abs(current - prevAns) <= eps) {
                break
            }
            prevAns = current
            n *= 2
            if (n > 1000000) {
                return IntegralAnswer(-1, equation, null, it, interval)
            }
        }
        println("n : $n")

        return IntegralAnswer(0, equation, current, it, interval)
    }
}