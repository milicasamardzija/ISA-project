package com.example.demo.service.business;

import com.example.demo.dto.business.ReportOwnerDTO;
import com.example.demo.model.business.ReportOwner;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ReportOwnerRepository;
import com.example.demo.service.entities.EntityService;
import com.example.demo.service.users.ClientService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReportOwnerService {

    private ReportOwnerRepository reportOwnerRepository;
    private EntityService entityService;
    private ClientService clientService;


    public ReportOwnerService(ReportOwnerRepository reportOwnerRepository,EntityService entityService, ClientService clientService){
        this.reportOwnerRepository = reportOwnerRepository;
        this.entityService = entityService;
        this.clientService = clientService;
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

}
