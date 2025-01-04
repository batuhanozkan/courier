package com.migros.courier.web.controller;

import com.migros.courier.web.dto.CourierLogDto;
import com.migros.courier.web.response.CourierLogResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.migros.courier.web.dto.CourierDto;
import com.migros.courier.web.service.CourierService;

@RestController
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @PostMapping("/courier/logCourier")
    public void logCourier(@Valid @RequestBody CourierDto courierDto) {
        courierService.logCourier(courierDto);
    }

    @GetMapping("/courier/getTotalTravelDistance/{courierId}")
    public ResponseEntity<Double> getTotalTravelDistance(@PathVariable Long courierId) {
        return ResponseEntity.ok(courierService.getTotalTravelDistance(courierId));
    }

    @GetMapping("/courier/getCourierLog/{courierId}")
    public ResponseEntity<CourierLogResponse> getCourierLog(@PathVariable Long courierId) {
        return ResponseEntity.ok(courierService.getCourierLog(courierId));
    }
}

