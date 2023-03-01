package dmitri.comp.math

import dmitri.comp.math.reader.InfoUserReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class App {

    var mode : Int? = 0
    var scanner : Scanner = Scanner(System.`in`)
    var filename : String? = null
    val greeting: String
        get() {
            return "Привет. Откуда будем считывать матрицу? (1: файл, 2: консоль) : "
        }

    val inputFilename: String
        get() {
            return "Введите путь до файла : "
        }

    fun start() {
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
        }


//        var matrix = MatrixUserReader(null).read()
//        InfoPrinter().printMatrix(matrix)
    }
    
}

fun main() {
    App().start()
}
