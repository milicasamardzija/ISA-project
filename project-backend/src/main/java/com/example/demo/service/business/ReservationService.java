package com.example.demo.service.business;

import com.example.demo.dto.business.ReservationNewDTO;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ReservationRepository;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.entities.EntityService;
import com.example.demo.service.users.ClientService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private EmailService emailService;
    private ClientService clientService;
    private EntityService entityService;

    public ReservationService (ReservationRepository reservationRepository, EmailService emailService, ClientService clientService, EntityService entityService) {
        this.reservationRepository = reservationRepository;
        this.emailService = emailService;
        this.clientService = clientService;
        this.entityService = entityService;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> fetchAllFutureReservationsForClient(int clientId) {
        return reservationRepository.fetchAllFutureReservationsForClient(clientId, new Date());
    }

    public List<Reservation> fetchAllHistoryReservationsForClientBoats(int clientId) {
        return reservationRepository.fetchAllHistoryReservationsForClientBoats(clientId, new Date());
    }

    public List<Reservation> fetchAllHistoryReservationsForClientCottages(int clientId) {
        return reservationRepository.fetchAllHistoryReservationsForClientCottages(clientId, new Date());
    }

    public List<Reservation> fetchAllHistoryReservationsForClientAdventures(int clientId) {
        return reservationRepository.fetchAllHistoryReservationsForClientAdventures(clientId, new Date());
    }

    public EntityClass findEntityByReservation(int id) {
        return this.reservationRepository.findEntityByReservation(id);
    }

    public Reservation findById(int id){
        return this.reservationRepository.findById(id);
    }

    public void save(ReservationNewDTO reservation, User user) throws Exception {
        Client client = this.clientService.findById(user.getEmail());
        EntityClass entity = this.entityService.findById(reservation.getEntityId());

        //getting start and end date
        String[] time = reservation.getTimeStart().split(":");
        String hour = time[0];
        String minutes = time[1];
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(reservation.getDateStart());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(reservation.getDateStart());
        calEnd.add(Calendar.DAY_OF_YEAR, reservation.getDuration());

        Reservation newReservation = new Reservation();
        newReservation.setDateStart(calStart.getTime());
        newReservation.setDateEnd(calEnd.getTime());
        newReservation.setPrice(reservation.getPrice());
        newReservation.setClient(client);
        newReservation.setEntity(entity);
        this.reservationRepository.save(newReservation);
        this.emailService.sendEmailForReservation(user);
    }
}
