package com.example.demo.controller.business;

import com.example.demo.dto.business.ComplaintClientDTO;
import com.example.demo.dto.business.EvaluateDTO;
import com.example.demo.model.business.Complaint;
import com.example.demo.model.business.Evaluate;
import com.example.demo.service.business.ComplaintService;
import com.example.demo.service.business.EvaluateService;
import com.example.demo.service.email.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/evaluate")
public class EvaluateController {
    private EvaluateService evaluateService;
    private EmailSenderService service;
    public EvaluateController(EvaluateService evaluateService,EmailSenderService service){
        this.evaluateService = evaluateService;
        this.service = service;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<EvaluateDTO>> getAll(){
        System.out.print("Here i am");
        List<Evaluate> allEvaluates = evaluateService.findAll();
        List<EvaluateDTO> evaluates = new ArrayList<>();
        for(Evaluate e : allEvaluates ) {
            if (e.getAccepted() == null) {
                EvaluateDTO evaluateDTO = new EvaluateDTO(e);
                evaluates.add(evaluateDTO);
            }
        }
        return  new ResponseEntity<>(evaluates, HttpStatus.OK);
    }

}
