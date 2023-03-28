package dmitri.comp.math.processors

import dmitri.comp.math.entity.NewtonSystemAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.equation.FirstSystem
import dmitri.comp.math.equation.SecondSystem
import dmitri.comp.math.graphics.GraphicDrawer
import dmitri.comp.math.interfaces.MethodProcessor
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.solvers.NotLinearSystemNewtonSolver
import dmitri.comp.math.util.InfoPrinter
import java.util.*
import kotlin.NoSuchElementException

class NotLinearSystemNewtonProcessor : MethodProcessor {

    var systemNumber : Int = 0
    var systemAmount : Int = 2
    var searchInterval : SearchInterval? = null
    val scanner : Scanner = Scanner(System.`in`)
    val chooseSystem : String
        get() = "Выберите систему из представленных ниже: "
    val inputInterval : String
        get() = "Введите начальное приближение(два числа через пробел): "

    override fun processMethod() {
        var infoReader : InfoUserReader = InfoUserReader(scanner)
        println(chooseSystem)
        println("1.\n" + FirstSystem().system)
        println("2.\n" + SecondSystem().system)
        print("Введите номер системы: ")

        do {
            try {
                val number = infoReader.readMode()
                if (number !in 1..systemAmount)
                    throw InputMismatchException()
                systemNumber = number
            } catch (badInputException : InputMismatchException) {
                print("Введите номер системы(1 или 2) : ")
                scanner.nextLine()
            } catch (endOfFileException : NoSuchElementException) {
                println("Ввод был прерван. Завершение программы")
                scanner.close()
                return
            }
        } while (systemNumber == 0)

        print(inputInterval)
        do {
            try {
                var userInterval : SearchInterval = infoReader.readInterval()
                if (userInterval.left !in -100.0..100.0 ||
                    userInterval.right !in -100.0..100.0) {

                    print("Введите два числа от -100 до 100: ")
                } else {
                    searchInterval = userInterval
                }
            } catch (badInputException : InputMismatchException) {
                print("Введите два числа от -100 до 100: ")
                scanner.nextLine()
            } catch (endOfFileException : NoSuchElementException) {
                println("Ввод был прерван. Завершение программы")
                scanner.close()
                return
            }
        } while (searchInterval == null)

        val answer : NewtonSystemAnswer = NotLinearSystemNewtonSolver().solve(
            searchInterval!!,
            if (systemNumber == 1) FirstSystem()
            else SecondSystem())

        InfoPrinter().printNewtonAnswer(answer)
        println("Колво итераций: " + answer.i)
        if (answer.status == 0) {
            if (systemNumber == 1) {
                println(FirstSystem().firstEquation.invoke(answer.x!!, answer.y!!))
                println(FirstSystem().secondEquation.invoke(answer.x!!, answer.y!!))

            } else {
                println(SecondSystem().firstEquation.invoke(answer.x!!, answer.y!!))
                println(SecondSystem().secondEquation.invoke(answer.x!!, answer.y!!))
            }
            GraphicDrawer().showSystem(answer)
        }
    }

}