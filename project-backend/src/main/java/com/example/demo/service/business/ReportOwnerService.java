package com.example.demo.service.business;

import com.example.demo.dto.business.ReportOwnerDTO;
import com.example.demo.dto.enums.EntityType;
import com.example.demo.model.business.ReportForOwner;
import com.example.demo.model.business.ReportOwner;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ReportOwnerRepository;
import com.example.demo.service.entities.BoatService;
import com.example.demo.service.entities.CottageService;
import com.example.demo.service.entities.EntityService;
import com.example.demo.service.users.ClientService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ReportOwnerService {

    private ReportOwnerRepository reportOwnerRepository;
    private EntityService entityService;
    private ClientService clientService;
    private BoatService boatService;
    private CottageService cottageService;

    private ReservationService reservationService;


    public ReportOwnerService(ReportOwnerRepository reportOwnerRepository,EntityService entityService, ClientService clientService, ReservationService reservationService,BoatService boatService, CottageService cottageService){
        this.reportOwnerRepository = reportOwnerRepository;
       this.entityService = entityService;
        this.clientService = clientService;
        this.reservationService = reservationService;
        this.boatService = boatService;
        this.cottageService = cottageService;
    }

    public void save(ReportOwner report) {
    this.reportOwnerRepository.save(report);
    }

    public void addReportCottageOwner(ReportOwnerDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User cottageOwner = (User)authentication.getPrincipal();

        Client client = clientService.findClientById(dto.getIdClient());

        ReportOwner reportOwner = new ReportOwner(client, cottageOwner, dto.getComment(),dto.restrictionType);

        this.save(reportOwner);

    }

    public ReportForOwner createReportForBusiness(ReportForOwner reportForOwner){
        ReportForOwner newReport = new ReportForOwner();
        EntityClass entity = entityService.findById(reportForOwner.getEntityId());
        newReport.setEntityName(entity.getName());
        newReport.setGrade(entity.getGrade());
        newReport.setType(reportForOwner.getType());
        newReport.setEntityId(entity.getId());

        double earning = 0;
        int peopleNum= 0;

        Calendar calFrom = Calendar.getInstance();
        calFrom.setTime(reportForOwner.getFromDate());
        newReport.setFromDate(calFrom.getTime());
        Calendar calTo = Calendar.getInstance();
        calTo.setTime(reportForOwner.getToDate());
        newReport.setToDate(reportForOwner.getToDate());
        if(reportForOwner.getType().name() == "BOAT"){
            Boat boat = boatService.findOne(entity.getId());
            List<Reservation> reservationForEntity = reservationService.getAllReservationForEntity(entity.getId());
            for (Reservation r: reservationForEntity ) {
                Calendar calStart = Calendar.getInstance();
                Calendar calEnd = Calendar.getInstance();
                calStart.setTime(r.getTerm().getDateStart());
                calEnd.setTime(r.getTerm().getDateEnd());
                if(calStart.getTime().after(calFrom.getTime()) && calEnd.getTime().before(calTo.getTime())){
                   earning += r.getPrice();
                   peopleNum += boat.getQuantity();
                }
            }

        }else{
            Cottage cottage = cottageService.findOne(entity.getId());
            List<Reservation> reservationForEntity = reservationService.getAllReservationForEntity(entity.getId());
            for (Reservation r: reservationForEntity ) {
                Calendar calStart = Calendar.getInstance();
                Calendar calEnd = Calendar.getInstance();
                calStart.setTime(r.getTerm().getDateStart());
                calEnd.setTime(r.getTerm().getDateEnd());
                if(calStart.getTime().after(calFrom.getTime()) && calEnd.getTime().before(calTo.getTime())){
                    earning += r.getPrice();
                    peopleNum += cottage.getBedsByRoom()* cottage.getRoomsNumber();
                }
            }

        }
        newReport.setNumberOfPeople(peopleNum);
        newReport.setEarning(earning);

        return  newReport;

    }

}
