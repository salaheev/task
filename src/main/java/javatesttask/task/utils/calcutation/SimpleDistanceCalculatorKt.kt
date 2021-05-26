package javatesttask.task.utils.calcutation

import javatesttask.task.entity.CityEntity
import javatesttask.task.utils.calculator.CalculationType
import org.springframework.stereotype.Component
import java.lang.Math.PI
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.*

@Component
class SimpleDistanceCalculatorKt : DistanceCalculator {

    private val planetRadius: Int = 6371

    override fun calculateBetween(from: CityEntity, to: CityEntity, type: CalculationType?): String {

        when (type) {
            CalculationType.MATRIX -> {

                return matrixCalculate(from, to)
            }
            CalculationType.CROW_FLIGHT -> {

                return crowFlightCalculate(from, to)
            }
            CalculationType.ALL -> {

                return "All results:\n " +
                        "Matrix type is: ${matrixCalculate(from, to)}. \n" +
                        "Crow flight type is: ${crowFlightCalculate(from, to)}"
            }
        }

        throw IllegalArgumentException(
                "Calculation type is not assigned. " +
                        "Please select one of the implemets: " +
                        "${CalculationType.values()}")
    }

    private fun matrixCalculate(from: CityEntity, to: CityEntity): String {
        val latitudeA = from.latitude
        val longitudeA = from.longitude

        val latitudeB = to.longitude
        val longitudeB = to.longitude

        val latitudeBtoA = latitudeB - latitudeA
        val longitudeBtoA = longitudeB - longitudeA

        val length = abs(sqrt(latitudeBtoA.pow(2) + longitudeBtoA.pow(2)))

        return "Distance: $length meters."
    }

    private fun crowFlightCalculate(from: CityEntity, to: CityEntity): String {
        val fromRadianLatitude = convertToRadians(from.latitude)
        val toRadianLatitude = convertToRadians(to.latitude)

        val cL1 = cos(fromRadianLatitude)
        val cL2 = cos(toRadianLatitude)
        val sL1 = sin(fromRadianLatitude)
        val sL2 = sin(toRadianLatitude)
        val delta = toRadianLatitude - fromRadianLatitude
        val cDelta = cos(delta)
        val sDelta = sin(delta)

        val azX = cL1 * sL2 - sL1 * cL2 * cDelta
        val y = sqrt((cL2 * sDelta).pow(2.0) + azX.pow(2.0))
        val x = sL1 * sL2 + cL1 * cL2 * cDelta

        val ad = atan2(y, x)

        val distance = ad * planetRadius

        val azY = sDelta * cL2
        var azZ = Math.toDegrees(atan(-azY / azX))

        if (azX < 0) {
            azZ += 180
        }

        var z2 = (azZ + 180) % 360 - 180
        z2 = -Math.toRadians(z2)

        val angRad2 = z2 - 2 * PI * floor(z2 / (2 * PI))
        val angDeg = angRad2 * 180 / PI

        val decimalFormat = DecimalFormat()
        decimalFormat.roundingMode = RoundingMode.CEILING
        val answer = decimalFormat.format(distance)

        val formattedAnswer = answer.split(",".toRegex()).toTypedArray()

//        println("Distance: " + formattedAnswer[0] + " km " + formattedAnswer[1] + " meters.")
//        println("Initial bearing: $angDeg degrees.")
        return "Distance: ${formattedAnswer[0]} km ${formattedAnswer[1]} meters."
    }

    private fun convertToRadians(coordinate: Double): Double {

        return coordinate * PI / 180
    }
}
