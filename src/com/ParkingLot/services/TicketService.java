package com.ParkingLot.services;

import com.ParkingLot.exceptions.GateNotFoundException;
import com.ParkingLot.factory.SpotAssigmentStrategyFactory;
import com.ParkingLot.models.*;
import com.ParkingLot.repositories.GateRepository;
import com.ParkingLot.repositories.ParkingLotRepository;
import com.ParkingLot.repositories.TicketRepository;
import com.ParkingLot.repositories.VehicleRepository;
import com.ParkingLot.strategy.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(Long gateId, String vehicleNo, String ownerName, VehicleType vehicleType) throws GateNotFoundException {
        Ticket ticket = new Ticket();
        ticket.setCreatedAt(new Date());

        Optional<Gate> optionalGate = gateRepository.findGateById(gateId);

        if(optionalGate.isEmpty()){
            throw new GateNotFoundException();
        }

        Gate gate = optionalGate.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getOperator());

        Optional<Vehicle> optionalVehicle = vehicleRepository.getVehicleByNumber(vehicleNo);
        Vehicle savedVehicle;

        if(optionalVehicle.isEmpty()){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNo(vehicleNo);
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(ownerName);
            vehicleRepository.save();
            savedVehicle = vehicle;
        } else {
            savedVehicle = optionalVehicle.get();
        }

        ticket.setVehicle(savedVehicle);

        ParkingLot parkingLot = parkingLotRepository.getParkingLot();
        SpotAssignmentStrategyType spotAssignmentStrategyType = parkingLot.getSpotAssignmentStrategyType();
        SpotAssignmentStrategy spotAssignmentStrategy = SpotAssigmentStrategyFactory.getSpotAssignmentStrategy(spotAssignmentStrategyType);

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType, gate);
        ticket.setParkingSpot(parkingSpot);

        ticket.setTicketNo("TICKET_" + gateId + "_" + ticket.getGeneratedAt());
        ticketRepository.save(ticket);

        return ticket;
    }
}
