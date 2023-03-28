package dmitri.comp.math.processors

import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.MethodProcessor
import dmitri.comp.math.interfaces.NotLinearEquationSolver
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.solvers.NotLinearEquationHalfDivisionSolver
import dmitri.comp.math.solvers.NotLinearEquationNewtonSolver
import dmitri.comp.math.solvers.NotLinearEquationSimpleIterationSolver
import java.util.*

class IntegralProcessor: MethodProcessor {

    final val MIN_EPS: Double = 1.0E-9

    val scanner: Scanner = Scanner(System.`in`)
    val infoReader = InfoUserReader(scanner)

    val methods: Map<Int, NotLinearEquationSolver<SearchInterval, NotLinearEquationAnswer>> = mapOf(
        Pair(1, NotLinearEquationHalfDivisionSolver()),
        Pair(2, NotLinearEquationNewtonSolver()),
        Pair(3, NotLinearEquationSimpleIterationSolver())
    )

    override fun processMethod() {

    }
}