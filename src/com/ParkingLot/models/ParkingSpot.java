package com.ParkingLot.models;

import java.util.List;

public class ParkingSpot extends BaseModel{
    private ParkingSpotStatus parkingSpotStatus;
    private int parkingSpotNo;
    private List<VehicleType> vehicleTypeList;
    private ParkingFloor parkingFloor;
}
