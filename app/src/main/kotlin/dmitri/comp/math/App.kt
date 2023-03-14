package dmitri.comp.math

import dmitri.comp.math.SLAE.GaussSLAESolver
import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.SLAEAnswer
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.reader.MatrixUserReader
import dmitri.comp.math.util.InfoPrinter
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.NoSuchElementException

class App {

    var mode : Int? = 0
    var scanner : Scanner = Scanner(System.`in`)
    var filename : String? = null
    var  size : Int = -1
    val greeting: String
        get() {
            return "Привет. Откуда будем считывать матрицу? (1: файл, 2: консоль) : "
        }

    private val inputFilename: String
        get() {
            return "Введите путь до файла : "
        }

    private val inputSize: String
        get() {
            return "Введите размер матрицы (1..20) : "
        }

    private val inputMatrix: String
        get() {
            return "Введите матрицу : "
        }

    fun start() {
        scanner.useLocale(Locale.US)
        var infoReader = InfoUserReader(scanner)

        print(greeting)
        do {
            try {
                var userMode : Int = infoReader.readMode()
                if (userMode == 1 || userMode == 2)
                    mode = userMode
                else
                    print("Введите 1(файл) или 2(консоль) : ")
            } catch (badInputException : InputMismatchException) {
                print("Введите корректный режим работы (1: файл, 2: консоль) : ")
                scanner.nextLine()
            } catch (eofException : NoSuchElementException) {
                println("Ввод был прерван. Завершение программы")
                scanner.close()
                return
            }
        } while (mode !in 1..2)
        if (mode == 1) {
            print(inputFilename)
            do {
                try {
                    var userFilename : String = infoReader.readFilename()
                    var path = Paths.get(userFilename)
                    if (Files.exists(path) && !Files.isDirectory(path))
                        filename = userFilename
                    else
                        print("Файл не найден, попробуйте еще раз : ")
                } catch (eofException : NoSuchElementException) {
                    println("Ввод был прерван. Завершение программы")
                    scanner.close()
                    return
                }
            } while (filename == null)
            print(inputSize)
            do {
                try {
                    var userSize: Int = infoReader.readSize()
                    if (userSize !in 1..20) {
                        println("Введите размер от 1 до 20")
                    } else {
                        size = userSize
                    }

                } catch (badInputException : InputMismatchException) {
                    println("Пожалуйста введите число от 1 до 20")
                    scanner.nextLine()
                } catch (eofException : NoSuchElementException) {
                    println("Ввод был прерван. Завершение программы")
                    scanner.close()
                    return
                }
            } while (size == -1)

            val file = File(filename!!)
            scanner = Scanner(file)
            scanner.useLocale(Locale.US)

            val fileMatrixReader : MatrixUserReader = MatrixUserReader(scanner)
            val matrix : Array<Array<Double>> = fileMatrixReader.readMatrix(size)
            val infoPrinter : InfoPrinter = InfoPrinter()
            println("Исходная матрица: ")
            infoPrinter.printMatrix(Matrix(size, matrix))
            infoPrinter.printDelimiter()

            val answer : SLAEAnswer? = GaussSLAESolver().solve(Matrix(size, matrix))

            infoPrinter.printSLAEAnswer(answer!!)
        } else if (mode == 2) {
            print(inputSize)
            do {
                try {
                    var userSize: Int = infoReader.readSize()
                    if (userSize !in 1..20) {
                        println("Введите размер от 1 до 20")
                    } else {
                        size = userSize
                    }

                } catch (badInputException : InputMismatchException) {
                    println("Пожалуйста введите число от 1 до 20")
                    scanner.nextLine()
                } catch (eofException : NoSuchElementException) {
                    println("Ввод был прерван. Завершение программы")
                    scanner.close()
                    return
                }
            } while (size == -1)

            val consoleMatrixReader : MatrixUserReader = MatrixUserReader(scanner)
            println(inputMatrix)
            val matrix : Array<Array<Double>> = consoleMatrixReader.readMatrix(size)
            val infoPrinter : InfoPrinter = InfoPrinter()

            val answer : SLAEAnswer? = GaussSLAESolver().solve(Matrix(size, matrix))

            infoPrinter.printSLAEAnswer(answer!!)

        }

//        var matrix = MatrixUserReader(null).read()
//        InfoPrinter().printMatrix(matrix)
    }
    
}

fun main() {
    App().start()
}
