package com.example.demo.controller.business;

import com.example.demo.dto.business.*;

import com.example.demo.dto.business.ReservationDTO;
import com.example.demo.dto.business.ReservationNewDTO;
import com.example.demo.dto.business.ReservationnDTO;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.enums.EntityType;
import com.example.demo.dto.users.ClientProfileDTO;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.business.ReservationServices;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.service.business.ReservationService;
import com.example.demo.service.business.ReservationServicesService;
import com.example.demo.service.business.ReservedTermService;
import com.example.demo.service.entities.AdditionalServicesService;
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


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


@CrossOrigin
@Controller
@RequestMapping(value = "api/reservation")
public class ReservationController {

    private ReservationService reservationService;
    private EntityService entityService;
    private ClientService  clientService;
    private ReservedTermService reservedTermService;
    private AdditionalServicesService additionalServicesService;
    private ReservationServicesService reservationServicesService;

    public ReservationController(ReservationServicesService reservationServicesService,AdditionalServicesService additionalServicesService,ReservationService reservationService,EntityService entityService, CottageOwnerService cottageOwnerService,ClientService clientService, ReservedTermService reservedTermService){
        this.reservationService = reservationService;
        this.entityService = entityService;
        this.clientService = clientService;
        this.reservedTermService=reservedTermService;
        this.additionalServicesService = additionalServicesService;
        this.reservationServicesService=reservationServicesService;
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

    @GetMapping("/getMyReservations")
    public ResponseEntity<List<ReservationnDTO>> getMyReservations(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.findAllReservationsForInstructor(user.getId());
        List<ReservationnDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            ReservationnDTO reservationDTO = new ReservationnDTO(reservation);
            EntityClass e = this.entityService.findById(reservation.getEntity().getId());
            EntityDTO entityDTO = new EntityDTO(e);
            reservationDTO.setEntity(entityDTO);
            ret.add(reservationDTO);
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/myRegularReservation")
    public ResponseEntity<List<ReservationnDTO>> getMyRegularReservation(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.findAllReservationsForInstructor(user.getId());
        List<ReservationnDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            if (!reservation.getAction()) {
            ReservationnDTO reservationDTO = new ReservationnDTO();
            reservationDTO.setId(reservation.getId());
            reservationDTO.setCanceled(reservation.getCanceled());
            reservationDTO.setPrice(reservation.getPrice());
            reservationDTO.setDuration(reservation.getDuration());
            reservationDTO.setClientID(String.valueOf(reservation.getClient().getId()));

            ReservedTerm rt = this.reservedTermService.findById(reservation.getTerm().getId());
            ReservedTermDTO rtDTO = new ReservedTermDTO(rt);
            reservationDTO.setTerm(rtDTO);
            reservationDTO.setDateEnd(rtDTO.getDateEnd().toString());
            reservationDTO.setDateStart(rtDTO.getDateStart().toString());
            EntityClass e = this.entityService.findById(reservation.getEntity().getId());
            EntityDTO entityDTO = new EntityDTO(e);
            reservationDTO.setEntity(entityDTO);
            ret.add(reservationDTO);
            }
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/myActionReservation")
    public ResponseEntity<List<ReservationnDTO>> myActionReservation(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.findAllReservationsForInstructor(user.getId());
        List<ReservationnDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            if (reservation.getAction()) {
                ReservationnDTO reservationDTO = new ReservationnDTO();
                reservationDTO.setId(reservation.getId());
                reservationDTO.setCanceled(reservation.getCanceled());
                reservationDTO.setPrice(reservation.getPrice());
                reservationDTO.setDuration(reservation.getDuration());

                ReservedTerm rt = this.reservedTermService.findById(reservation.getTerm().getId());
                ReservedTermDTO rtDTO = new ReservedTermDTO(rt);
                reservationDTO.setTerm(rtDTO);
                reservationDTO.setDateEnd(rtDTO.getDateEnd().toString());
                reservationDTO.setDateStart(rtDTO.getDateStart().toString());
                EntityClass e = this.entityService.findById(reservation.getEntity().getId());
                EntityDTO entityDTO = new EntityDTO(e);
                reservationDTO.setEntity(entityDTO);
                ret.add(reservationDTO);
            }
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
        try {
            reservationService.actionReservation(id, user);
        } catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody ReservationNewDTO reservation){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (!this.reservationService.checkPenals(user)){
            return new ResponseEntity<String>("Imate tri ili vise penala!Ne mozete vrsiti rezervisanje do prvog sledeceg u mesecu.", HttpStatus.OK);
        }

        boolean ret = false;
        try {
            ret = !this.reservationService.save(reservation, user);
        } catch (Exception e){
            System.out.print("*******************************************************************************************");
            System.out.print(e);
            System.out.print("*******************************************************************************************");
            return new ResponseEntity<String>("Ne mozete u ovom trenutku da izvrsite rezervaciju, neko je vec zakazauje!Pokusajte ponovo za koji minut!", HttpStatus.BAD_REQUEST);
        }

        if (ret){
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
            if(reservationService.findClientForReservation(reservation.getId()) == null){
                ret.add(new ReservationForOwnerDTO(reservation.getId(), reservation.getAction(), new ReservedTermDTO(reservation.getTerm()), reservation.getPrice(),new EntityDTO(reservation.getEntity()), reservation.getCanceled(), reservation.getDuration(), new ClientProfileDTO("/", 0,"/","/") ));
            }else {
                Client c = reservationService.findClientForReservation(reservation.getId());
                ClientProfileDTO client = new ClientProfileDTO(c);
                ret.add(new ReservationForOwnerDTO(reservation,client));}

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

    @PostMapping("/addAction")
    public ResponseEntity<HttpStatus> addAction(@RequestBody ActionRequestDTO actionRequestDTO) throws ParseException {
        System.out.print("naziv entiteta je"+actionRequestDTO.getName());

        ReservedTerm rt = new ReservedTerm();
        EntityClass e = this.entityService.findByName(actionRequestDTO.getName());
        rt.setEntity(e);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        String[] parts1 = actionRequestDTO.getDateStart().split("-");
        String year1 = parts1[0];
        String month1 = parts1[1];
        String day1 =parts1[2];
        String realDateFrom = day1+"/"+month1+"/"+year1;

        String[] parts2 = actionRequestDTO.getDateEnd().split("-");
        String year2 = parts2[0];
        String month2 = parts2[1];
        String day2 =parts2[2];
        String realDateTo = day2+"/"+month2+"/"+year2;

        Date dateStart = format.parse(realDateFrom);
        Date dateEnd = format.parse(realDateTo);
        rt.setDateStart(dateStart);
        rt.setDateEnd(dateEnd);
        System.out.print("DOVDE SAM");

        this.reservedTermService.saveNewTerm(rt);

        Reservation r = new Reservation();
        r.setTerm(rt);
        r.setPrice(actionRequestDTO.getPrice());
        r.setEntityType(EntityType.ADVENTURE);

        String[] parts3 = actionRequestDTO.getValidFrom().split("-");
        String year3 = parts3[0];
        String month3 = parts3[1];
        String day3 =parts3[2];
        String validFromString = day3+"/"+month3+"/"+year3;

        String[] parts4 =actionRequestDTO.getValidTo().split("-");
        String year4 = parts4[0];
        String month4 = parts4[1];
        String day4=parts4[2];
        String validToString = day4+"/"+month4+"/"+year4;

        Date validFrom = format.parse(validFromString);
        Date validTo = format.parse(validToString);

        r.setValidFrom(validFrom);
        r.setValidTo(validTo);
        r.setAction(true);
        long diff = dateEnd.getTime() - dateStart.getTime();
        int duration = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        r.setDuration(duration);
        r.setEntity(e);

        this.reservationService.saveReservation(r);

        List<AdditionalServiceDTO> list = actionRequestDTO.getAdditionalServices();
        for (AdditionalServiceDTO a: list) {
            AdditionalService as = this.additionalServicesService.findByName(a.getName());
            ReservationServices rs = new ReservationServices();
            rs.setName(as.getName());
            rs.setReservation(r);
            rs.setPrice(as.getPrice());
            this.reservationServicesService.save(rs);
            System.out.print(a.getName());
        }

        this.reservationService.sendMailToSubscribedClients(e);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/allReservationsBoatOwner")
    public ResponseEntity<List<ReservationForOwnerDTO>> allReservationsBoatOwner()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> allReservations = reservationService.getAllReservationsForBoatOwner(user.getId());
        List<ReservationForOwnerDTO> ret = new ArrayList<>();
        for(Reservation reservation : allReservations ) {
            if(reservationService.findClientForReservation(reservation.getId()) == null){
                ret.add(new ReservationForOwnerDTO(reservation.getId(),reservation.getAction(), new ReservedTermDTO(reservation.getTerm()), reservation.getPrice(),new EntityDTO(reservation.getEntity()), reservation.getCanceled(), reservation.getDuration(), new ClientProfileDTO("/", 0,"/","/") ));
            }else {
                Client c = reservationService.findClientForReservation(reservation.getId());
                ClientProfileDTO client = new ClientProfileDTO(c);
                ret.add(new ReservationForOwnerDTO(reservation, client));
            }
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

    @PostMapping("/actionBoat")
    public ResponseEntity<HttpStatus> createActionReservationBoat(@RequestBody ActionReservationDTO action)  {
        this.reservationService.saveActionBoat(action);

        return  new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/checkIfReservationExist")
    public ResponseEntity<HttpStatus> checkIfReservationExist(@RequestBody UnavailablePeriodDTO action){
        if( this.reservationService.checkReservations(action)){
            return  new ResponseEntity<>( HttpStatus.OK);
        } else {
            return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/unavailablePeriodCottageOwner")
    public ResponseEntity<HttpStatus> unavailablePeriodDefineCO(@RequestBody UnavailablePeriodDTO action){

        this.reservationService.saveUnavailablePeriod(action, 1);
            return  new ResponseEntity<>( HttpStatus.OK);

    }

    @PostMapping("/unavailablePeriodBoatOwner")
    public ResponseEntity<HttpStatus> unavailablePeriodDefineBO(@RequestBody UnavailablePeriodDTO action){

        this.reservationService.saveUnavailablePeriod(action, 0);
        return  new ResponseEntity<>( HttpStatus.OK);

    }

    @GetMapping("/currentClient/{id}")
    public ResponseEntity<ClientProfileDTO> findCurrentClientForEntity(@PathVariable int id){
            ClientProfileDTO client = this.reservationService.findCurrentClientForEntity(id);
        return  new ResponseEntity<>( client, HttpStatus.OK);

    }

    @PostMapping("/makeReservationOwner")
    public  ResponseEntity<HttpStatus> makeReservationOwner(@RequestBody ReservationNewOwnerDTO res) throws Exception {
        this.reservationService.saveReservationOwner(res);
        return  new ResponseEntity<>( HttpStatus.OK);
    }


}
