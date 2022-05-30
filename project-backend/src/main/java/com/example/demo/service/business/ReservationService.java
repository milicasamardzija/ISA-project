package com.example.demo.service.business;

import com.example.demo.dto.business.PriceDTO;
import com.example.demo.dto.business.ReservationNewDTO;
import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.enums.EntityType;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.business.ReservationServices;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ReservationRepository;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.entities.AdditionalServicesService;
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
    private ReservedTermService reservedTermService;
    private AdditionalServicesService additionalService;
    private ReservationServicesService reservationServicesService;

    public ReservationService (ReservationRepository reservationRepository, EmailService emailService, ClientService clientService, EntityService entityService, ReservedTermService reservedTermService, AdditionalServicesService additionalService, ReservationServicesService reservationServicesService) {
        this.reservationRepository = reservationRepository;
        this.emailService = emailService;
        this.clientService = clientService;
        this.entityService = entityService;
        this.reservedTermService = reservedTermService;
        this.additionalService = additionalService;
        this.reservationServicesService = reservationServicesService;
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

        if (canceledReservation == null){
            entity.getReservedTerms().add(newReservation.getTerm());
            this.reservationRepository.save(newReservation);

            for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
                ReservationServices a = new ReservationServices(as);
                a.setReservation(newReservation);
                this.reservationServicesService.save(a);
            }

            this.emailService.sendEmailForReservation(user);
        }
        //vrati poruku
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

    public String cancelReservation(int id) {
        Reservation reservation = this.reservationRepository.findById(id);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, 3);

        if (cal.getTime().before(reservation.getTerm().getDateStart())){
            reservation.setCanceled(true);

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
}
