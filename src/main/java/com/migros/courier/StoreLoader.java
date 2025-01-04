package com.migros.courier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.migros.courier.domain.entity.Store;
import com.migros.courier.domain.repository.StoreRepository;
import com.migros.courier.util.ObjectMapperSingleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StoreLoader implements CommandLineRunner {
    private final StoreRepository storeRepository;
    @Override
    public void run(String... args) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/stores.json");
            List<Store> stores = ObjectMapperSingleton.getInstance().readValue(inputStream, new TypeReference<>() {});
            storeRepository.saveAll(stores);
            log.info("Store loaded successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
