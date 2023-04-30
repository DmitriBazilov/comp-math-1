package dmitri.comp.math.solvers

import dmitri.comp.math.approximation_methods.*
import dmitri.comp.math.entity.ApproximationAnswer
import dmitri.comp.math.entity.Point
import dmitri.comp.math.interfaces.ApproximationSolver
import dmitri.comp.math.util.InfoPrinter
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sign

class ApproximationSolverImpl : ApproximationSolver<ApproximationAnswer> {

    val methods: List<Method> = listOf(
        LinearMethod(),
        SquareMethod(),
        CubicMethod(),
        PowerMethod(),
        LogarithmicMethod(),
        ExponentialMethod()
    )

    val methodsName: List<String> = listOf(
        "linear",
        "square",
        "cubic",
        "power",
        "logarithmic",
        "exponential"
    )

    val functions: List<(x: Double, coef: List<Double>) -> Double> = listOf(
        { x, coef -> coef[0] + x * coef[1] },
        { x, coef -> coef[0] + x * coef[1] + coef[2] * x * x },
        { x, coef -> coef[0] + coef[1] * x + coef[2] * x * x + coef[3] * x * x * x },
        { x, coef -> coef[0] * x.pow(coef[1]) },
        { x, coef -> coef[0] * ln(x) + coef[1] },
        { x, coef -> coef[0] * exp(coef[1] * x) }
    )

    override fun solve(size: Int, points: List<Point>): ApproximationAnswer {

        var results: List<List<Double>?> = methods.asSequence()
            .map { it -> it.solve(points.map { it.copy() }) }
            .toList()

        var mnk: List<Double> = results.asSequence()
            .map { it?.last() ?: Double.MAX_VALUE }
            .toList()

        mnk.forEach {print("$it ")}
        var mn: Double = mnk.min()
        var name: String = methodsName[mnk.indexOf(mn)]
        var coef: List<Double> = results[mnk.indexOf(mn)]!!.subList(0, results[mnk.indexOf(mn)]!!.size)
        var func = functions[mnk.indexOf(mn)]

        println("index: ${mnk.indexOf(mn)}")
        print("mnks: ")
        results.forEach { print(" " + (it?.last() ?: "null")) }
        println()
        println("Коэффициенты")
        for (i in results) {
            i?.let { InfoPrinter().printArray(it.toTypedArray()) }
        }

        return ApproximationAnswer(
            0,
            name,
            points,
            coef,
            func,
            mn
        )
    }
}
