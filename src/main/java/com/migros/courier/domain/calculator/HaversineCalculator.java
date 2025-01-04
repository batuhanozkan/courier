package com.migros.courier.domain.calculator;

import com.migros.courier.util.CalculatorConstant;
import org.springframework.stereotype.Component;

@Component
public class HaversineCalculator implements DistanceCalculator {
    @Override
    public double calculate(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return CalculatorConstant.EARTH_RADIUS * c;
    }
}
