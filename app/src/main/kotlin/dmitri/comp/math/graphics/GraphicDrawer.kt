package dmitri.comp.math.graphics

import dmitri.comp.math.entity.NewtonSystemAnswer
import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.interfaces.EquationSystem
import org.math.plot.Plot2DPanel
import java.awt.Color
import javax.swing.JFrame


class GraphicDrawer {
    fun showEquation(answer: NotLinearEquationAnswer, interval: SearchInterval) {

        // create your PlotPanel (you can use it as a JPanel)
        val plot = Plot2DPanel()
        addEquationOnGraph(plot,
            { x: Double?, y: Double? ->
                answer.equation.f(
                    x!!
                )
            }, interval
        )

//        Point2D point2D = new Point2D.Double(answer.getX(), 0);

        // put the PlotPanel in a JFrame, as a JPanel
        val frame = JFrame("Ответ")
        frame.setSize(800, 600)
        frame.contentPane = plot
        frame.isVisible = true
    }

    fun showSystem(answer: NewtonSystemAnswer) {

        // create your PlotPanel (you can use it as a JPanel)
        val plot = Plot2DPanel()


//        Point2D point2D = new Point2D.Double(answer.getX(), 0);
//        addSystemOnGraph(plot, answer.system!!, SearchInterval(-4.0, 4.0))
        var x = DoubleArray(1000)
        var y = DoubleArray(1000)
        var start: Double = -2.5
        for (i in 0..999) {
            x[i] = start
            y[i] = answer.system!!.firstEquation.invoke(start, 0.0)
            start += 0.005
        }
        plot.addLinePlot("", Color.BLUE, x, y)
        x = DoubleArray(1000)
        y = DoubleArray(1000)
        start = -2.5
        for (i in 0..999) {
            x[i] = start
            y[i] = answer.system!!.secondEquation.invoke(start, 0.0)
            start += 0.005
        }
        plot.addLinePlot("", Color.BLUE, x, y)

        val x0 = doubleArrayOf(-2.5, 0.0)
        val y0 = doubleArrayOf(2.5, 0.0)
        plot.addLinePlot("y=0", Color.DARK_GRAY, x0, y0)
        // put the PlotPanel in a JFrame, as a JPanel
        val frame = JFrame("Ответ")
        frame.setSize(800, 600)
        frame.contentPane = plot
        frame.isVisible = true
    }

    private fun addEquationOnGraph(plot: Plot2DPanel, f: (Double, Double) -> Double, interval: SearchInterval) {
        val x = DoubleArray(1000)
        val y = DoubleArray(1000)
        var start: Double = -5.0
        for (i in 0..999) {
            x[i] = start
            y[i] = f.invoke(start, 0.0)
            start += 0.01
        }
        plot.addLinePlot("", Color.BLUE, x, y)
        val x0 = doubleArrayOf(-5.0, 0.0)
        val y0 = doubleArrayOf(5.0, 0.0)
        plot.addLinePlot("y=0", Color.DARK_GRAY, x0, y0)
    }

    private fun addSystemOnGraph(plot: Plot2DPanel, system: EquationSystem, interval: SearchInterval) {
        addEquationOnGraph(plot, system::firstEquation.get(), interval)
        addEquationOnGraph(plot, system::secondEquation.get(), interval)
    }

}