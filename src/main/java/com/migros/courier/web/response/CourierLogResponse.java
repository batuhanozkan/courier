package com.migros.courier.web.response;

import com.migros.courier.web.dto.CourierLogDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourierLogResponse {
    List<CourierLogDto> courierLogs;
}
