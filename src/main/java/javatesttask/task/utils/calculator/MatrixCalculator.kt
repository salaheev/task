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

        val latitudeA = from.latitude
        val longitudeA = from.longitude

        val latitudeB = to.longitude
        val longitudeB = to.longitude

        val latitudeBtoA = latitudeB - latitudeA
        val longitudeBtoA = longitudeB - longitudeA

        val length = abs(sqrt(latitudeBtoA.pow(2) + longitudeBtoA.pow(2)))

        return "Matrix type - distance: $length meters."
    }

    override fun getType(): CalculationType {
        return calculationType;
    }
}