package com.ParkingLot.repositories;

import com.ParkingLot.models.Gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepository {
    Map<Long, Gate> gateMap = new HashMap<>();

    public Optional<Gate> findGateById(Long id) {
        if (gateMap.containsKey(id)) {
            return Optional.of(gateMap.get(id));
        }
        return Optional.empty();
    }
}
