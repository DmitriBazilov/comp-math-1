package dmitri.comp.math.processors

import dmitri.comp.math.entity.Point
import dmitri.comp.math.interfaces.ApproximationSolver
import dmitri.comp.math.interfaces.MethodProcessor
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.solvers.ApproximationSolverImpl
import java.util.*
import kotlin.NoSuchElementException
import kotlin.system.exitProcess

class ApproximationProcessor : MethodProcessor {

    val scanner: Scanner = Scanner(System.`in`)
    val infoReader = InfoUserReader(scanner)

    var pointsNumber: Int = 0
    var points: List<Point> = ArrayList<Point>(100)

    override fun processMethod() {
        readPointsNumber()
        readPoints()
        ApproximationSolverImpl().solve(pointsNumber, points)
    }

    fun readPointsNumber() {
        do {
            print("Введите количество точек [1..14]: ")
            var number = infoReader.readMode()
            if (number in 1..14)
                pointsNumber = number
        } while (pointsNumber == 0)
    }

    fun readPoints() {
        var readMode = 0
        do {
            print("Считать точки из файла или из консоли(1-файл, 2-консоль): ")
            var mode = infoReader.readMode()
            if (mode == 1 || mode == 2)
                readMode = mode
        } while (readMode == 0)

        if (readMode == 1) {
            readPointsFromFile()
        } else {
            readPointsFromConsole()
        }
    }

    private fun readPointsFromConsole() {
        var it = 0
        var result : MutableList<Point> = ArrayList()
        while (it < pointsNumber) {
            print("Введите точку: ")
            try {
                var point : Point = infoReader.readPoint()
                result.add(point)
                it++
            } catch (ex: InputMismatchException) {
                scanner.nextLine()
            } catch (ex: NoSuchElementException) {
                print("Ввод был прерван")
                exitProcess(-1)
            }
        }
        points = result
    }

    private fun readPointsFromFile() {

    }
}
