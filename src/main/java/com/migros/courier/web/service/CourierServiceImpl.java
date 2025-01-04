package com.migros.courier.web.service;

import com.migros.courier.domain.calculator.CalculatorFactory;
import com.migros.courier.domain.calculator.DistanceCalculator;
import com.migros.courier.domain.entity.CourierHistory;
import com.migros.courier.domain.entity.CourierLog;
import com.migros.courier.domain.entity.Store;
import com.migros.courier.domain.repository.CourierLogRepository;
import com.migros.courier.domain.repository.CourierHistoryRepository;
import com.migros.courier.domain.repository.StoreRepository;
import com.migros.courier.web.dto.CourierLogDto;
import com.migros.courier.web.response.CourierLogResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.migros.courier.web.dto.CourierDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierLogRepository courierLogRepository;
    private final CourierHistoryRepository courierHistoryRepository;
    private final StoreRepository storeRepository;
    private final CalculatorFactory calculatorFactory;

    @Override
    @Transactional
    public void logCourier(CourierDto courierDto) {
        CourierHistory courierHistory = new CourierHistory();
        BeanUtils.copyProperties(courierDto, courierHistory);
        courierHistoryRepository.save(courierHistory);
        List<Store> stores = storeRepository.findAll();
        DistanceCalculator distanceCalculator = calculatorFactory.getCalculator("haversine");
        for (Store store : stores) {
            if (distanceCalculator.calculate(courierDto.getLat(), courierDto.getLng(), store.getLat(), store.getLng()) <= 100) {
                LocalDateTime oneMinuteAgo = courierDto.getTime().minusMinutes(1);
                boolean alreadyLogged = courierLogRepository.existsByCourierIdAndStoreNameAndEntryTimeAfter(
                        courierDto.getCourierId(), store.getName(), oneMinuteAgo);

                if (!alreadyLogged)
                    courierLogRepository.save(new CourierLog.Builder()
                            .courierId(courierDto.getCourierId())
                            .storeName(store.getName())
                            .entryTime(courierDto.getTime())
                            .build());
            }
        }
    }

    @Override
    public Double getTotalTravelDistance(Long courierId) {
        List<CourierHistory> courierHistory = courierHistoryRepository.findByCourierIdOrderByTimeAsc(courierId);
        if (courierHistory.size() < 2)
            return 0.0;
        DistanceCalculator distanceCalculator = calculatorFactory.getCalculator("haversine");
        return IntStream.range(1, courierHistory.size())
                .mapToDouble(i -> distanceCalculator.calculate(
                        courierHistory.get(i - 1).getLat(), courierHistory.get(i - 1).getLng(),
                        courierHistory.get(i).getLat(), courierHistory.get(i).getLng()))
                .sum();
    }

    @Override
    public CourierLogResponse getCourierLog(Long courierId) {
        List<CourierLog> courierLogList = courierLogRepository.findCourierLogByCourierId(courierId);
        CourierLogResponse courierLogResponse = new CourierLogResponse();
        courierLogResponse.setCourierLogs(courierLogList.stream()
                .map(log -> {
                    CourierLogDto courierLogDto = new CourierLogDto();
                    BeanUtils.copyProperties(log, courierLogDto);
                    return courierLogDto;
                })
                .toList()
        );
        return courierLogResponse;
    }

}
