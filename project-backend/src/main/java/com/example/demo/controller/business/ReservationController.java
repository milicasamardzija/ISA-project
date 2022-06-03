package com.example.demo.controller.business;

import com.example.demo.dto.business.*;

import com.example.demo.dto.business.ReservationDTO;
import com.example.demo.dto.business.ReservationNewDTO;
import com.example.demo.dto.business.ReservationnDTO;

import com.example.demo.dto.entities.AdventureDTO;
import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.dto.users.ClientProfileDTO;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.Adventure;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.service.business.ReservationService;
import com.example.demo.service.entities.AdventureService;
import com.example.demo.service.entities.EntityService;
import com.example.demo.service.users.CottageOwnerService;
import com.example.demo.service.users.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


@Controller
@RequestMapping(value = "api/reservation")
public class ReservationController {

    private ReservationService reservationService;
    private EntityService entityService;
    private CottageOwnerService cottageOwnerService;
    private ClientService  clientService;

    public ReservationController(ReservationService reservationService,EntityService entityService, CottageOwnerService cottageOwnerService,ClientService clientService ){
        this.reservationService = reservationService;
        this.entityService = entityService;
        this.cottageOwnerService = cottageOwnerService;
        this.clientService = clientService;
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
    public ResponseEntity<List<ReservationnDTO>> getSuccessfulBooking(){
        List<Reservation> allReservation = reservationService.findAll();
        List<ReservationnDTO> reservations = new ArrayList<>();
        for(Reservation r : allReservation ) {
            if (r.getSuccessful()) {
                ReservationnDTO reservationDTO = new ReservationnDTO(r);
                System.out.print("id je"+r.getEntity().getId());
                EntityClass e = this.entityService.findById(r.getEntity().getId());
                EntityDTO entityDTO = new EntityDTO(e);
                reservationDTO.setEntity(entityDTO);
                reservations.add(reservationDTO);
            }
        }
        return  new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(value = "/getSomeReservations/{dateFrom}/{dateTo}")
    public ResponseEntity<List<ReservationnDTO>> getSomeReservations(@PathVariable String dateFrom, @PathVariable String dateTo) throws ParseException {
        String[] parts1 = dateFrom.split("-");
        String year1 = parts1[0];
        String month1 = parts1[1];
        String day1 =parts1[2];
        String realDateFrom = day1+"/"+month1+"/"+year1;

        String[] parts2 = dateTo.split("-");
        String year2 = parts2[0];
        String month2 = parts2[1];
        String day2 =parts2[2];
        String realDateTo = day2+"/"+month2+"/"+year2;

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date dateStart = format.parse(realDateFrom);
        Date dateEnd = format.parse(realDateTo);

        List<Reservation> allReservation = reservationService.findAll();
        List<ReservationnDTO> reservationns = new ArrayList<>();
        for(Reservation r : allReservation ) {
            System.out.print(dateStart);
            System.out.print(dateEnd);

            System.out.print(r.getTerm().getDateStart().toString());
            System.out.print(r.getTerm().getDateEnd().toString());

            int y1 = r.getTerm().getDateStart().getYear();
            int m1 = r.getTerm().getDateStart().getMonth();
            int d1 = r.getTerm().getDateStart().getDay();

            int y2 = r.getTerm().getDateEnd().getYear();
            int m2 = r.getTerm().getDateEnd().getMonth();
            int d2 = r.getTerm().getDateEnd().getDay();

            if (r.getTerm().getDateStart().after(dateStart) || (y1 == dateStart.getYear() && m1 == dateStart.getMonth() && d1 == dateStart.getDay())) {
                if ((r.getTerm().getDateEnd().before(dateEnd)) || (y2 == dateEnd.getYear() && m2 == dateEnd.getMonth() && d2 == dateEnd.getDay())) {
                           ReservationnDTO reservationDTO = new ReservationnDTO(r);
                           EntityClass e = this.entityService.findById(r.getEntity().getId());
                           EntityDTO entityDTO = new EntityDTO(e);
                           reservationDTO.setEntity(entityDTO);
                           reservationns.add(reservationDTO);
                }
            }

        }
        return  new ResponseEntity<>(reservationns, HttpStatus.OK);
    }

    @GetMapping(value = "/getEarningsByDate/{dateFrom}/{dateTo}")
    public ResponseEntity<Double> getEarningsByDate(@PathVariable String dateFrom, @PathVariable String dateTo) throws ParseException {
        System.out.print("USLA SAMN U EARNINGS");
        Double totalEarnings = 0.0;
        String[] parts1 = dateFrom.split("-");
        String year1 = parts1[0];
        String month1 = parts1[1];
        String day1 =parts1[2];
        String realDateFrom = day1+"/"+month1+"/"+year1;

        String[] parts2 = dateTo.split("-");
        String year2 = parts2[0];
        String month2 = parts2[1];
        String day2 =parts2[2];
        String realDateTo = day2+"/"+month2+"/"+year2;

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date dateStart = format.parse(realDateFrom);
        Date dateEnd = format.parse(realDateTo);

        List<Reservation> allReservations = reservationService.findAll();
        List<ReservationnDTO> reservationns = new ArrayList<>();
        for(Reservation r : allReservations ) {
            System.out.print(dateStart);
            System.out.print(dateEnd);

            System.out.print(r.getTerm().getDateStart().toString());
            System.out.print(r.getTerm().getDateEnd().toString());

            int y1 = r.getTerm().getDateStart().getYear();
            int m1 = r.getTerm().getDateStart().getMonth();
            int d1 = r.getTerm().getDateStart().getDay();

            int y2 = r.getTerm().getDateEnd().getYear();
            int m2 = r.getTerm().getDateEnd().getMonth();
            int d2 = r.getTerm().getDateEnd().getDay();

            if (r.getTerm().getDateStart().after(dateStart) || (y1 == dateStart.getYear() && m1 == dateStart.getMonth() && d1 == dateStart.getDay())) {
                if ((r.getTerm().getDateEnd().before(dateEnd)) || (y2 == dateEnd.getYear() && m2 == dateEnd.getMonth() && d2 == dateEnd.getDay())) {
                    ReservationnDTO reservationDTO = new ReservationnDTO(r);
                    EntityClass e = this.entityService.findById(r.getEntity().getId());
                    EntityDTO entityDTO = new EntityDTO(e);
                    reservationDTO.setEntity(entityDTO);
                    reservationns.add(reservationDTO);
                }
            }

        }
        for( ReservationnDTO reservationDTO: reservationns) {
            totalEarnings += reservationDTO.getPrice();
        }
        System.out.print("Suma je"+totalEarnings);
        return  new ResponseEntity<>(totalEarnings, HttpStatus.OK);
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
    public ResponseEntity<String> save(@RequestBody ReservationNewDTO reservation) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (!this.reservationService.checkPenals(user)){
            return new ResponseEntity<String>("Imate tri ili vise penala!Ne mozete vrsiti rezervisanje do prvog sledeceg u mesecu.", HttpStatus.OK);
        }

        if (this.reservationService.save(reservation, user)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Vec ste jednom zapazali i otkazali ovu vikendicu u ovom periodu!Ne mozete ponovo!", HttpStatus.OK);
        }
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return new ResponseEntity<>(reservationService.cancelReservation(id, user), HttpStatus.OK);
    }

    @PostMapping("/totalPrice")
    public ResponseEntity<Double> getTotalPrice(@RequestBody PriceDTO price){
        return new ResponseEntity<>(this.reservationService.getTotalPrice(price), HttpStatus.OK);
    }

    //cottage owner
    @GetMapping("/allReservationsCottageOwner")
    public ResponseEntity<List<ReservationForOwnerDTO>> allReservationsCottageOwner()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
       // CottageOwner cottageOwner = cottageOwnerService.findById(user.getId());

        List<Reservation> allReservations = reservationService.getAllReservationsForCottageOwner(user.getId());
        List<ReservationForOwnerDTO> ret = new ArrayList<>();
        for(Reservation reservation : allReservations ) {
            Client c = reservationService.findClientForReservation(reservation.getId());
            ClientProfileDTO client = new ClientProfileDTO(c);
            ret.add(new ReservationForOwnerDTO(reservation,client));
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping("/checkDate")
    public ResponseEntity<Boolean> checkDate(@RequestBody DateDTO date){
        if (this.reservationService.checkDate(date)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/getDateEnd")
    public ResponseEntity<Date> getDateEnd(@RequestBody DateDTO date){
        return new ResponseEntity<>(this.reservationService.getDateEnd(date),HttpStatus.OK);
    }

}
