package dmitri.comp.math.processors

import dmitri.comp.math.entity.Point
import dmitri.comp.math.graphics.GraphicDrawer
import dmitri.comp.math.interfaces.ApproximationSolver
import dmitri.comp.math.interfaces.MethodProcessor
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.solvers.ApproximationSolverImpl
import dmitri.comp.math.util.InfoPrinter
import java.io.File
import java.lang.ProcessHandle.Info
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.NoSuchElementException
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

class ApproximationProcessor : MethodProcessor {

    var scanner: Scanner = Scanner(System.`in`)
    var infoReader = InfoUserReader(scanner)

    var pointsNumber: Int = 0
    var points: List<Point> = ArrayList<Point>(100)

    override fun processMethod() {
        readPointsNumber()
        readPoints()
        println("Массив точек: ")
        InfoPrinter().printArray(points.toTypedArray())
        println("------------------------------------")
        var answer = ApproximationSolverImpl().solve(pointsNumber, points)
        InfoPrinter().printApproximationAnswer(answer)
        GraphicDrawer().showApproximateLines(
            answer.points,
            answer.points
                .asSequence()
                .map { Point(it.x, answer.fi(it.x, answer.coef)) }
                .toList()
        )
    }

    fun readPointsNumber() {
        do {
            print("Введите количество точек [8..12]: ")
            var number = infoReader.readMode()
            if (number in 8..12)
                pointsNumber = number
        } while (pointsNumber == 0)
    }

    private fun readPoints() {
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
                scanner.close()
                exitProcess(-1)
            }
        }
        points = result
    }

    private fun readPointsFromFile() {
        var filename = readFilename()
        var file = File(filename)
        scanner = Scanner(file)
        infoReader = InfoUserReader(scanner)
        var result : MutableList<Point> = ArrayList()
        var it = 0
        while (it < pointsNumber) {
            try {
                val point : Point = infoReader.readPoint()
                result.add(point)
                it++
            } catch (ex: InputMismatchException) {
                scanner.nextLine()
            } catch (ex: NoSuchElementException) {
                print("Ввод был прерван")
                scanner.close()
                exitProcess(-1)
            }
        }
        points = result
        //todo refactor
    }

    private fun readFilename() : String {
        var result : String = ""
        do {
            print("Введите имя файла: ")
            try {
                var filename = infoReader.readFilename()
                var path = Paths.get(filename)
                if (Files.exists(path) && !Files.isDirectory(path)) {
                    result = filename
                }
            } catch (ex : NoSuchElementException) {
                scanner.close()
                exitProcess(-1)
            }

        } while (result == "")
        return result
    }
}
