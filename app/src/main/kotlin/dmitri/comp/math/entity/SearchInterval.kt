package dmitri.comp.math.entity

data class SearchInterval(val left : Double, val right : Double) {
    override fun toString(): String {
        return "($left, $right)"
    }
}
