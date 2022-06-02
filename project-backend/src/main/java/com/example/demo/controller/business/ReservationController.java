package com.example.demo.controller.business;

import com.example.demo.dto.business.*;
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
import java.util.List;
import java.util.Optional;

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
            if(reservationService.findClientForReservation(reservation.getId()) == null){
                ret.add(new ReservationForOwnerDTO(reservation.getId(), reservation.getAction(), new ReservedTermDTO(reservation.getTerm()), reservation.getPrice(),new EntityDTO(reservation.getEntity()), reservation.getCanceled(), reservation.getDuration(), new ClientProfileDTO("/", 0,"/","/") ));
            }else {
                Client c = reservationService.findClientForReservation(reservation.getId());
                ClientProfileDTO client = new ClientProfileDTO(c);
                ret.add(new ReservationForOwnerDTO(reservation,client));}

        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }


    @GetMapping("/allReservationsBoatOwner")
    public ResponseEntity<List<ReservationForOwnerDTO>> allReservationsBoatOwner()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> allReservations = reservationService.getAllReservationsForBoatOwner(user.getId());
        List<ReservationForOwnerDTO> ret = new ArrayList<>();
        for(Reservation reservation : allReservations ) {
            Client c = reservationService.findClientForReservation(reservation.getId());
            ClientProfileDTO client = new ClientProfileDTO(c);
            ret.add(new ReservationForOwnerDTO(reservation,client));
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<HttpStatus> checkIfFutureReservationExist(@PathVariable int id){
        if(this.reservationService.findFutureReservationsForEntity(id)){
            return  new ResponseEntity<>( HttpStatus.OK);
        }else{
            return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/checkAvailability")
    public ResponseEntity<HttpStatus> checkAvailability(@RequestBody ActionReservationDTO action){
            if( this.reservationService.checkAvailability(action)){
                return  new ResponseEntity<>( HttpStatus.OK);
            } else {
                return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
            }
    }



    @PostMapping("/actionCottage")
    public ResponseEntity<HttpStatus> createActionReservationCottage(@RequestBody ActionReservationDTO action)  {
        this.reservationService.saveActionCottage(action);

        return  new ResponseEntity<>( HttpStatus.OK);
    }

}
