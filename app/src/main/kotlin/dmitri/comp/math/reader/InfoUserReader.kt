package dmitri.comp.math.reader

import dmitri.comp.math.entity.Point
import dmitri.comp.math.entity.SearchInterval
import java.io.File
import java.util.InputMismatchException
import java.util.Scanner
import kotlin.NoSuchElementException
import kotlin.Throws
import kotlin.system.exitProcess

class InfoUserReader(private var scanner: Scanner) {

    var mode = 1

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    fun readMode(): Int {
        return scanner.nextInt()
    }

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    fun readSize(): Int {
        return scanner.nextInt()
    }

    @Throws(NoSuchElementException::class)
    fun readFilename() : String {
        var result : String = ""
        while (result == "") {
            result = scanner.nextLine()
        }
        return result
    }

    @Throws(NoSuchElementException::class)
    fun readFile() : File {
        var result : File? = null

        do {
            var filename = readFilename()
            var file = File(filename)
            if (file.isFile && !file.isDirectory) {
                result = file
            }
        } while (result == null)

        return result
    }
//    InputMismatchException – if the next token does not match the Float regular expression, or is out of range
//    NoSuchElementException – if the input is exhausted
//    IllegalStateException – if this scanner is closed
    @Throws(InputMismatchException::class, NoSuchElementException::class)
    fun readInterval() : SearchInterval {
        var left : Double = scanner.nextDouble()
        var right : Double = scanner.nextDouble()
        return SearchInterval(left, right)
    }

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    fun readEpsilon(): Double {
        return scanner.nextDouble()
    }

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    fun readPoint() : Point {
        if (mode == 1) {
            print("Введите точку(x, y): ")
        }
        var x = scanner.nextDouble()
        var y = scanner.nextDouble()
        return Point(x, y)
    }

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    fun readInterpolationPoint(): Double {
        if (mode == 1) {
            print("Введите точку интерполяции(одно число x): ")
        }
        return scanner.nextDouble()
    }

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    fun readPoints(n: Int) : List<Point> {
        var result = mutableListOf<Point>()
        var i = 0
        while (i < n) {
            try {
                val newPoint = readPoint()
                result.add(newPoint)
                i++
            } catch (ex : InputMismatchException) {
                println()
            } catch (ex : java.util.NoSuchElementException) {
                exitProcess(-1)
            }
        }
        return result
    }

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    fun readPointsNumber() : Int {
        if (mode == 1) {
            print("Введите число точек: ")
        }
        return scanner.nextInt()
    }

    @Throws(NoSuchElementException::class)
    fun readLine() : String {
        return scanner.nextLine()
    }
}