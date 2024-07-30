package com.ParkingLot.strategy;

import com.ParkingLot.models.Gate;
import com.ParkingLot.models.ParkingSpot;
import com.ParkingLot.models.VehicleType;

public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(VehicleType vehicleType, Gate gate);
}
