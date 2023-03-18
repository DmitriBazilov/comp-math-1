package dmitri.comp.math.processors

import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.MethodProcessor
import dmitri.comp.math.interfaces.NotLinearEquationSolver
import dmitri.comp.math.solvers.NotLinearEquationHalfDivisionSolver
import dmitri.comp.math.solvers.NotLinearEquationNewtonSolver
import dmitri.comp.math.solvers.NotLinearEquationSimpleIterationSolver

class NotLinearEquationProcessor : MethodProcessor {

    val methods : Map<Int, NotLinearEquationSolver<SearchInterval, NotLinearEquationAnswer>>
        get() {
            var map : Map<Int, NotLinearEquationSolver<SearchInterval, NotLinearEquationAnswer>> = HashMap()
            map.plus(Pair(1, NotLinearEquationHalfDivisionSolver()))
            map.plus(Pair(2, NotLinearEquationNewtonSolver()))
            map.plus(Pair(3, NotLinearEquationSimpleIterationSolver()))
            return map
        }

    override fun processMethod() {
        TODO("Not yet implemented")
    }
}