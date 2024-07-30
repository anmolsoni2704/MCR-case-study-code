package com.ParkingLot.controllers;

import com.ParkingLot.dtos.IssueTicketRequestDTO;
import com.ParkingLot.dtos.IssueTicketResponseDTO;
import com.ParkingLot.dtos.ResponseStatus;
import com.ParkingLot.exceptions.GateNotFoundException;
import com.ParkingLot.models.Ticket;
import com.ParkingLot.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO issueTicketRequestDTO){
        IssueTicketResponseDTO issueTicketResponseDTO = new IssueTicketResponseDTO();

        try{
            Ticket ticket = ticketService.issueTicket(issueTicketRequestDTO.getGateId(),
                    issueTicketRequestDTO.getVehicleNumber() ,
                    issueTicketRequestDTO.getVehicleOwner(),
                    issueTicketRequestDTO.getVehicleType());

            issueTicketResponseDTO.setTicket(ticket);
            issueTicketResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch(Exception e){
            e.getMessage();
            issueTicketResponseDTO.setResponseStatus(ResponseStatus.FAILED);
        }
        return issueTicketResponseDTO;

    }
}
