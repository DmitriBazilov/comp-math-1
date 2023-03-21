package dmitri.comp.math.entity

import dmitri.comp.math.interfaces.EquationSystem

class NewtonSystemAnswer(var status : Int) {

    var x : Double? = null
    var y : Double? = null
    var i : Int = -1
    var system : EquationSystem? = null
    var interval : SearchInterval? = null
}