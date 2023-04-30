package dmitri.comp.math.solvers

import dmitri.comp.math.entity.Matrix
import dmitri.comp.math.entity.NewtonSystemAnswer
import dmitri.comp.math.entity.SLAEAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.EquationSystem
import dmitri.comp.math.interfaces.NotLinearSystemSolver
import kotlin.math.abs
import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D
import java.awt.geom.Line2D

class NotLinearSystemNewtonSolver : NotLinearSystemSolver<SearchInterval, NewtonSystemAnswer> {

    override fun solve(interval: SearchInterval, system: EquationSystem): NewtonSystemAnswer {
        
        val answer : NewtonSystemAnswer = NewtonSystemAnswer(0)

        var x0 = interval.left
        var y0 = interval.right

        val eps = 0.1f
        val je = system.jacobianEquations

        var matrix : Array<Array<Double>> = Array(2) { Array(3) { 0.0 } }

        var x1 = 0.0
        var y1 = 0.0
        var it = 0

        do {
            it++
            for (i in je.indices) {
                for (j in 0 until je[i].size) {
                    matrix[i][j] = je[i][j].invoke(x0, y0)
                }
            }

            val gaussAnswer = GaussSLAESolver().solve(Matrix(2, matrix))
            if (gaussAnswer!!.status != SLAEAnswer.GaussResult.OK) return NewtonSystemAnswer(-1)
            val matrixAnswer = gaussAnswer.roots!!.toTypedArray()
            x1 = matrixAnswer[0] + x0
            y1 = matrixAnswer[1] + y0

            if (abs(x1 - x0) <= eps && abs(y1 - y0) <= eps) {
                break
            }

            x0 = x1
            y0 = y1
        } while (it < 10000)
        if (it == 10000) {
            return NewtonSystemAnswer(-1)
        }

        answer.x = x1
        answer.y = y1
        answer.i = it
        answer.system = system
        answer.interval = interval

        return answer
    }
}