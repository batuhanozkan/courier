package com.migros.courier.domain.calculator;

public interface DistanceCalculator {
    double calculate(double lat1, double lon1, double lat2, double lon2);
}
