package com.example.demo.service.business;

import com.example.demo.model.business.Reservation;
import com.example.demo.repository.business.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService (ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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
}
