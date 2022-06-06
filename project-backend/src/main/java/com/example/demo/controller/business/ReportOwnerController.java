package com.example.demo.controller.business;

import com.example.demo.dto.business.DateReportDTO;
import com.example.demo.dto.business.InformationsForChart;
import com.example.demo.dto.business.ReportOwnerDTO;
import com.example.demo.model.business.ReportForOwner;
import com.example.demo.service.business.ReportOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/reportOwner")
public class ReportOwnerController {

    private ReportOwnerService reportOwnerService;


    public  ReportOwnerController(ReportOwnerService reportOwnerService){
        this.reportOwnerService = reportOwnerService;
    }


    @PreAuthorize("hasAnyRole('BOAT_OWNER','COTTAGE_OWNER')")
    @PostMapping("/reportCottage")
    public ResponseEntity<HttpStatus> addReportForCottageOwner(@RequestBody ReportOwnerDTO dto){
        this.reportOwnerService.addReportCottageOwner(dto);

        return  new ResponseEntity<>( HttpStatus.OK);
    }


@PreAuthorize("hasAnyRole('BOAT_OWNER','COTTAGE_OWNER')")
    @PostMapping("/reportOwner")
    public ResponseEntity<InformationsForChart> addReportForBusiness(@RequestBody DateReportDTO dto){
        return  new ResponseEntity<>(this.reportOwnerService.getReportForChart(dto), HttpStatus.OK);
    }
}
