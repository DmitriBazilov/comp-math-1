package dmitri.comp.math.processors

import dmitri.comp.math.entity.IntegralAnswer
import dmitri.comp.math.entity.NotLinearEquationAnswer
import dmitri.comp.math.entity.SearchInterval
import dmitri.comp.math.equation.QuadraticEquation
import dmitri.comp.math.equation.ThirdDegreeEquation
import dmitri.comp.math.equation.TranscendentalEquation
import dmitri.comp.math.interfaces.*
import dmitri.comp.math.reader.InfoUserReader
import dmitri.comp.math.solvers.*
import java.util.*

class IntegralProcessor: MethodProcessor {

    final val MIN_EPS: Double = 1.0E-9

    val scanner: Scanner = Scanner(System.`in`)
    val infoReader = InfoUserReader(scanner)

    val methods = listOf<IntegralSolver<IntegralAnswer>>(
            IntegralSimpsonSolver(),
            IntegralTrapezoidSolver(),
            IntegralLeftRectangleSolver(),
            IntegralMediumRectangleSolver(),
            IntegralRightRectangleSolver()
    );

    val methodsName = listOf<String>(
            "Метод Симпсона",
            "Метод трапеции",
            "Метод левых прямоугольников",
            "Метод средних прямоугольников",
            "Метод правых прямоугольников"
    )

    val equations = listOf<Equation>(
            QuadraticEquation(),
            ThirdDegreeEquation(),
            TranscendentalEquation()
    )

    var methodNumber = 0;

    var equationNumber = 0;

    val userReader : InfoUserReader = InfoUserReader(Scanner(System.`in`))

    var interval : SearchInterval? = null;

    var eps : Double = 0.0

    override fun processMethod() {

        for (s : String in methodsName) {
            println("\t-$s")
        }

        do {
            print("Введите номер метода: ")
            var number = userReader.readMode()
            if (number in 1..methods.size) {
                methodNumber = number;
            }

        } while (methodNumber == 0)

        for (e : Equation in equations) {
            println(e)
        }

        do {
            print("Введите номер уравнения: ")
            var number = userReader.readMode()
            if (number in 1..equations.size) {
                equationNumber = number
            }
        } while (equationNumber == 0)

        do {
            print("Введите интервал: ")
            var userInterval = userReader.readInterval()
            if (userInterval.left < userInterval.right) {
                interval = userInterval
            }
        } while (interval == null)

        do {
            print("Введите точность: ")
            var eps = userReader.readEpsilon()
            this.eps = eps
        } while (eps == 0.0)

        var answer = methods.get(methodNumber - 1).solve(interval!!, equations[equationNumber - 1], eps)

        println(answer)
    }

}