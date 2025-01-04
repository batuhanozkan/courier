package com.migros.courier.domain.repository;


import com.migros.courier.domain.entity.CourierLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourierLogRepository extends JpaRepository<CourierLog, Long> {
    boolean existsByCourierIdAndStoreNameAndEntryTimeAfter(Long courierId, String Name, LocalDateTime oneMinuteAgo);
    List<CourierLog> findCourierLogByCourierId(Long courierId);
}
