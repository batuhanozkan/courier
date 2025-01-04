package com.migros.courier.web.service;

import com.migros.courier.web.dto.CourierLogDto;
import com.migros.courier.web.response.CourierLogResponse;
import org.springframework.web.bind.annotation.RequestBody;
import com.migros.courier.web.dto.CourierDto;

public interface CourierService {

    void logCourier(@RequestBody CourierDto location);

    Double getTotalTravelDistance(Long courierId);

    CourierLogResponse getCourierLog(Long courierId);
}
