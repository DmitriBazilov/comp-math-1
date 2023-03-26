package dmitri.comp.math.interfaces

interface Equation {

    val eq : String;

    fun f(x : Double) : Double

    fun fDerivative(x : Double) : Double
}