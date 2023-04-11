package dmitri.comp.math.solvers

import dmitri.comp.math.entity.IntegralAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.Equation
import dmitri.comp.math.interfaces.IntegralSolver
import kotlin.math.abs
import kotlin.math.pow

class IntegralRightRectangleSolver: IntegralSolver<IntegralAnswer> {

    private val DEFAULT_N: Long = 5
    private val ACCURACY_ORDER : Double = 2.0
    override fun solve(interval: SearchInterval, equation: Equation, eps: Double): IntegralAnswer {
        var prevAns : Double = Double.MAX_VALUE
        var current : Double
        var n: Long = DEFAULT_N
        var it = 0

        while (true) {
            var step : Double = (interval.right - interval.left) / n
            println("текущий шаг: $step")
            it++
            current = 0.0
            var x0 = interval.left
            for (i in 0 until n) {
                var x1 = x0 + step
                var sq = equation.f(x1) * step
                current += sq
                x0 += step
            }
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