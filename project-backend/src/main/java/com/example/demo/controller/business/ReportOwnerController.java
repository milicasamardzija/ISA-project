package com.example.demo.controller.business;

import com.example.demo.dto.business.ReportOwnerDTO;
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
}
