package dmitri.comp.math.entity

class SLAEAnswer(val status : GaussResult) {

    enum class GaussResult(val message : String) {
        OK("Метод отработал успешно"),
        NOT_ZERO_ELEMENT("В процессе оказалось что нет ненулевых элементов. Определитель равен нулю"),
        ZERO_DIAGONAL_ELEMENT("Ноль в главной дигонале, Определитель равен нулю")
    }

    var swaps : Int = 0
    var roots : DoubleArray? = null
    var det : Double? = null
    var triangleMatrix : Matrix? = null
    var discrepancies : Array<String>? = null
}