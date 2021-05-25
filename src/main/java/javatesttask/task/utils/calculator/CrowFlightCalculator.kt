package javatesttask.task.utils.calculator

import javatesttask.task.entity.CityEntity
import javatesttask.task.utils.calcutation.CalculationType
import org.springframework.stereotype.Service
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.*

@Service
class CrowFlightCalculator : Calculator<CalculationType> {

    private val calculationType: CalculationType = CalculationType.CROW_FLIGHT

    private val planetRadius: Int = 6371

    override fun calculate(from: CityEntity, to: CityEntity): String {

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

        val angRad2 = z2 - 2 * Math.PI * floor(z2 / (2 * Math.PI))
        val angDeg = angRad2 * 180 / Math.PI

        val decimalFormat = DecimalFormat()
        decimalFormat.roundingMode = RoundingMode.CEILING
        val answer = decimalFormat.format(distance)

        val formattedAnswer = answer.split(",".toRegex()).toTypedArray()

//        println("Distance: " + formattedAnswer[0] + " km " + formattedAnswer[1] + " meters.")
//        println("Initial bearing: $angDeg degrees.")
        return "CrowFlight type - distance: ${formattedAnswer[0]} km ${formattedAnswer[1]} meters."

    }

    override fun getType(): CalculationType {
        return calculationType;
    }

    private fun convertToRadians(coordinate: Double): Double {

        return coordinate * Math.PI / 180
    }

}