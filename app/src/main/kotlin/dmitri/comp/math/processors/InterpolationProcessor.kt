package dmitri.comp.math.processors

import dmitri.comp.math.entity.Point
import dmitri.comp.math.interfaces.InterpolationSolver
import dmitri.comp.math.interfaces.MethodProcessor
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.solvers.interpolation.InterpolationLagrangeSolver
import dmitri.comp.math.solvers.interpolation.InterpolationNewtonSolver
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

    override fun processMethod() {
//        readInputMethod()
//        if (readMethod == 2) {
//            reader = InfoUserReader(Scanner())
//        }

        readMode()
        if (modeNumber == 1) {
            readInputMethod()
            if (readMethod == 2) {
                readFile()
                reader = InfoUserReader(Scanner(userFile))
            }
            readPoints()
            readInterpolationPoint()
        } else if (modeNumber == 2) {

        }


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
        functionsName.forEachIndexed { index, s -> println("$index. $s") }
        do {
            try {
                var number = reader.readMode()
                if (number in functionsName.indices) {
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
}