package dmitri.comp.math.graphics

import dmitri.comp.math.entity.NewtonSystemAnswer
import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.Point
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.EquationSystem
import dmitri.comp.math.util.InfoPrinter
import org.math.plot.Plot2DPanel
import java.awt.Color
import javax.swing.JFrame


class GraphicDrawer {
    fun showNotLinearEquation(answer: NotLinearEquationAnswer, interval: SearchInterval) {

        // create your PlotPanel (you can use it as a JPanel)
        val plot = Plot2DPanel()
        addEquationOnGraph(
            plot,
            { x: Double?, y: Double? ->
                answer.equation.f(
                    x!!
                )
            }, interval
        )

        val frame = JFrame("Ответ")
        frame.setSize(800, 600)
        frame.contentPane = plot
        frame.isVisible = true
    }

    fun showApproximateLines(graphPoints: List<Point>, approximatePoints: List<Point>) {
        val plot = Plot2DPanel()
        addLineByDots(plot, "graph", Color.GREEN, graphPoints)
        addLineByDots(plot, "approximation", Color.RED, approximatePoints)

        val frame = JFrame("Ответ")
        frame.setSize(800, 600)
        frame.contentPane = plot
        frame.isVisible = true
    }

    fun addLineByDots(plot: Plot2DPanel, name: String, color: Color, line: List<Point>) {
        InfoPrinter().printArray(line.toTypedArray())
        plot.addLinePlot(
            name,
            color,
            line
                .asSequence()
                .map { it.x }
                .toList()
                .toDoubleArray(),
            line
                .asSequence()
                .map { it.y }
                .toList()
                .toDoubleArray()
        )
    }

    fun showSystem(answer: NewtonSystemAnswer) {

        // create your PlotPanel (you can use it as a JPanel)
        val plot = Plot2DPanel()


//        Point2D point2D = new Point2D.Double(answer.getX(), 0);
        addSystemOnGraph(plot, answer.system!!, answer.interval!!)

        // put the PlotPanel in a JFrame, as a JPanel
        val frame = JFrame("Ответ")
        frame.setSize(800, 600)
        frame.contentPane = plot
        frame.isVisible = true
    }

    private fun addEquationOnGraph(plot: Plot2DPanel, f: (Double, Double) -> Double, interval: SearchInterval) {
        val x = DoubleArray(80)
        val y = DoubleArray(60)
        var start: Double = interval.left - 0.5
        for (i in 0..59) {
            x[i] = start
            y[i] = f.invoke(start, 0.0)
            start += 0.05
        }
        plot.addLinePlot("", Color.BLUE, x, y)
        val x0 = doubleArrayOf(interval.left - 2, 0.0)
        val y0 = doubleArrayOf(interval.right + 2, 0.0)
        plot.addLinePlot("y=0", Color.DARK_GRAY, x0, y0)
    }



    private fun addSystemOnGraph(plot: Plot2DPanel, system: EquationSystem, interval: SearchInterval) {
        addEquationOnGraph(plot, system::firstEquation.get(), interval)
        addEquationOnGraph(plot, system::secondEquation.get(), interval)
    }
}