package dmitri.comp.math.entity

data class Matrix(val size : Int, val matrix : Array<Array<Double>>) {

    public fun plusToRow(src : Array<Double>, to : Int) {
        for (i in 0..size) {
            matrix[to][i] += src[i]
        }
    }

    public fun getMultipliedRow(k : Double, row : Int): Array<Double> {
        var result : Array<Double> = arrayOf<Double>()
        for (i in 0..size) {
            result += (matrix[row][i] * k)
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (size != other.size) return false
        if (!matrix.contentDeepEquals(other.matrix)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = size
        result = 31 * result + matrix.contentDeepHashCode()
        return result
    }
}
