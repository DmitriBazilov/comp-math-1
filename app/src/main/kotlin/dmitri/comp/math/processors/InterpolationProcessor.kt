package dmitri.comp.math.processors

import dmitri.comp.math.entity.Point
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.InterpolationSolver
import dmitri.comp.math.interfaces.MethodProcessor
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.solvers.interpolation.InterpolationLagrangeSolver
import dmitri.comp.math.solvers.interpolation.InterpolationNewtonSolver
import dmitri.comp.math.util.FiniteDifferenceCounter
import java.io.File
import java.util.*
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.system.exitProcess

class InterpolationProcessor : MethodProcessor {

    private val MAX_POINTS = 30

    val methods = listOf<InterpolationSolver>(
        InterpolationLagrangeSolver(),
        InterpolationNewtonSolver()
    )

    val functions = mapOf(
        Pair(1) { x: Double -> x * x },
        Pair(2) { x: Double -> sqrt(x) },
        Pair(3) { x: Double -> sin(x) }
    )

    val functionsName = listOf(
        "x^2",
        "sqrt(x)",
        "sin(x)"
    )

    var reader = InfoUserReader(Scanner(System.`in`))

    var modeNumber = -1

    var readMethod = -1
    var userFile : File = File("")

    var functionNumber = -1

    var pointsNumber = -1
    var points: List<Point> = listOf()

    var interpolationPoint = Double.MIN_VALUE

    var solverMethod = -1

    override fun processMethod() {
        readMode()
        readSolverMethod()
        if (modeNumber == 1) {
            readInputMethod()
            readInterpolationPoint()
            if (readMethod == 2) {
                readFile()
                reader = InfoUserReader(Scanner(userFile))
                reader.mode = 2
            }
            readPoints()
        } else if (modeNumber == 2) {
            readFunction()
            print("Введите интервал: ")
            var interval = reader.readInterval()
            readPointsNumber()
            makeDots(functions[functionNumber]!!, interval, pointsNumber)
            readInterpolationPoint()
            points.forEach {print("$it ")}
        }

        var coefs : DoubleArray = methods[solverMethod - 1].solve(points)
        if (solverMethod == 1) {
            println("Коэффициенты лагранжа")
            coefs.forEach { print(String.format("%.3f ", it)) }
            println()
            println("Результат для Лагранжа: " + methods[solverMethod - 1].getResult(interpolationPoint, points, coefs))
        } else {
            println("Коэффициенты Ньютона")
            coefs.forEach { print(String.format("%.3f ", it)) }
            println()
            println("Результат для Ньютона: " + methods[solverMethod - 1].getResult(interpolationPoint, points, coefs))
            var finiteDiffs = FiniteDifferenceCounter().count(points)
            for (i in finiteDiffs.indices) {
                for (j in finiteDiffs[i].indices) {
                    print(
                        String.format("%7.3f ", finiteDiffs[i][j])
                    )
                }
                println()
                println("-------------------------------------------------")
            }
        }

    }

    private fun readSolverMethod() {
        print("Выберите метод интерполяции(1-Лагранж, 2-Ньютон): ")
        solverMethod = reader.readMode()
    }

    private fun readInterpolationPoint() {
        do {
            try {
                var userPoint = reader.readInterpolationPoint()
                interpolationPoint = userPoint
            } catch (ex : InputMismatchException) {
                reader.readLine()
            } catch (ex : NoSuchElementException) {
                exitProcess(-1)
            }
        } while (interpolationPoint == Double.MIN_VALUE)
    }

    fun readPoints() {
        readPointsNumber()
        points = reader.readPoints(pointsNumber)
    }

    fun readFunction() {
        println("Выберите функцию")
        functionsName.forEachIndexed { index, s -> println("${index + 1}. $s") }
        do {
            try {
                var number = reader.readMode()
                if (number in 1..functionsName.size) {
                    functionNumber = number
                }
            } catch (ex : InputMismatchException) {
                reader.readLine()
            } catch (ex : NoSuchElementException) {
                exitProcess(-1)
            }
        } while (functionNumber == -1)
    }

    fun readInputMethod() {
        print("Как считать точки(1-консоль, 2-файл): ")
        do {
            try {
                var userMethod = reader.readMode()
                if (userMethod == 1 || userMethod == 2) {
                    readMethod = userMethod
                }
            } catch (ex : InputMismatchException) {
                reader.readLine()
            } catch (ex : NoSuchElementException) {
                exitProcess(-1)
            }
        } while (readMethod == -1)
    }

    fun readFile() {
        userFile = reader.readFile()
    }

    fun readPointsNumber() {
        do {
            try {
                var number = reader.readPointsNumber()
                if (number in 2..MAX_POINTS) {
                    pointsNumber = number
                }
            } catch (ex : InputMismatchException) {
                reader.readLine()
            } catch (ex : NoSuchElementException) {
                exitProcess(-1)
            }
        } while (pointsNumber == -1)
    }

    fun readMode() {
        do {
            print("Выберите способ ввода(1-набор точек, 2-функция): ")
            try {
                var number = reader.readMode()
                if (number in 1..2) {
                    modeNumber = number
                }
            } catch (ex : InputMismatchException) {
                reader.readLine()
            } catch (ex : NoSuchElementException) {
                exitProcess(-1)
            }
        } while (modeNumber == -1)
    }

    fun makeDots(func: (x : Double) -> Double, interval : SearchInterval, n : Int ) {
        var result = mutableListOf<Point>()
        var h = (interval.right - interval.left) / (n - 1)
        var left = interval.left
        for (i in 0 until n) {
            result.add(Point(left, func(left)))
            left += h
        }
        points = result
    }
}