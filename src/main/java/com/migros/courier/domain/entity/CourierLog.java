package com.migros.courier.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class CourierLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long courierId;
    private LocalDateTime entryTime;
    private String storeName;
    public static class Builder {
        private Long courierId;
        private LocalDateTime entryTime;
        private String storeName;

        public Builder courierId(Long courierId) {
            this.courierId = courierId;
            return this;
        }

        public Builder entryTime(LocalDateTime entryTime) {
            this.entryTime = entryTime;
            return this;
        }

        public Builder storeName(String storeName) {
            this.storeName = storeName;
            return this;
        }

        public CourierLog build() {
            CourierLog courierLog = new CourierLog();
            courierLog.setCourierId(courierId);
            courierLog.setEntryTime(entryTime);
            courierLog.setStoreName(storeName);
            return courierLog;
        }
    }
}
