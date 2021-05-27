package javatesttask.task.utils.calculator

import javatesttask.task.entity.CityEntity
import org.springframework.stereotype.Service
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

@Service
class MatrixCalculator : Calculator<CalculationType> {

    private val calculationType: CalculationType = CalculationType.MATRIX

    override fun calculate(from: CityEntity, to: CityEntity): String {

        val Ax = from.latitude
        val Ay = from.longitude

        val Bx = to.longitude
        val By = to.longitude

        val latitudeBtoA = abs(Bx - Ax)
        val longitudeBtoA = abs(By - Ay)

        val length = sqrt(latitudeBtoA.pow(2) + longitudeBtoA.pow(2)) * 100
        val formattedDistanceAnswer = Calculator.toDimensional(length)

        return "Matrix type - distance: ${formattedDistanceAnswer[0]} km ${formattedDistanceAnswer[1]} meters."
    }

    override fun getType(): CalculationType {
        return calculationType;
    }
}