package dmitri.comp.math.entity

data class Matrix(private val size : Int, private val matrix : Array<Array<Number>>) {
    

    public fun plusToRow(src : Array<Number>, to : Int) {
        var i : Int = 0
        for (i; i < size + 1; i++) {
            matrix[to][i] += src[i]
        }
    }

    public fun getMultipliedRow(k : Number, row : Int) {
        var result : Array<Number> = arrayOf<Number>()
        var i : Int = 0
        for (i; i < size + 1; i++) {
            result += (matrix[row][i] * k)
        }
        //todo
    }
}
