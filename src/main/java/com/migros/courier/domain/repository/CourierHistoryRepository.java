package com.migros.courier.domain.repository;


import com.migros.courier.domain.entity.CourierHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourierHistoryRepository extends JpaRepository<CourierHistory, Long> {

    List<CourierHistory> findByCourierIdOrderByTimeAsc(Long courierId);
}
