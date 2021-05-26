package javatesttask.task.utils.calcutation;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.utils.calculator.CalculationType;


public class SimpleDistanceCalculator implements DistanceCalculator {

    @Override
    public String calculateBetween(CityEntity from, CityEntity to, CalculationType type) {

        return null;
    }

//    @Override
//    public double calculateBetween(CityEntity from, CityEntity to, CalculationType type) {
//
//        final int EARTH_RADIUS = 6371;
//
//        Point A = new Point(55.75, 37.66);
//        Point B = new Point(-59.88, 30.25);
//
//
//        double cL1 = cos(A.getxRad());
//        double cL2 = cos(B.getxRad());
//        double sL1 = sin(A.getxRad());
//        double sL2 = sin(B.getxRad());
//        double delta = B.getyRad() - A.getyRad();
//        double cDelta = cos(delta);
//        double sDelta = sin(delta);
//
//        double azX = cL1 * sL2 - sL1 * cL2 * cDelta;
//        double y = sqrt(pow(cL2 * sDelta, 2) + pow(azX, 2));
//        double x = sL1 * sL2 + cL1 * cL2 * cDelta;
//
//        double ad = atan2(y, x);
//
//        double distance = ad * EARTH_RADIUS;
//
//        double azY = sDelta * cL2;
//        double azZ = toDegrees(atan(-azY / azX));
//
//        if (azX < 0) {
//            azZ = azZ + 180;
//        }
//
//        double z2 = (azZ + 180) % 360 - 180;
//        z2 = -toRadians(z2);
//
//        double angRad2 = z2 - ((2 * PI) * floor((z2 / (2 * PI))));
//        double angDeg = (angRad2*180) / PI;
//
//        DecimalFormat decimalFormat = new DecimalFormat();
//        decimalFormat.setRoundingMode(RoundingMode.CEILING);
//        String answer = decimalFormat.format(distance);
//
//        String[] formattedAnswer = answer.split(",");
//
//        System.out.println("Distance: " + formattedAnswer[0] + " km " + formattedAnswer[1] + " meters.");
//        System.out.println("Initial bearing: " + angDeg + " degrees.");
//
//    }
//
//    static class Point {
//
//        private final double x, y;
//        private final double xRad, yRad;
//
//        public Point(double x, double y) {
//            this.x = x;
//            this.y = y;
//            this.xRad = x * PI / 180;
//            this.yRad = y * PI / 180;
//        }
//
//        public double getX() {
//            return x;
//        }
//
//        public double getY() {
//            return y;
//        }
//
//        public double getxRad() {
//            return xRad;
//        }
//
//        public double getyRad() {
//            return yRad;
//        }
//    }
//        return 0;
//    }
}


