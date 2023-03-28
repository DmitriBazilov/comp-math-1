package dmitri.comp.math.processors

import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.equation.QuadraticEquation
import dmitri.comp.math.equation.ThirdDegreeEquation
import dmitri.comp.math.equation.TranscendentalEquation
import dmitri.comp.math.graphics.GraphicDrawer
import dmitri.comp.math.interfaces.Equation
import dmitri.comp.math.interfaces.MethodProcessor
import dmitri.comp.math.interfaces.NotLinearEquationSolver
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.solvers.NotLinearEquationHalfDivisionSolver
import dmitri.comp.math.solvers.NotLinearEquationNewtonSolver
import dmitri.comp.math.solvers.NotLinearEquationSimpleIterationSolver
import dmitri.comp.math.util.IntervalFinder
import java.util.*
import kotlin.NoSuchElementException
import kotlin.system.exitProcess

class NotLinearEquationProcessor : MethodProcessor {

    final val MIN_EPS: Double = 1.0E-9

    val scanner: Scanner = Scanner(System.`in`)
    val infoReader = InfoUserReader(scanner)

    val methods: Map<Int, NotLinearEquationSolver<SearchInterval, NotLinearEquationAnswer>> = mapOf(
        Pair(1, NotLinearEquationHalfDivisionSolver()),
        Pair(2, NotLinearEquationNewtonSolver()),
        Pair(3, NotLinearEquationSimpleIterationSolver())
    )

    val equations: List<Equation> = arrayListOf(
        QuadraticEquation(),
        ThirdDegreeEquation(),
        TranscendentalEquation()
    )

    var methodNumber: Int = 0
    var searchInterval: SearchInterval? = null
    var equationNumber: Int = 0
    var eps: Double = 0.01


    private val chooseMethod = "Выберите метод:" +
            "\n\t1. Метод половинных делений" +
            "\n\t2. Метод Ньютона" +
            "\n\t3. Метод простых итераций"

    private val inputInterval = "Введите интервал от (-100) до (100): "

    override fun processMethod() {

        readMethod()
        readEquation()

        suggestInterval()

        readEpsilon()

        val solver = methods[methodNumber]

        var answer = solver!!.solve(searchInterval!!, equations[equationNumber - 1], eps)

        println(answer)
        println(equations[equationNumber - 1].f(answer.x))

        GraphicDrawer().showEquation(answer, searchInterval!!)
    }

    private fun readMethod() {
        println(chooseMethod)
        do {
            try {
                print("Выберите номер метода(1.." + methods.size + "): ")
                var number = scanner.nextInt()
                if (number in 1..methods.size) {
                    methodNumber = number
                }
            } catch (badInputException: InputMismatchException) {
                scanner.nextLine()
            } catch (endOfFileException: NoSuchElementException) {
                println("Ввод был прерван. Завершение программы")
                scanner.close()
                return
            }
        } while (methodNumber !in 1..3)
    }

    private fun suggestInterval() {
        var intervalFinder = IntervalFinder()
        do {
            readInterval()
            var intervals = intervalFinder.findIntervals(equations[equationNumber - 1], searchInterval!!)
            if (intervals.isNotEmpty()) {
                searchInterval = chooseInterval(intervals)
                break
            } else {
                println("На данном промежутке корней не найдено. Попробуйте еще раз")
            }
        } while (true)
    }

    private fun chooseInterval(intervals: List<SearchInterval>): SearchInterval {

        println("Промежутки с корнями: ")
        for (i in intervals.indices) {
            println("\t" + (i + 1) + ". " + intervals[i].toString())
        }
        var chosenInterval: SearchInterval? = null
        do {
            print("Ввыберите промежуток из вышепредставленных: ")
            try {
                var number = infoReader.readMode()
                if (number in 1..intervals.size) {
                    chosenInterval = intervals[number - 1]
                }
            } catch (badInputException: InputMismatchException) {
                scanner.nextLine()
            } catch (endOfFileException : NoSuchElementException) {
                println("Ввод был прерван. Завершение программы")
                scanner.close()
                exitProcess(-1)
            }

        } while (chosenInterval == null)
        return chosenInterval
    }

    private fun readInterval() {
        var newInterval: SearchInterval? = null
        do {
            print(inputInterval)
            try {
                var userInterval: SearchInterval = infoReader.readInterval()
                if (userInterval.left in ((-100.0) .. (100.0)) ||
                    userInterval.right in ((-100.0) .. (100.0))
                ) {
                    newInterval = userInterval
                }


            } catch (badInputException: InputMismatchException) {
                scanner.nextLine()
            } catch (endOfFileException: NoSuchElementException) {
                println("Ввод был прерван. Завершение программы")
                scanner.close()
                return
            }
        } while (newInterval == null)
        searchInterval = newInterval
    }

    private fun readEquation() {
        println("Список нелинейных уравнений:")
        for (idx in equations.indices) {
            println("\t" + (idx + 1) + ". " + equations[idx].eq)
        }
        do {
            print("Выберите уравнение: ")
            try {
                var number = infoReader.readMode()
                if (number in 1..equations.size) {
                    equationNumber = number
                }
            } catch (badInputException: InputMismatchException) {
                scanner.nextLine()
            } catch (endOfFileException : NoSuchElementException) {
                println("Ввод был прерван. Завершение программы")
                scanner.close()
                return
            }
        } while (equationNumber == 0)
    }

    private fun readEpsilon() {

        do {
            try {
                print("Введите требуемую точность(0 - точность по умолчанию): ")
                var userEps = infoReader.readEpsilon()
                if (userEps == 0.0) return
                if (userEps < MIN_EPS) {
                    println("Введите точность поменьше(eps>1E-9)")
                } else {
                    eps = userEps
                    return
                }
            } catch (badInputException: InputMismatchException) {
                scanner.nextLine()
            } catch (endOfFileException: NoSuchElementException) {
                println("Ввод был прерван. Завершение программы")
                scanner.close()
                return
            }
        } while (true)
    }
}