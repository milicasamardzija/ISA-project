package com.example.demo.service.business;

import com.example.demo.dto.business.DateReportDTO;
import com.example.demo.dto.business.InformationsForChart;
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
import com.example.demo.repository.users.BoatOwnerRepository;
import com.example.demo.repository.users.CottageOwnerRepository;
import com.example.demo.service.entities.BoatService;
import com.example.demo.service.entities.CottageService;
import com.example.demo.service.entities.EntityService;
import com.example.demo.service.users.ClientService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ReportOwnerService {

    private ReportOwnerRepository reportOwnerRepository;
    private EntityService entityService;
    private ClientService clientService;
    private BoatService boatService;
    private CottageService cottageService;
    private CottageOwnerRepository coRepo;
    private BoatOwnerRepository boRepo;

    private ReservationService reservationService;


    public ReportOwnerService(ReportOwnerRepository reportOwnerRepository,EntityService entityService, ClientService clientService, ReservationService reservationService,BoatService boatService, CottageService cottageService, CottageOwnerRepository coRepo, BoatOwnerRepository boRepo){
        this.reportOwnerRepository = reportOwnerRepository;
       this.entityService = entityService;
        this.clientService = clientService;
        this.reservationService = reservationService;
        this.boatService = boatService;
        this.cottageService = cottageService;
        this.boRepo= boRepo;
        this.coRepo = coRepo;
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
//version one for report
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

    public InformationsForChart getReportForChart(DateReportDTO dto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<String> names = new ArrayList<>();
        List<Integer> number = new ArrayList<>();
        List<Double>  grade = new ArrayList<>();
        List<Integer>  earning = new ArrayList<>();

        Calendar calFrom = Calendar.getInstance();
        calFrom.setTime(dto.getDateFrom());
        Calendar calTo = Calendar.getInstance();
        calTo.setTime(dto.getDateTo());

        if(dto.getType() == 1)  //cottage
        {
            List<Cottage> allCottages = cottageService.findAllOwnerCottages(user.getId());
            if(allCottages.size() != 0) {
                for (Cottage c : allCottages) {
                    names.add(c.getName());
                    grade.add(c.getGrade());
                    List<Reservation> reservations = reservationService.getAllReservationForEntity(c.getId());
                    int numPeople = 0;
                    int money = 0;
                    if(reservations.size() != 0){
                    for (Reservation r : reservations) {
                        Calendar calStart = Calendar.getInstance();
                        Calendar calEnd = Calendar.getInstance();
                        calStart.setTime(r.getTerm().getDateStart());
                        calEnd.setTime(r.getTerm().getDateEnd());
                        if (calStart.getTime().after(calFrom.getTime()) && calEnd.getTime().before(calTo.getTime())) {
                            money += r.getPrice();
                            numPeople += c.getBedsByRoom() * c.getRoomsNumber();
                        }
                    }
                    }
                    number.add(numPeople);
                    earning.add(money);

                }
            }
        }
        if(dto.getType() == 0){
            List<Boat> allBoats = boatService.findBoatsForBoatOwner(user.getId());
            if(allBoats.size() != 0) {
            for (Boat c: allBoats ) {
                names.add(c.getName());
                grade.add(c.getGrade());
                List<Reservation> reservations = reservationService.getAllReservationForEntity(c.getId());
                int numPeople = 0;
                int money = 0;
                if (reservations.size() != 0) {
                    for (Reservation r : reservations) {
                        Calendar calStart = Calendar.getInstance();
                        Calendar calEnd = Calendar.getInstance();
                        calStart.setTime(r.getTerm().getDateStart());
                        calEnd.setTime(r.getTerm().getDateEnd());
                        if (calStart.getTime().after(calFrom.getTime()) && calEnd.getTime().before(calTo.getTime())) {
                            numPeople += c.getQuantity();
                            money += r.getPrice();
                        }
                    }
                }
                number.add(numPeople);
                earning.add(money);
            }
            }
        }
        return  new InformationsForChart(names, number, grade, earning);
    }

}
