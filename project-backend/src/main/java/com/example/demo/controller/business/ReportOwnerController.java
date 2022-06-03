package com.example.demo.controller.business;

import com.example.demo.dto.business.DateReportDTO;
import com.example.demo.dto.business.InformationsForChart;
import com.example.demo.dto.business.ReportOwnerDTO;
import com.example.demo.model.business.ReportForOwner;
import com.example.demo.service.business.ReportOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    @PostMapping("/reportCottage")
    public ResponseEntity<HttpStatus> addReportForCottageOwner(@RequestBody ReportOwnerDTO dto){
        this.reportOwnerService.addReportCottageOwner(dto);

        return  new ResponseEntity<>( HttpStatus.OK);
    }

//    @PostMapping("/reportOwner")
//    public ResponseEntity<ReportForOwner> addReportForBusiness(@RequestBody ReportForOwner dto){
//        return  new ResponseEntity<>(this.reportOwnerService.createReportForBusiness(dto), HttpStatus.OK);
//    }

    @PostMapping("/reportOwner")
    public ResponseEntity<InformationsForChart> addReportForBusiness(@RequestBody DateReportDTO dto){
        return  new ResponseEntity<>(this.reportOwnerService.getReportForChart(dto), HttpStatus.OK);
    }
}
