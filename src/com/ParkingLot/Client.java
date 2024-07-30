package com.ParkingLot;

import com.ParkingLot.controllers.TicketController;
import com.ParkingLot.dtos.IssueTicketRequestDTO;
import com.ParkingLot.dtos.IssueTicketResponseDTO;
import com.ParkingLot.dtos.ResponseStatus;
import com.ParkingLot.models.ParkingLot;
import com.ParkingLot.models.Ticket;
import com.ParkingLot.repositories.GateRepository;
import com.ParkingLot.repositories.ParkingLotRepository;
import com.ParkingLot.repositories.TicketRepository;
import com.ParkingLot.repositories.VehicleRepository;
import com.ParkingLot.services.TicketService;

public class Client {
    public static void main(String[] args) {
        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        GateRepository gateRepository = new GateRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        TicketService ticketService = new TicketService(gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository);

        TicketController ticketController = new TicketController(ticketService);

        IssueTicketRequestDTO issueTicketRequestDTO = new IssueTicketRequestDTO();

        IssueTicketResponseDTO issueTicketResponseDTO = ticketController.issueTicket(issueTicketRequestDTO);

        if(issueTicketResponseDTO.getResponseStatus().equals(ResponseStatus.SUCCESS)){
            Ticket ticket = issueTicketResponseDTO.getTicket();

        }else{
            //throw an exception
        }
    }
}
