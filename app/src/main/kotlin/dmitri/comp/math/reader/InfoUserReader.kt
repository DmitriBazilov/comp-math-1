package dmitri.comp.math.reader

import dmitri.comp.math.entity.UserInfo
import java.util.InputMismatchException
import java.util.NoSuchElementException
import java.util.Scanner
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
}