package com.migros.courier.domain.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalculatorFactory {
    private final EuclideanCalculator euclideanCalculator;
    private final HaversineCalculator haversineCalculator;

    public DistanceCalculator getCalculator(String formulaName) {
        return switch (formulaName) {
            case "euclidean" -> euclideanCalculator;
            case "haversine" -> haversineCalculator;
            default -> null;
        };
    }
}
