package com.example.demo.service.business;

import com.example.demo.dto.business.DateDTO;
import com.example.demo.dto.business.ActionReservationDTO;
import com.example.demo.dto.business.PriceDTO;
import com.example.demo.dto.business.ReservationNewDTO;
import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.dto.enums.EntityType;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.business.ReservationServices;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ReservationRepository;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.entities.AdditionalServicesService;
import com.example.demo.service.entities.CottageService;
import com.example.demo.service.entities.EntityService;
import com.example.demo.service.users.ClientService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.DateTimeException;
import java.util.*;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private EmailService emailService;
    private ClientService clientService;
    private EntityService entityService;
    private ReservedTermService reservedTermService;
    private AdditionalServicesService additionalService;
    private ReservationServicesService reservationServicesService;
  //  private CottageService cottageService;

    public ReservationService (ReservationRepository reservationRepository, EmailService emailService, ClientService clientService, EntityService entityService, ReservedTermService reservedTermService, AdditionalServicesService additionalService, ReservationServicesService reservationServicesService, CottageService cottageService) {
        this.reservationRepository = reservationRepository;
        this.emailService = emailService;
        this.clientService = clientService;
        this.entityService = entityService;
        this.reservedTermService = reservedTermService;
        this.additionalService = additionalService;
        this.reservationServicesService = reservationServicesService;
       // this.cottageService = cottageService;
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

    public Reservation findById(int id) {
        return this.reservationRepository.findById(id);
    }

    public Boolean checkDate(DateDTO searchParam){
        String[] time = searchParam.getTime().split(":");
        String hour = time[0];
        String minutes = time[1];

        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(searchParam.getDate());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(searchParam.getDate());
        calEnd.add(Calendar.DAY_OF_YEAR, searchParam.getNumber());

        Boolean isNotReserved = true;
        EntityClass cottage = this.entityService.findById(searchParam.getId());
            for (ReservedTerm term : cottage.getReservedTerms()) {
                if (!term.isCanceled()) {
                    //  |***term***|
                    //      |----cal---|
                    if (calStart.getTime().after(term.getDateStart()) && calStart.getTime().before(term.getDateEnd()) && calEnd.getTime().after(term.getDateEnd())
                    ) {
                        isNotReserved = false;
                    }
                    //  |----cal---|
                    //     |***term***|
                    if (calStart.getTime().before(term.getDateStart()) && calEnd.getTime().before(term.getDateEnd()) && calEnd.getTime().after(term.getDateStart())) {
                        isNotReserved = false;
                    }
                    //  |------cal------|
                    //     |***term***|
                    if (calStart.getTime().before(term.getDateStart()) && calEnd.getTime().after(term.getDateEnd())
                    ) {
                        isNotReserved = false;
                    }
                    //    |----cal----|
                    //  |******term******|
                    if (calStart.getTime().after(term.getDateStart()) && calEnd.getTime().before(term.getDateEnd())
                    ) {
                        isNotReserved = false;
                    }
                }

            }
            return  isNotReserved;
    }

    public Boolean checkPenals(User user){
        Client client = this.clientService.findById(user.getEmail());

        if (client.getPenals() >= 3){
            return false;
        }

        return  true;
    }

    public Boolean save(ReservationNewDTO reservation, User user) throws Exception {
        Client client = this.clientService.findById(user.getEmail());
        EntityClass entity = this.entityService.findById(reservation.getEntityId());

        boolean alreadyReserved = false;

        //getting start and end date
        String[] time = reservation.getTimeStart().split(":");
        String hour = time[0];
        String minutes = time[1];
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(reservation.getDateStart());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour) - 2);
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(reservation.getDateStart());
        calEnd.add(Calendar.DAY_OF_YEAR, reservation.getDuration());

        Reservation newReservation = new Reservation();
        newReservation.setTerm(new ReservedTerm(calStart.getTime(), calEnd.getTime(), entity));
        newReservation.setPrice(reservation.getPrice());
        newReservation.setClient(client);
        newReservation.setEntity(entity);
        newReservation.setEntityType(EntityType.COTTAGE);
        newReservation.setDuration(reservation.getDuration());

        ArrayList<ReservationServices> services = new ArrayList<>();

        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);
            services.add(a);
            this.reservationServicesService.save(a);
        }

        newReservation.setReservationServices(services);

        Reservation canceledReservation = this.reservationRepository.getCanceledReservation(client.getId(), entity.getId(), newReservation.getTerm().getDateStart(), newReservation.getTerm().getDateEnd());

        if (canceledReservation == null) {
            entity.getReservedTerms().add(newReservation.getTerm());
            this.reservationRepository.save(newReservation);

            for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
                ReservationServices a = new ReservationServices(as);
                a.setReservation(newReservation);
                this.reservationServicesService.save(a);
            }

            this.emailService.sendEmailForReservation(user);
        } else {
            alreadyReserved = true;
        }

        return alreadyReserved;
    }

    public List<Reservation> getActionsForEntity(int id) {
        return this.reservationRepository.getActionsForEntity(id);
    }

    public void actionReservation(int id, User user) {
        Reservation reservation = this.reservationRepository.findById(id);
        Client client = this.clientService.findById(user.getEmail());
        reservation.setClient(client);
        this.reservationRepository.save(reservation);
        emailService.sendEmailForReservationAction(user.getEmail());
    }

    //klijent otkazuje rezervaciju
    public String cancelReservation(int id, User user) {
        Reservation reservation = this.reservationRepository.findById(id);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, 3);

        if (cal.getTime().before(reservation.getTerm().getDateStart())) {
            reservation.setCanceled(true);
            Client client = this.clientService.findById(user.getEmail());
            int penals = client.getPenals();
            client.setPenals(penals + 1);
            this.clientService.save(client);
            Client c = this.clientService.findById(user.getEmail());

            ReservedTerm term = reservation.getTerm();
            term.setCanceled(true);

           this.reservedTermService.save(term);
           this.reservationRepository.save(reservation);
           return "Reservation is canceled!";
        }

        return "You can cancel your reservation no later than three days before the start!";
    }

    public Double getTotalPrice(PriceDTO price) {
        double ret = price.getEntityPrice();

        for(AdditionalServiceDTO as : price.getServices()) {
            ret = ret + as.getPrice();
        }

        return  ret;
    }

    public List<Reservation> getAllReservationsForCottageOwner(int id_owner) {
        List<Reservation> ret = new ArrayList<>();

        for (Reservation r: this.reservationRepository.findAllReservationsForCottageOwner(id_owner) ) {
            if(r.getAction() ){
                ret.add(r);
            }
        }
        return  ret;

    }

    public List<Reservation> getAllReservationsForCottageOwnerActions(int id_owner) {
        List<Reservation> ret = new ArrayList<>();
        for (Reservation r: this.reservationRepository.findAllReservationsForCottageOwner(id_owner) ) {
            if(r.getAction()){
                ret.add(r);
            }
        }
        return  ret;

    }

    public List<Reservation> getAllReservationsForBoatOwner(int id_owner) {
        return   this.reservationRepository.findAllReservationsForBoatOwner(id_owner);
    }

    public Client findClientForReservation(int id){
        return this.reservationRepository.findClientFromReservation(id);
    }


//    public Integer getMaxPeople(int id) {
//        Cottage cottage = this.cottageService.findOne(id);
//        return cottage.getRoomsNumber() * cottage.getBedsByRoom();
//    }

    public Date getDateEnd(DateDTO date) {
        String[] time = date.getTime().split(":");

          String hour = time[0];
        String minutes = time[1];
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(date.getDate());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour) - 2);
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
   calEnd.setTime(date.getDate());
        calEnd.add(Calendar.DAY_OF_YEAR, date.getNumber());
        return calEnd.getTime();
    }

    public void deleteById(int id) {
        Reservation r = this.reservationRepository.findById(id);
        this.reservationRepository.delete(r);
    }

    public void delete(Reservation r) {
        this.reservationRepository.delete(r);

    }

    public void saveReservation(Reservation r) {
        this.reservationRepository.save(r);
         }

    public boolean findFutureReservationsForEntity(int id) {
        List<Reservation> allReservations = this.reservationRepository.getReservationsForEntity(id);
        //Date today = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        List<Reservation> futureReservations = new ArrayList<>();
        if (allReservations.size() != 0) {
            for (Reservation r : allReservations) {
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(r.getTerm().getDateEnd());
                if (cal.before(cal2)) {
                    futureReservations.add(r);
                }
            }
        }
        if(futureReservations.size() != 0){
            return false;
        }else{
            return true;
        }
    }


    public boolean checkAvailability(ActionReservationDTO action) {
        List<Reservation> allReservations = this.reservationRepository.getReservationsForEntity(action.getEntityId());
        String[] time = action.getTimeStart().split(":");
        String hour = time[0];
        String minutes = time[1];

        Calendar calStartAction = Calendar.getInstance();
        Calendar calEndAction = Calendar.getInstance();

        calStartAction.setTime(action.getDateStart());
        calStartAction.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calStartAction.add(Calendar.MINUTE, Integer.parseInt(minutes));

        calEndAction.setTime(calStartAction.getTime());
        calEndAction.add(Calendar.DAY_OF_YEAR, action.getDuration());

        Boolean isNotReserved = true;

        if (allReservations.size() != 0) {
            for (Reservation r : allReservations) {
                Calendar calRStart = Calendar.getInstance();
                calRStart.setTime(r.getTerm().getDateStart());

                Calendar calREnd = Calendar.getInstance();
                calREnd.setTime(r.getTerm().getDateEnd());

                    if(calRStart.getTime().after(calStartAction.getTime()) && calREnd.getTime().after(calEndAction.getTime())
                       && calStartAction.getTime().before(calRStart.getTime()) && calEndAction.getTime().before(calREnd.getTime())
                    && calEndAction.getTime().after(calRStart.getTime())){
                        isNotReserved = false;
                    }
                    else if(calRStart.getTime().before(calStartAction.getTime()) && calREnd.getTime().before(calEndAction.getTime())
                     && calStartAction.getTime().after(calRStart.getTime()) && calEndAction.getTime().after(calREnd.getTime())
                    && calStartAction.getTime().before(calREnd.getTime())     //ovo pokriva kompletan period nakon neke rezervacije
                    ){
                        isNotReserved = false;
                    }
                    else if(calRStart.getTime().before(calStartAction.getTime()) && calREnd.getTime().after(calEndAction.getTime())
                      && calStartAction.getTime().after(calRStart.getTime()) && calEndAction.getTime().before(calREnd.getTime())){
                        isNotReserved = false;
                    }
                    else if(calRStart.getTime().after(calStartAction.getTime()) && calREnd.getTime().before(calEndAction.getTime())
                    && calStartAction.getTime().before(calRStart.getTime()) && calEndAction.getTime().after(calREnd.getTime())){
                        isNotReserved = false;
                    }
            }
        }
        return isNotReserved;
    }


    public void saveActionBoat(ActionReservationDTO reservation)  {

        EntityClass entity = this.entityService.findById(reservation.getEntityId()); //koji je entitet
        //getting start and end date, end action date nije ishendlan
        String[] time = reservation.getTimeStart().split(":");
        String hour = time[0];
        String minutes = time[1];
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(reservation.getDateStart());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour) - 2);
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(calStart.getTime());
        calEnd.add(Calendar.DAY_OF_YEAR, reservation.getDuration());

        //definisem promo period
        Calendar calEndAction = Calendar.getInstance();
        calEndAction.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEndAction.setTime(reservation.getDateEndAction());


        Reservation newReservation = new Reservation();
        newReservation.setTerm(reservedTermService.saveNewTerm(new ReservedTerm(calStart.getTime(), calEnd.getTime(), entity, false)));

        newReservation.setPrice(reservation.getPrice());
        //definisem promo period
         newReservation.setValidFrom(new Date());
         newReservation.setValidTo(calEndAction.getTime());

        newReservation.setClient(null);  //slobodna za klijenta
        newReservation.setAction(true);  //na akciji
        newReservation.setEntity(entity);
        newReservation.setEntityType(EntityType.BOAT);
        newReservation.setDuration(reservation.getDuration());

        // ArrayList<ReservationServices> services = new ArrayList<>();

        //dodatne usluge ubacene
        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);
            // services.add(a);

            a.setReservation(newReservation);
            newReservation.getReservationServices().add(a);
            //  this.reservationServicesService.save(a); //ovde puca za servise kad treba da pravi reservation service tabelu

        }

        //newReservation.setReservationServices(services);
        reservationRepository.save(newReservation);

        //servisima dodeljujem ovu rezervaciju
        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);
            a.setReservation(newReservation);
            this.reservationServicesService.save(a);
        }
        reservationRepository.save(newReservation);

        List<Client> subscribedClients = entityService.findSubscribedClients(entity.getId());

        //slanje mejla svim pretplacenim
        for (Client c: subscribedClients ) {
            this.emailService.sendEmailForCreatedAction(c.getName(), entity.getName());
        }


    }

    public void saveActionCottage(ActionReservationDTO reservation)  {

        EntityClass entity = this.entityService.findById(reservation.getEntityId()); //koji je entitet

        //getting start and end date, end action date nije ishendlan
        String[] time = reservation.getTimeStart().split(":");
        String hour = time[0];
        String minutes = time[1];
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(reservation.getDateStart());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour) - 2);
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(calStart.getTime());
        calEnd.add(Calendar.DAY_OF_YEAR, reservation.getDuration());

        //definisem promo period
        Calendar calEndAction = Calendar.getInstance();
        calEndAction.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEndAction.setTime(reservation.getDateEndAction());

        Reservation newReservation = new Reservation();
        newReservation.setTerm(reservedTermService.saveNewTerm(new ReservedTerm(calStart.getTime(), calEnd.getTime(), entity, false)));

        newReservation.setPrice(reservation.getPrice());
        //definisem promo period
        newReservation.setValidFrom(new Date());
        newReservation.setValidTo(calEndAction.getTime());

        newReservation.setClient(null);  //slobodna za klijenta
        newReservation.setAction(true);  //na akciji
        newReservation.setEntity(entity);
        newReservation.setEntityType(EntityType.COTTAGE);
        newReservation.setDuration(reservation.getDuration());

       // ArrayList<ReservationServices> services = new ArrayList<>();

        //dodatne usluge ubacene
        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);
           // services.add(a);

            a.setReservation(newReservation);
            newReservation.getReservationServices().add(a);
         //  this.reservationServicesService.save(a); //ovde puca za servise kad treba da pravi reservation service tabelu

        }

        //newReservation.setReservationServices(services);
        reservationRepository.save(newReservation);

        //servisima dodeljujem ovu rezervaciju
        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);
            a.setReservation(newReservation);
            this.reservationServicesService.save(a);
        }
        reservationRepository.save(newReservation);

        List<Client> subscribedClients = entityService.findSubscribedClients(entity.getId());

        //slanje mejla svim pretplacenim
        for (Client c: subscribedClients ) {
            this.emailService.sendEmailForCreatedAction(c.getName(), entity.getName());
        }
    }
}
