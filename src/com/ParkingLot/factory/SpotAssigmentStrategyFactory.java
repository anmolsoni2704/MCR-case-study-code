package com.ParkingLot.factory;

import com.ParkingLot.models.SpotAssignmentStrategyType;
import com.ParkingLot.strategy.CheapestSpotAssignmentStrategy;
import com.ParkingLot.strategy.NearestSpotAssignmentStrategy;
import com.ParkingLot.strategy.SpotAssignmentStrategy;

public class SpotAssigmentStrategyFactory {
    public static SpotAssignmentStrategy getSpotAssignmentStrategy(SpotAssignmentStrategyType spotAssignmentStrategyType){
        if(spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.CHEAPEST)){
            return new CheapestSpotAssignmentStrategy();
        }else if(spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.NEAREST)){
            return new NearestSpotAssignmentStrategy();
        }else{
            return null;
        }
    }
}
