package dmitri.comp.math.processors

import dmitri.comp.math.entity.IntegralAnswer
import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.IntegralSolver
import dmitri.comp.math.interfaces.MethodProcessor
import dmitri.comp.math.interfaces.NotLinearEquationSolver
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.solvers.*
import java.util.*

class IntegralProcessor: MethodProcessor {

    final val MIN_EPS: Double = 1.0E-9

    val scanner: Scanner = Scanner(System.`in`)
    val infoReader = InfoUserReader(scanner)

    val methods: Map<Int, IntegralSolver<IntegralAnswer>> = mapOf(
        Pair(2, IntegralSimpsonSolver()),
        Pair(3, IntegralTrapezoidSolver())
    )

    var methodNumber: Int = 0

    override fun processMethod() {
        readMethod()
        if (methodNumber == 1) {
            IntegralRectangleProcessor().processMethod()
            return
        } else {
            TODO()
//            methods[methodNumber]!!.solve()
        }
    }

    private fun readMethod() {

    }

}