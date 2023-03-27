package dmitri.comp.math.util

import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.Equation
import java.util.*


class IntervalFinder {

    public fun findIntervals(eq: Equation, interval: SearchInterval): List<SearchInterval> {

        var answer = ArrayList<SearchInterval>()
        var i : Double = interval.left + 0.5
        while (i < interval.right) {
            var left = eq.f(i - 0.5)
            var right = eq.f(i)
            if (left * right <= 0.0) {
                answer.add(SearchInterval(i - 0.5, i))
            }
            i += 0.5
        }
        return answer
    }
}