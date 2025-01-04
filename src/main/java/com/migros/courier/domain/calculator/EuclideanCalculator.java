package com.migros.courier.domain.calculator;

import org.springframework.stereotype.Component;

@Component
public class EuclideanCalculator implements DistanceCalculator {
    @Override
    public double calculate(double lat1, double lon1, double lat2, double lon2) {
        double deltaX = lat2 - lat1;
        double deltaY = lon2 - lon1;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
