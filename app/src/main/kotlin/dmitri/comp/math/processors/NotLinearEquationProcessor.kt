package dmitri.comp.math.processors

import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.equation.QuadraticEquation
import dmitri.comp.math.equation.ThirdDegreeEquation
import dmitri.comp.math.equation.TranscendentalEquation
import dmitri.comp.math.interfaces.Equation
import dmitri.comp.math.interfaces.MethodProcessor
import dmitri.comp.math.interfaces.NotLinearEquationSolver
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.solvers.NotLinearEquationHalfDivisionSolver
import dmitri.comp.math.solvers.NotLinearEquationNewtonSolver
import dmitri.comp.math.solvers.NotLinearEquationSimpleIterationSolver
import java.util.*

class NotLinearEquationProcessor : MethodProcessor {

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
    var equationNumber : Int = 0

    private val chooseMethod = "Выберите метод:" +
            "\n\t1. Метод половинных делений" +
            "\n\t2. Метод Ньютона" +
            "\n\t3. Метод простых итераций"

    private val inputInterval = "Введите интервал от (-100) до (100): "

    override fun processMethod() {

        readMethod()
        readInterval()
        readEquation()

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

    private fun readInterval() {
        do {
            print(inputInterval)
            try {
                var userInterval: SearchInterval = infoReader.readInterval()
                if (userInterval.left in -100.0..100.0 ||
                    userInterval.right in -100.0..100.0
                ) {
                    searchInterval = userInterval
                }


            } catch (badInputException: InputMismatchException) {
                scanner.nextLine()
            } catch (endOfFileException: NoSuchElementException) {
                println("Ввод был прерван. Завершение программы")
                scanner.close()
                return
            }
        } while (searchInterval == null)
    }

    private fun readEquation() {
    }
}