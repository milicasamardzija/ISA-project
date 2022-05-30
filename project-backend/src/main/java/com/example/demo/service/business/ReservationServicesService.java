package com.example.demo.service.business;

import com.example.demo.model.business.ReservationServices;
import com.example.demo.repository.business.ReservationServicesRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationServicesService {

    private ReservationServicesRepository reservationServicesRepository;

    public ReservationServicesService(ReservationServicesRepository reservationServicesRepository) {
        this.reservationServicesRepository = reservationServicesRepository;
    }

    public void save(ReservationServices a) {
        this.reservationServicesRepository.save(a);
    }
}
