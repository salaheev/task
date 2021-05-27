package javatesttask.task.utils.calculator

import javatesttask.task.entity.CityEntity
import org.springframework.stereotype.Service
import kotlin.math.*

@Service
class CrowFlightCalculator : Calculator<CalculationType> {

    private val calculationType: CalculationType = CalculationType.CROW_FLIGHT

    private val planetRadius: Int = 6371

    override fun calculate(from: CityEntity, to: CityEntity): String {

        val fromRadianLatitude = convertToRadians(from.latitude)
        val toRadianLatitude = convertToRadians(to.latitude)

        val fromRadianLongitude = convertToRadians(from.longitude)
        val toRadianLongitude = convertToRadians(to.longitude)

        val cL1 = cos(fromRadianLatitude)
        val cL2 = cos(toRadianLatitude)
        val sL1 = sin(fromRadianLatitude)
        val sL2 = sin(toRadianLatitude)

        val delta = toRadianLongitude - fromRadianLongitude
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
        val formattedDistanceAnswer = Calculator.toDimensional(distance)

        return "CrowFlight type - distance: ${formattedDistanceAnswer[0]} km ${formattedDistanceAnswer[1]} meters."
    }

    override fun getType(): CalculationType {
        return calculationType;
    }

    private fun convertToRadians(coordinate: Double): Double {

        return coordinate * Math.PI / 180
    }

}