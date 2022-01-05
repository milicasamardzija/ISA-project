package com.example.demo.controller.business;

import com.example.demo.dto.business.ReservationDTO;
import com.example.demo.dto.entities.AdventureDTO;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.entities.Adventure;
import com.example.demo.model.users.User;
import com.example.demo.service.business.ReservationService;
import com.example.demo.service.entities.AdventureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/scheduledReservations")
    public ResponseEntity<List<ReservationDTO>> getAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.fetchAllFutureReservationsForClient(user.getId());
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            ret.add(new ReservationDTO(reservation));
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/historyReservationsAdventures")
    public ResponseEntity<List<ReservationDTO>> historyReservationsAdventures(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.fetchAllHistoryReservationsForClientAdventures(user.getId());
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            ret.add(new ReservationDTO(reservation));
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/historyReservationsBoats")
    public ResponseEntity<List<ReservationDTO>> historyReservationsBoats(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.fetchAllHistoryReservationsForClientBoats(user.getId());
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            ret.add(new ReservationDTO(reservation));
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/historyReservationsCottages")
    public ResponseEntity<List<ReservationDTO>> historyReservationsCottages(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.fetchAllHistoryReservationsForClientCottages(user.getId());
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            ret.add(new ReservationDTO(reservation));
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

}
