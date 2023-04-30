package dmitri.comp.math.entity

data class ApproximationAnswer(val status: Int,
                               val name: String,
                               val points: List<Point>,
                               val coef : List<Double>,
                               val fi: (x: Double, coef: List<Double>) -> Double,
                               val mnk: Double)
