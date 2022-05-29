package com.example.demo.controller.business;

import com.example.demo.dto.business.ReservationDTO;
import com.example.demo.dto.business.ReservationNewDTO;
import com.example.demo.dto.entities.AdventureDTO;
import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.Adventure;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;
import com.example.demo.service.business.ReservationService;
import com.example.demo.service.entities.AdventureService;
import com.example.demo.service.entities.EntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "api/reservation")
public class ReservationController {

    private ReservationService reservationService;
    private EntityService entityService;

    public ReservationController(ReservationService reservationService,EntityService entityService){
        this.reservationService = reservationService;
        this.entityService = entityService;
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

        return new ResponseEntity<>(ret, HttpStatus.OK);
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

    @GetMapping(value = "/getSuccessfulBooking")
    public ResponseEntity<List<ReservationDTO>> getSuccessfulBooking(){
        List<Reservation> allReservation = reservationService.findAll();
        List<ReservationDTO> reservations = new ArrayList<>();
        for(Reservation r : allReservation ) {
            if (r.getSuccessful()) {
                 ReservationDTO reservation = new ReservationDTO();
                 reservation.setId(r.getId());
                 //reservation.setDateStart(r.getTerm().getDateStart().toString());
                 //reservation.setDateEnd(r.getTerm().getDateEnd().toString());
                 reservation.setPrice(r.getPrice());
                 EntityClass entity = new EntityClass();
                 entity = entityService.findById(r.getEntity().getId());
                 reservation.setEntity(entity);
                 reservations.add(reservation);
            }
        }
        return  new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(value = "/getTotalEarnings")
    public ResponseEntity<Double> getTotalEarnings(){
        System.out.print("Cao1");
        Double totalEarnings = 0.0;
        List<Reservation> allReservation = reservationService.findAll();
        List<ReservationDTO> reservations = new ArrayList<>();
        for(Reservation r : allReservation ) {
            if (r.getSuccessful()) {
                ReservationDTO reservation = new ReservationDTO();
                reservation.setId(r.getId());
                //reservation.setDateStart(r.getTerm().getDateStart().toString());
                //reservation.setDateEnd(r.getTerm().getDateEnd().toString());
                reservation.setPrice(r.getPrice());
                EntityClass entity = new EntityClass();
                entity = entityService.findById(r.getEntity().getId());
                reservation.setEntity(entity);
                reservations.add(reservation);
            }
        }
        for( ReservationDTO reservationDTO: reservations) {
            totalEarnings += reservationDTO.getPrice();
        }
        System.out.print("Cao");
        return  new ResponseEntity<>(totalEarnings, HttpStatus.OK);
    }

    @GetMapping("/entity/{id}")
    public ResponseEntity<EntityDTO> findEntityByReservation(@PathVariable int id){

        Reservation reservation = this.reservationService.findById(id);
        EntityClass entity = this.reservationService.findEntityByReservation(id);

        return  new ResponseEntity<>(new EntityDTO(entity, reservation.getEntityType()), HttpStatus.OK);
    }

    @GetMapping("/actions/{id}")
    public ResponseEntity<List<ReservationDTO>> getActionsForEntity(@PathVariable int id){
        List<Reservation> reservations = reservationService.getActionsForEntity(id);
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation reservation : reservations ) {
            ret.add(new ReservationDTO(reservation));
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/actionReservation/{id}")
    public ResponseEntity<HttpStatus> actionReservation(@PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        reservationService.actionReservation(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> save(@RequestBody ReservationNewDTO reservation) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        this.reservationService.save(reservation, user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable int id){
        return new ResponseEntity<>(reservationService.cancelReservation(id), HttpStatus.OK);
    }
}
