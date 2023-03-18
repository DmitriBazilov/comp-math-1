package dmitri.comp.math.reader

import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.entity.UserInfo
import dmitri.comp.math.interfaces.UserReader
import java.util.InputMismatchException
import java.util.Scanner
import kotlin.NoSuchElementException
import kotlin.jvm.Throws

class InfoUserReader(private var scanner: Scanner) : UserReader<UserInfo> {
    override fun read(): UserInfo {
        return UserInfo(0, null)
    }

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    fun readMode() : Int {
        val result = scanner.nextInt()
        return result
    }

    @Throws(InputMismatchException::class, NoSuchElementException::class)
    fun readSize() : Int {
        var result =  scanner.nextInt()
        return result
    }

    @Throws(NoSuchElementException::class)
    fun readFilename() : String {
        var result : String = ""
        while (result == "") {
            result = scanner.nextLine()
        }
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
}