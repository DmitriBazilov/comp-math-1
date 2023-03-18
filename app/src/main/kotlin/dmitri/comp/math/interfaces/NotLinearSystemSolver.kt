package dmitri.comp.math.interfaces

interface NotLinearSystemSolver<E, T> {

    fun solve (interval : E, system : EquationSystem) : T
}