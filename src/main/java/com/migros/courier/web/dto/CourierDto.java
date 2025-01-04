package com.migros.courier.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CourierDto {
    @NotNull(message = "courierId may not be null")
    private Long courierId;
    @NotNull(message = "time may not be null")
    private LocalDateTime time;
    @NotNull(message = "lat may not be null")
    private Double lat;
    @NotNull(message = "lng may not be null")
    private Double lng;
}
