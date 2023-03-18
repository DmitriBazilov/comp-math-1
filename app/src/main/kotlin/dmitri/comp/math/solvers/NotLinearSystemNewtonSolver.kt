package dmitri.comp.math.solvers

import dmitri.comp.math.entity.NewtonSystemAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.EquationSystem
import dmitri.comp.math.interfaces.NotLinearSystemSolver

class NotLinearSystemNewtonSolver : NotLinearSystemSolver<SearchInterval, NewtonSystemAnswer> {

    override fun solve(interval: SearchInterval, system: EquationSystem): NewtonSystemAnswer {
        TODO("Not yet implemented")
    }
}