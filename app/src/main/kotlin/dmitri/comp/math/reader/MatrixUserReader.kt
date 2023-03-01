package dmitri.comp.math.reader

import dmitri.comp.math.entity.Matrix
import java.util.*
import kotlin.jvm.Throws

class MatrixUserReader(private var scanner : Scanner) : UserReader<Matrix> {

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    override fun read(): Matrix {
        val size = readSize()
        return Matrix(size, readMatrix(size))
    }

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    private fun readSize() : Int {
        var result =  scanner.nextInt()
        return result
    }

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    private fun readMatrix(size : Int) : Array<Array<Double>> {
        var result : Array<Array<Double>> = arrayOf()
        for (i in 0 until size) {
            var row : Array<Double> = arrayOf()
            for (j in 0..size) {
                var x = scanner.nextDouble()
                row += x
            }    
            result += row
        }
        return result
        //todo safe
    }
}
