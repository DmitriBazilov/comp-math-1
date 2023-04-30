package dmitri.comp.math.approximation_methods

import dmitri.comp.math.entity.Point

interface Method {

    fun solve (points : List<Point>) : List<Double>?
}