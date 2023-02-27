package dmitri.comp.math.reader

import java.util.Scanner
import dmitri.comp.math.entity.Matrix

class MatrixUserReader(val scanner : Scanner) : UserReader<Matrix> {
    
    override public fun read() : Matrix {
        println("type size")
        var size : Int = readSize()
        println("type matrix")
        var matrix : Matrix = readMatrix(size)
        return matrix
    }

    private fun readSize() : Int {
        return scanner.nextInt()
        //todo safe read
    }

    private fun readMatrix(size : Int) : Matrix {
        var result : Array<Array<Int>> = arrayOf<Array<Int>>()
        var i : Int = 0
        for (i; i < size; i++) {
            var row : Array<Int> = arrayOf<Int>()
            var j : Int = 0
            for (j; j < size + 1; j++) {
                var x : Int = scanner.nextInt()
                row += x
            }    
            result += row
        }
        //todo safe
        return Matrix(1, arrayOf(arrayOf(1, 2, 3)))
    }
}
